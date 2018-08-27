package com.apptest.accenture.accentureinterview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.RecyclerExpenseAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelExpense;
import com.apptest.accenture.accentureinterview.presenter.PresenterListExpense;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.ListExpense;

import java.util.ArrayList;

public class ListExpenseActivity extends AppCompatActivity implements ListExpense.View{

    private RecyclerView myRecyclerView;
    private ListExpense.Presenter listExpensePresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_expense_activity);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        listExpensePresenter = new PresenterListExpense(this);
        listExpensePresenter.initInterface();
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
    public void loadExpenses(ArrayList<ModelExpense> expeses) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(this);
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerExpenseAdapter(expeses);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);
    }

    @Override
    public void connectionServerError(String error) {

    }
}
