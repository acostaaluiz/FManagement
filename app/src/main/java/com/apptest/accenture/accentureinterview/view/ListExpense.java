package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelExpense;

import java.util.ArrayList;

public interface ListExpense {

    interface View{

        MyApplication getMyApplication();
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void loadExpenses(ArrayList<ModelExpense> expeses);
        void connectionServerError(String error);
    }

    interface Presenter{

        void initInterface();
    }
}
