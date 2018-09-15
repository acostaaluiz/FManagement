package com.apptest.accenture.accentureinterview.presenter;

import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.ItemRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelItem;
import com.apptest.accenture.accentureinterview.view.ListItem;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterListItem implements ListItem.Presenter {

    private ItemRestAPI itemRestAPI;
    private ListItem.View activityListItem;
    private ArrayList<ModelItem> myItems;

    public PresenterListItem(ListItem.View activityListItem){

        this.activityListItem = activityListItem;
        itemRestAPI = new ItemRestAPI();
    }

    @Override
    public void initInterface() {

        InitInterface initInterface = new InitInterface();
        initInterface.execute();

    }

    private class InitInterface extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            activityListItem.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myItems = itemRestAPI.getAllItems(activityListItem.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {

                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("OK")) {

                activityListItem.loadItems(myItems);
            }

            activityListItem.finishLoadProgressBar();
        }
    }
}
