package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelItem;

import java.util.ArrayList;

public interface ListItem {

    interface View{

        MyApplication getMyApplication();
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void loadItems(ArrayList<ModelItem> items);
        void connectionServerError(String error);
    }

    interface Presenter{

        void initInterface();
    }
}
