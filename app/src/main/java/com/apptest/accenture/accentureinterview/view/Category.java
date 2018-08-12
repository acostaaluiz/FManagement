package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;

/**
 * Created by fcost on 30/06/2018.
 */

public interface Category {

    interface View{

        void expenseCategoryEmptyError();
        void expenseCategoryAlreadyExists();
        void successfullyRegister(ArrayList<ModelCategory> modelCategoryList);
        void connectionServerError(String error);
        void thereIsNoInternetConnection();
        void initLoadProgressBar();
        void finishLoadProgressBar();
    }

    interface Presenter{
        void creationExpenseCategoryProcess(ModelCategory modelCategory);
        void deleteExpenseCategory(ModelCategory modelCategory);
        void loadAllExpenseCategory();
    }
}
