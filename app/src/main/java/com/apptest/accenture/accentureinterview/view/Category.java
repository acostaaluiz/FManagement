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
        void errorRegister();
    }

    interface Presenter{
        void saveExpenseCategory(ModelCategory modelCategory);
        void loadAllExpenseCategory();
    }
}
