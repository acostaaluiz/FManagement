package com.apptest.accenture.accentureinterview.fragments;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.activities.ListIncomeActivity;
import com.apptest.accenture.accentureinterview.adapters.ArrayAdapterIncome;
import com.apptest.accenture.accentureinterview.adapters.CategoryIncomeSpinnerAdapter;
import com.apptest.accenture.accentureinterview.adapters.FrequencySpinnerAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;
import com.apptest.accenture.accentureinterview.model.ModelFrequency;
import com.apptest.accenture.accentureinterview.model.ModelIncome;
import com.apptest.accenture.accentureinterview.presenter.PresenterIncome;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.Income;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

/**
 * Created by fcost on 02/07/2018.
 */

public class FragmentIncome extends Fragment implements Income.View {

    private EditText txtIncomeValue;
    private EditText txtIncomeDateValue;
    private EditText txtIncomeToDateValue;
    private EditText txtIncomePriceValue;
    private Spinner spnIncomeFrequency;
    private Spinner spnCategoryIncome;
    private Button btnRegister;
    private Button btnDate;
    private Button btnToDate;
    private TextView txtViewList;
    private ModelIncome modelIncome;
    private ModelCategoryIncome myCategoryIncome;
    private ModelFrequency myModelFrequency;
    private Income.Presenter incomePresenter;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_income, container, false);

        txtIncomeValue = vw.findViewById(R.id.txtIncomeValue);
        txtIncomeDateValue = vw.findViewById(R.id.txtIncomeDateValue);
        txtIncomeToDateValue = vw.findViewById(R.id.txtIncomeToDateValue);
        txtIncomePriceValue = vw.findViewById(R.id.txtViewIncomePriceValue);
        spnIncomeFrequency = vw.findViewById(R.id.spnFrequency);
        spnCategoryIncome = vw.findViewById(R.id.spnCategoryIncome);
        btnRegister = vw.findViewById(R.id.btnRegister);
        btnDate = vw.findViewById(R.id.btnIncomeDate);
        btnToDate = vw.findViewById(R.id.btnIncomeToDate);
        txtViewList = vw.findViewById(R.id.txtViewList);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String income = txtIncomeValue.getText().toString();
                String incomeDate = txtIncomeDateValue.getText().toString();
                String incomeToDate = txtIncomeToDateValue.getText().toString();
                String incomePrice = txtIncomePriceValue.getText().toString();
                String frequency = myModelFrequency.getFrequency();
                String categoryIncome = myCategoryIncome.getCategoryIncome();

                modelIncome = new ModelIncome(income, frequency, incomeDate, incomeToDate, incomePrice, categoryIncome);

                incomePresenter.creationIncomeProcess(modelIncome);

            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               incomePresenter.initDatePickerDialog("INCOME_DATE");
            }
        });

        btnToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomePresenter.initDatePickerDialog("INCOME_TODATE");
            }
        });

        txtViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), ListIncomeActivity.class);
                startActivity(myIntent);
            }
        });

        spnIncomeFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                FrequencySpinnerAdapter spinnerAdapter = (FrequencySpinnerAdapter) spnIncomeFrequency.getAdapter();

                myModelFrequency = spinnerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        spnCategoryIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                CategoryIncomeSpinnerAdapter spinnerAdapter = (CategoryIncomeSpinnerAdapter) spnCategoryIncome.getAdapter();

                myCategoryIncome = spinnerAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        incomePresenter = new PresenterIncome(this, getActivity());
        incomePresenter.initInterface();

        return vw;
    }

    @Override
    public void incomeEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_income));
    }

    @Override
    public void frequencyEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_income_frequency));
    }

    @Override
    public void incomeDateEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_income_date));
    }

    @Override
    public void incomeToDateEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_income_todate));
    }

    @Override
    public void priceEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_income_price));
    }

    @Override
    public void categoryIncomeEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_category_income));
    }

    @Override
    public void incomeAlreadyExists() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.income_already_exists));
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public void loadIncomeFrequency(ArrayList<ModelFrequency> frequencies) {

        ArrayAdapter<ModelFrequency> adapter = new FrequencySpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                frequencies
        );

        spnIncomeFrequency.setAdapter(adapter);
    }

    @Override
    public void loadIncomeCategories(ArrayList<ModelCategoryIncome> incomeCategories) {

        ArrayAdapter<ModelCategoryIncome> adapter = new CategoryIncomeSpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                incomeCategories
        );

        spnCategoryIncome.setAdapter(adapter);

    }

    @Override
    public void successfullyRegister(final ArrayList<ModelIncome> incomes) {

    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
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
    public void setTxtIncomeDate(String date) {

        txtIncomeDateValue.setText(date);
    }

    @Override
    public void setTxtIncomeToDate(String date) {

        txtIncomeToDateValue.setText(date);
    }

    @Override
    public void showDatePickerDialog(int year, int month, int day, final String txtId) {

        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                if(txtId.equals("INCOME_DATE"))
                    incomePresenter.convertPickerBtnDate(year, monthOfYear, dayOfMonth);
                else if(txtId.equals("INCOME_TODATE"))
                    incomePresenter.convertPickerBtnToDate(year, monthOfYear, dayOfMonth);
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
