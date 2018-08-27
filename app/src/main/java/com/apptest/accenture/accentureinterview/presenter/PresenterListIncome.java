package com.apptest.accenture.accentureinterview.presenter;

import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.IncomeRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelIncome;
import com.apptest.accenture.accentureinterview.view.ListIncome;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterListIncome implements ListIncome.Presenter {

    private IncomeRestAPI incomeRestAPI;
    private ListIncome.View activityListIncome;
    private ArrayList<ModelIncome> myIncomes;

    public PresenterListIncome(ListIncome.View activityListIncome){

        this.activityListIncome = activityListIncome;
        this.incomeRestAPI = new IncomeRestAPI();
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

            activityListIncome.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myIncomes = incomeRestAPI.getAllIncomes(activityListIncome.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {

                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("OK")) {

                activityListIncome.loadIncomes(myIncomes);
            }

            activityListIncome.finishLoadProgressBar();
        }
    }


}
