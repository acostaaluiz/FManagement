package com.apptest.accenture.accentureinterview.presenter;

import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.CustomerRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;
import com.apptest.accenture.accentureinterview.view.ListCustomer;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterListCustomer implements ListCustomer.Presenter {

    private ListCustomer.View activityListCustomer;
    private CustomerRestAPI customerRestAPI;
    private ArrayList<ModelCustomer> myCustomers;

    public PresenterListCustomer(ListCustomer.View listCustomerActivity){

        this.activityListCustomer = listCustomerActivity;
        this.customerRestAPI = new CustomerRestAPI();
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
            activityListCustomer.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myCustomers = customerRestAPI.getAllCustomers(activityListCustomer.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {

                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("OK"))
                activityListCustomer.loadCustomers(myCustomers);

            activityListCustomer.finishLoadProgressBar();
        }
    }
}
