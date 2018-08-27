package com.apptest.accenture.accentureinterview.presenter;

import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.ExpenseRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelExpense;
import com.apptest.accenture.accentureinterview.view.ListExpense;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterListExpense implements ListExpense.Presenter {

    private ListExpense.View activityListExpense;
    private ExpenseRestAPI expenseRestAPI;
    private ArrayList<ModelExpense> myExpenses;

    public PresenterListExpense(ListExpense.View activityListExpense){

        this.activityListExpense = activityListExpense;
        this.expenseRestAPI = new ExpenseRestAPI();
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

            activityListExpense.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                myExpenses = expenseRestAPI.getAllExpenses(activityListExpense.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {

                return e.toString();
            }

            return "OK";
        }


        @Override
        protected void onPostExecute(String result) {

            if (result.equals("OK")) {

                activityListExpense.loadExpenses(myExpenses);
            }

            activityListExpense.finishLoadProgressBar();
        }
    }
}
