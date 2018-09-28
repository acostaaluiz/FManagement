package com.apptest.accenture.accentureinterview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.adapters.RecyclerItemAdapter;
import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelItem;
import com.apptest.accenture.accentureinterview.presenter.PresenterListItem;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.ListItem;

import java.util.ArrayList;

public class ListItemActivity extends AppCompatActivity implements ListItem.View {

    private RecyclerView myRecyclerView;
    private ProgressDialog progressDialog;
    private ListItem.Presenter listItemPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_activity);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        listItemPresenter = new PresenterListItem(this);
        listItemPresenter.initInterface();
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
    public void loadItems(ArrayList<ModelItem> items) {

        RecyclerView.LayoutManager myLayouyManager = new LinearLayoutManager(this);
        RecyclerView.Adapter myRecyclerViewAdpter = new RecyclerItemAdapter(items);

        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayouyManager);
        myRecyclerView.setAdapter(myRecyclerViewAdpter);
    }

    @Override
    public void connectionServerError(String error) {

    }
}
