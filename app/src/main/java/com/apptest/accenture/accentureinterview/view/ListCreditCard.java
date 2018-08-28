package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.ArrayList;

public interface ListCreditCard {

    interface View{

        MyApplication getMyApplication();
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void loadCreditCards(ArrayList<ModelCreditCard> creditCards);
        void connectionServerError(String error);
    }

    interface Presenter {
        void initInterface();
    }
}
