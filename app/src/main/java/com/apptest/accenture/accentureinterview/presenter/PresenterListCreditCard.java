package com.apptest.accenture.accentureinterview.presenter;

import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.CreditCardRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.view.ListCreditCard;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterListCreditCard implements ListCreditCard.Presenter {

    private CreditCardRestAPI creditCardRestAPI;
    private ListCreditCard.View activityListCreditCard;

    public PresenterListCreditCard(ListCreditCard.View activityListCreditCard){

        this.activityListCreditCard = activityListCreditCard;
        this.creditCardRestAPI = new CreditCardRestAPI();
    }

    @Override
    public void initInterface() {

        InitInterface initInterface = new InitInterface();
        initInterface.execute();
    }

    private class InitInterface extends
            AsyncTask<String, String, String> {

        ArrayList<ModelCreditCard> myCreditCards;

        @Override
        protected void onPreExecute() {
            activityListCreditCard.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            myCreditCards = new ArrayList<>();

            try {

                myCreditCards = creditCardRestAPI.getAllCreditCards(activityListCreditCard.getMyApplication().getJwtToken()).execute().body();

            } catch (IOException e) {
                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK")) {
                activityListCreditCard.loadCreditCards(myCreditCards);
            } else
                activityListCreditCard.connectionServerError(result);

            activityListCreditCard.finishLoadProgressBar();
        }
    }
}
