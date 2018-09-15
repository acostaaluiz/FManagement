package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelItem;

import java.util.ArrayList;

public interface Item {

    interface View {

        MyApplication getMyApplication();
        void itemEmptyError();
        void itemNameEmptyError();
        void itemPriceEmptyError();
        void itemStoredQuantityEmptyError();
        void itemAlReadyExists();
        void connectionServerError(String error);
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void successfullyRegister(ArrayList<ModelItem> incomes);
    }

    interface Presenter {

        void initInterface();
        void creationItemProcess(ModelItem modelItem);
        void deleteItemData(ModelItem modelItem);
    }
}
