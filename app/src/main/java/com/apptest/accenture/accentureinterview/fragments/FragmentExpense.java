package com.apptest.accenture.accentureinterview.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.adapters.ArrayAdapterExpense;
import com.apptest.accenture.accentureinterview.adapters.CategorySpinnerAdapter;
import com.apptest.accenture.accentureinterview.adapters.CreditCardSpinnerAdapter;
import com.apptest.accenture.accentureinterview.adapters.FrequencySpinnerAdapter;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.model.ModelExpense;
import com.apptest.accenture.accentureinterview.model.ModelFrequency;
import com.apptest.accenture.accentureinterview.presenter.PresenterExpense;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.Expense;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

/**
 * Created by fcost on 01/07/2018.
 */

public class FragmentExpense extends Fragment implements Expense.View {

    private EditText txtExpenseValue;
    private EditText txtExpenseDateValue;
    private EditText txtPriceValue;
    private CheckBox checkBoxHasCreditCard;
    private Spinner spnCreditCard;
    private Spinner spnCategory;
    private Spinner spnFrequency;
    private SwipeMenuListView listViewExpense;
    private Button btnRegister;
    private Button btnExpenseDate;
    private ModelExpense modelExpense;
    private ModelCreditCard myModelCreditCard;
    private ModelFrequency myModelFrequency;
    private ModelCategory myModelCategory;
    private Expense.Presenter expensePresenter;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_expense, container, false);

        listViewExpense = vw.findViewById(R.id.listViewExpense);
        txtExpenseValue = vw.findViewById(R.id.txtExpenseValue);
        txtExpenseDateValue = vw.findViewById(R.id.txtExpenseDateValue);
        txtPriceValue = vw.findViewById(R.id.txtViewPriceValue);
        checkBoxHasCreditCard = vw.findViewById(R.id.checkBoxHasCreditCard);
        spnCreditCard = vw.findViewById(R.id.spnCreditCard);
        spnCreditCard.setEnabled(false);
        spnCategory = vw.findViewById(R.id.spnCategory);
        spnFrequency = vw.findViewById(R.id.spnFrequency);
        btnRegister = vw.findViewById(R.id.btnRegister);
        btnExpenseDate = vw.findViewById(R.id.btnExpenseDate);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String expense = txtExpenseValue.getText().toString();
                String expenseDate = txtExpenseDateValue.getText().toString();
                String price = txtPriceValue.getText().toString();
                String creditCard = myModelCreditCard.getCreditCard();
                String category = myModelCategory.getCategory();
                String frequency = myModelFrequency.getFrequency();

                if(modelExpense.getHasCreditCard().equals("true"))
                    modelExpense.setCreditCard(creditCard);

                modelExpense = new ModelExpense(expense, expenseDate, category, modelExpense.getHasCreditCard(), price, frequency);

                expensePresenter.creationExpenseProcess(modelExpense);

            }
        });

        btnExpenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                expensePresenter.initDatePickerDialog();

            }
        });

        checkBoxHasCreditCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                  if(isChecked){
                    modelExpense.setHasCreditCard("true");
                    spnCreditCard.setEnabled(true);
                  }
                  else {
                      modelExpense.setHasCreditCard("false");
                      spnCreditCard.setEnabled(false);
                  }

               }
        });

        spnCreditCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                CreditCardSpinnerAdapter spinnerAdapter = (CreditCardSpinnerAdapter) spnCreditCard.getAdapter();

                myModelCreditCard = spinnerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        spnFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                FrequencySpinnerAdapter spinnerAdapter = (FrequencySpinnerAdapter) spnFrequency.getAdapter();

                myModelFrequency = spinnerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                CategorySpinnerAdapter spinnerAdapter = (CategorySpinnerAdapter) spnCategory.getAdapter();

                myModelCategory = spinnerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        modelExpense = new ModelExpense();
        modelExpense.setHasCreditCard("false");
        expensePresenter = new PresenterExpense(this, getActivity());
        expensePresenter.initInterface();

        return vw;
    }

    @Override
    public void expenseEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_expense));
    }

    @Override
    public void frequencyEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_expense_frequency));
    }

    @Override
    public void expenseDateEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_expense_date));
    }

    @Override
    public void categoryEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_category));
    }

    @Override
    public void priceEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_price));
    }

    @Override
    public void expenseAlreadyExists() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.expense_already_exists));
    }

    @Override
    public void errorRegister() {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                getResources().getString(R.string.error_sqlite_insert));
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public Activity getContext() {
        return getContext();

    }

    @Override
    public void loadExpenses(final ArrayList<ModelExpense> expenses) {

        listViewExpense.setAdapter(new ArrayAdapterExpense(getActivity(), R.layout.listview_expense, expenses));
        listViewExpense.setTextFilterEnabled(true);

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

        listViewExpense.setMenuCreator(creator);

        listViewExpense.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        expensePresenter.deleteExpense(expenses.get(index));
                        break;
                }

                return false;
            }
        });

        listViewExpense.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void loadCreditCardSpinner(ArrayList<ModelCreditCard> creditCards) {

        ArrayAdapter<ModelCreditCard> adapter = new CreditCardSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                creditCards
        );

        spnCreditCard.setAdapter(adapter);

    }

    @Override
    public void loadCategorySpinner(ArrayList<ModelCategory> categories) {

        ArrayAdapter<ModelCategory> adapter = new CategorySpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                categories
        );

        spnCategory.setAdapter(adapter);

    }

    @Override
    public void loadFrequencySpinner(ArrayList<ModelFrequency> frequencies) {

        ArrayAdapter<ModelFrequency> adapter = new FrequencySpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                frequencies
        );

        spnFrequency.setAdapter(adapter);
    }

    @Override
    public void initLoadProgressBar() {

        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),
                getResources().getString(R.string.loading));
    }

    @Override
    public void finishLoadProgressBar() {

        if(progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void setTxtExpenseDate(String date) {

        this.txtExpenseDateValue.setText(date);
    }

    @Override
    public void showDatePickerDialog(int year, int month, int day) {

        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                expensePresenter.convertPickerBtnDate(year, monthOfYear, dayOfMonth);

            }
        }, year, month,day).show();
    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
