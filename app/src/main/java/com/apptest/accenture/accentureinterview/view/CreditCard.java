package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.ArrayList;

public interface CreditCard {

    interface View{
        void creditCardEmptyError();
        void bankEmptyError();
        void creditCardFlagEmptyError();
        void creditCardLimitEmptyError();
        void creditCardEndDateEmptyError();
        void creditCardAlReadyExists();
        MyApplication getMyApplication();
        void connectionServerError(String error);
        void successfullyRegister(ArrayList<ModelCreditCard> creditCards);
        void initLoadProgressBar();
        void finishLoadProgressBar();
    }

    interface Presenter{
        void creationCreditCardProcess(ModelCreditCard modelCreditCard);
        void deleteCreditCardData(ModelCreditCard modelCreditCard);
        void initInterface();

    }
}
