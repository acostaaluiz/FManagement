package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCustomer;

public interface Customer {

    interface View{

        MyApplication getMyApplication();
        void customerEmptyError();
        void customerNameEmptyError();
        void cepEmptyError();
        void addressEmptyError();
        void numberEmptyError();
        void neighborhoodEmptyError();
        void cityEmptyError();
        void stateEmptyError();
        void telephone1EmptyError();
        void emailEmptyError();
        void customerAlreadyExists();
        void connectionServerError(String error);
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void successfullyRegister();

    }

    interface Presenter{
        void creationCustomerProcess(ModelCustomer modelCustomer);
    }
}
