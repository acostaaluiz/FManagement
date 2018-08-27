package com.apptest.accenture.accentureinterview.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.adapters.ArrayAdapterCategoryIncome;
import com.apptest.accenture.accentureinterview.adapters.RecyclerCategoryIncomeAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;
import com.apptest.accenture.accentureinterview.presenter.PresenterCategoryIncome;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.CategoryIncome;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class FragmentCategoryIncome extends Fragment implements CategoryIncome.View {

    private EditText txtIncomeCategoryValue;
    private Button btnRegister;
    private SwipeMenuListView listViewCategoryIncome;
    private RecyclerView myRecyclerView;
    private ModelCategoryIncome modelCategoryIncome;
    private CategoryIncome.Presenter categoryIncomePresenter;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_income_category, container, false);

        myRecyclerView = vw.findViewById(R.id.myRecyclerView);
        //listViewCategoryIncome = vw.findViewById(R.id.listViewCategoryIncome);
        txtIncomeCategoryValue = vw.findViewById(R.id.txtIncomeCategoryValue);
        btnRegister = vw.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String incomeCategory = txtIncomeCategoryValue.getText().toString();
                modelCategoryIncome = new ModelCategoryIncome(incomeCategory);

                categoryIncomePresenter.creationIncomeCategoryProcess(modelCategoryIncome);

            }
        });

        categoryIncomePresenter = new PresenterCategoryIncome(this, getActivity());
        categoryIncomePresenter.loadAllIncomeCategories();

        return vw;
    }


    @Override
    public void incomeCategoryEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_category));
    }

    @Override
    public void incomeCategoryAlreadyExists() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_category));
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public void thereIsNoInternetConnection() {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                getResources().getString(R.string.no_internet_connection));
    }

    @Override
    public void successfullyRegister(final ArrayList<ModelCategoryIncome> modelCategoryIncomeList) {


        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(getActivity());
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerCategoryIncomeAdapter(modelCategoryIncomeList);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);

        /*listViewCategoryIncome.setAdapter(
                new ArrayAdapterCategoryIncome(getActivity(),
                R.layout.list_view_category_income, modelCategoryIncomeList)
        );

        listViewCategoryIncome.setTextFilterEnabled(true);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
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

        listViewCategoryIncome.setMenuCreator(creator);

        listViewCategoryIncome.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        categoryIncomePresenter.deleteIncomeCategory(modelCategoryIncomeList.get(index));
                        break;
                }

                return false;
            }
        });*/

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

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
