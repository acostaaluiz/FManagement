package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.data.api.CreditCardRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.view.CreditCard;

import java.io.IOException;
import java.util.ArrayList;

public class PresenterCreditCard implements CreditCard.Presenter {

    private CreditCardRestAPI creditCardRestAPI;
    private ArrayList<ModelCreditCard> myCreditCards;
    private ModelCreditCard myModelCreditCard;
    private CreditCard.View fragmentCreditCard;

    public PresenterCreditCard(CreditCard.View fragmentCreditCard, Context context){

        this.fragmentCreditCard = fragmentCreditCard;
        this.creditCardRestAPI = new CreditCardRestAPI();

    }

    @Override
    public void creationCreditCardProcess(ModelCreditCard modelCreditCard) {

        this.myModelCreditCard = modelCreditCard;

        CreateCreditCard createCreditCard = new CreateCreditCard();
        createCreditCard.execute();
    }

    @Override
    public void deleteCreditCardData(ModelCreditCard modelCreditCard) {

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
            fragmentCreditCard.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            myCreditCards = new ArrayList<>();

            try {

                myCreditCards = creditCardRestAPI.getAllCreditCards().execute().body();

            } catch (IOException e) {
                return e.toString();
            }

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("OK")) {
                fragmentCreditCard.successfullyRegister(myCreditCards);
            } else
                fragmentCreditCard.connectionServerError(result);

            fragmentCreditCard.finishLoadProgressBar();
        }
    }

    private class CreateCreditCard extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentCreditCard.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            try {

                if(myModelCreditCard.getCreditCard().isEmpty())
                    result = "CREDITCARD_EMPTY";
                else if(myModelCreditCard.getBank().isEmpty())
                    result = "BANK_EMPTY";
                else if(myModelCreditCard.getCreditCardFlag().isEmpty())
                    result = "CREDITCARD_FLAG_EMPTY";
                else if(myModelCreditCard.getCreditCardLimit().isEmpty())
                    result = "CREDITCARD_LIMIT_EMPTY";
                else if(myModelCreditCard.getCreditCardEndDate().isEmpty())
                    result = "CREDITCARD_ENDDATE_EMPTY";
                else {

                    myModelCreditCard = creditCardRestAPI.saveCreditCard(myModelCreditCard).execute().body();
                    result = myModelCreditCard.getResponse();
                }
            } catch (IOException e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("CREDITCARD_EMPTY"))
                fragmentCreditCard.creditCardEmptyError();
            else if(result.equals("BANK_EMPTY"))
                fragmentCreditCard.bankEmptyError();
            else if(result.equals("CREDITCARD_FLAG_EMPTY"))
                fragmentCreditCard.creditCardFlagEmptyError();
            else if(result.equals("CREDITCARD_LIMIT_EMPTY"))
                fragmentCreditCard.creditCardLimitEmptyError();
            else if(result.equals("CREDITCARD_ENDDATE_EMPTY"))
                fragmentCreditCard.creditCardEndDateEmptyError();
            else if(result.equals("INVALID_CREDITCARD"))
                fragmentCreditCard.creditCardAlReadyExists();
            else if(result.equals("OK")) {
                fragmentCreditCard.finishLoadProgressBar();

                InitInterface initInterface = new InitInterface();
                initInterface.execute();
            }
            else
                fragmentCreditCard.connectionServerError(result);

            fragmentCreditCard.finishLoadProgressBar();
        }
    }
}
