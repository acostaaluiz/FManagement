package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.model.ModelFrequency;

import java.util.ArrayList;

public interface Frequency {

    interface Presenter{

        void loadAllFrequencies();
        ArrayList<ModelFrequency> getAllFrequencies();
    }
}
