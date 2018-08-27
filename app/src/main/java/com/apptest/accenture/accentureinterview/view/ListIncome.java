package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.ArrayList;

public interface ListIncome {

    interface View{
        MyApplication getMyApplication();
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void loadIncomes(ArrayList<ModelIncome> incomes);
        void connectionServerError(String error);


    }

    interface Presenter {
        void initInterface();
    }
}
