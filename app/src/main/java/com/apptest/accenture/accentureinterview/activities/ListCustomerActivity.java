package com.apptest.accenture.accentureinterview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.RecyclerCustomerAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;
import com.apptest.accenture.accentureinterview.presenter.PresenterListCustomer;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.ListCustomer;

import java.util.ArrayList;

public class ListCustomerActivity extends AppCompatActivity implements ListCustomer.View {

    private RecyclerView myRecyclerView;
    private ProgressDialog progressDialog;
    private ListCustomer.Presenter listCustomerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_customer_activity);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        listCustomerPresenter = new PresenterListCustomer(this);
        listCustomerPresenter.initInterface();
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
    public void loadCustomers(ArrayList<ModelCustomer> customers) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(this);
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerCustomerAdapter(customers);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);
    }

    @Override
    public void connectionServerError(String error) {

    }
}
