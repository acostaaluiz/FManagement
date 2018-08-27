package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;

import java.util.ArrayList;

public interface CategoryIncome {

    interface View{

        void incomeCategoryEmptyError();
        void incomeCategoryAlreadyExists();
        void successfullyRegister(ArrayList<ModelCategoryIncome> modelCategoryIncomeList);
        void connectionServerError(String error);
        void thereIsNoInternetConnection();
        void initLoadProgressBar();
        void finishLoadProgressBar();
        MyApplication getMyApplication();
    }

    interface Presenter{
        void creationIncomeCategoryProcess(ModelCategoryIncome modelCategoryIncome);
        void deleteIncomeCategory(ModelCategoryIncome modelCategoryIncome);
        void loadAllIncomeCategories();
    }
}
