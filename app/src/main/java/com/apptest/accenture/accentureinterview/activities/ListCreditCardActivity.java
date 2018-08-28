package com.apptest.accenture.accentureinterview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.RecyclerCreditCardAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.presenter.PresenterListCreditCard;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.ListCreditCard;

import java.util.ArrayList;

public class ListCreditCardActivity extends AppCompatActivity implements ListCreditCard.View {

    private RecyclerView myRecyclerView;
    private ProgressDialog progressDialog;
    private ListCreditCard.Presenter presenterListCreditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_creditcard_activity);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        presenterListCreditCard = new PresenterListCreditCard(this);
        presenterListCreditCard.initInterface();
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
    public void loadCreditCards(ArrayList<ModelCreditCard> creditCards) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(this);
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerCreditCardAdapter(creditCards);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);
    }

    @Override
    public void connectionServerError(String error) {

    }
}
