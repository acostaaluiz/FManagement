package com.apptest.accenture.accentureinterview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.RecyclerIncomeAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelIncome;
import com.apptest.accenture.accentureinterview.presenter.PresenterListIncome;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.ListIncome;

import java.util.ArrayList;

public class ListIncomeActivity extends AppCompatActivity implements ListIncome.View {

    private RecyclerView myRecyclerView;
    private ProgressDialog progressDialog;
    private ListIncome.Presenter listIncomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_income_activity);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        listIncomePresenter = new PresenterListIncome(this);
        listIncomePresenter.initInterface();
    }

    @Override
    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    @Override
    public void initLoadProgressBar() {
        progressDialog = new ProgressDialog();
        progressDialog.show(getSupportFragmentManager(),
                getResources().getString(R.string.loading));
    }

    @Override
    public void finishLoadProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void loadIncomes(ArrayList<ModelIncome> incomes) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(this);
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerIncomeAdapter(incomes);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);
    }

    @Override
    public void connectionServerError(String error) {

    }
}
