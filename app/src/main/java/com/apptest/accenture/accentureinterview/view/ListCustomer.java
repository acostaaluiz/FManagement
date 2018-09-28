package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;

import java.util.ArrayList;

public interface ListCustomer {

    interface View{

        MyApplication getMyApplication();
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void loadCustomers(ArrayList<ModelCustomer> customers);
        void connectionServerError(String error);
    }

    interface Presenter{

        void initInterface();
    }
}
