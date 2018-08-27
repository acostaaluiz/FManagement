package com.apptest.accenture.accentureinterview.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.adapters.ArrayAdapterCreditCard;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.presenter.PresenterCreditCard;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.CreditCard;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class FragmentCreditCard extends Fragment implements CreditCard.View {

    private EditText txtCreditCardValue;
    private EditText txtBankValue;
    private EditText txtCreditCardFlagValue;
    private EditText txtCreditCardLimitValue;
    private EditText txtCreditCardEndDateValue;
    private Button btnRegister;
    private SwipeMenuListView listViewCreditCards;
    private ProgressDialog progressDialog;
    private CreditCard.Presenter creditCardPresenter;
    private ModelCreditCard myModelCreditCard;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_creditcard, container, false);

        listViewCreditCards = vw.findViewById(R.id.listViewCreditCards);
        txtCreditCardValue = vw.findViewById(R.id.txtCreditCardValue);
        txtBankValue = vw.findViewById(R.id.txtBankValue);
        txtCreditCardFlagValue = vw.findViewById(R.id.txtCreditCardFlagValue);
        txtCreditCardLimitValue = vw.findViewById(R.id.txtCreditCardLimitValue);
        txtCreditCardEndDateValue = vw.findViewById(R.id.txtCreditCardEndDateValue);
        btnRegister = vw.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String creditCard = txtCreditCardValue.getText().toString();
                String bank = txtBankValue.getText().toString();
                String creditCardFlag = txtCreditCardFlagValue.getText().toString();
                String creditCardLimit = txtCreditCardLimitValue.getText().toString();
                String creditCardEndDate = txtCreditCardEndDateValue.getText().toString();

                myModelCreditCard = new ModelCreditCard(creditCard, bank, creditCardFlag, creditCardLimit, creditCardEndDate);

                creditCardPresenter.creationCreditCardProcess(myModelCreditCard);

            }
        });


        creditCardPresenter = new PresenterCreditCard(this, getActivity());
        creditCardPresenter.initInterface();

        return vw;
    }

    @Override
    public void creditCardEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_creditcard));
    }

    @Override
    public void bankEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_bank));
    }

    @Override
    public void creditCardFlagEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_creditcard_flag));
    }

    @Override
    public void creditCardLimitEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_creditcard_limit));
    }

    @Override
    public void creditCardEndDateEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_creditcard_enddate));
    }

    @Override
    public void creditCardAlReadyExists() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.creditcard_already_exists));
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public void successfullyRegister(final ArrayList<ModelCreditCard> creditCards) {

        listViewCreditCards.setAdapter(new ArrayAdapterCreditCard(getActivity(), R.layout.listview_creditcard, creditCards));
        listViewCreditCards.setTextFilterEnabled(true);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xEB,
                        0xEC, 0xF2)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listViewCreditCards.setMenuCreator(creator);

        listViewCreditCards.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        creditCardPresenter.deleteCreditCardData(creditCards.get(index));
                        break;
                }

                return false;
            }
        });

        listViewCreditCards.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void initLoadProgressBar() {

        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),
                getResources().getString(R.string.loading));
    }

    @Override
    public void finishLoadProgressBar() {

        progressDialog.dismiss();
    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
