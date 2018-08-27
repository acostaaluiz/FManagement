package com.apptest.accenture.accentureinterview.view;

import android.app.Activity;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.model.ModelCategory;
import com.apptest.accenture.accentureinterview.model.ModelCreditCard;
import com.apptest.accenture.accentureinterview.model.ModelExpense;
import com.apptest.accenture.accentureinterview.model.ModelFrequency;

import java.util.ArrayList;

/**
 * Created by fcost on 01/07/2018.
 */

public interface Expense {

    interface View{

        void expenseEmptyError();
        void frequencyEmptyError();
        void expenseDateEmptyError();
        void categoryEmptyError();
        void priceEmptyError();
        void expenseAlreadyExists();
        void loadCreditCardSpinner(ArrayList<ModelCreditCard> creditCards);
        void loadCategorySpinner(ArrayList<ModelCategory> categories);
        void loadFrequencySpinner(ArrayList<ModelFrequency> frequencies);
        void initLoadProgressBar();
        void finishLoadProgressBar();
        MyApplication getMyApplication();
        void connectionServerError(String error);
        void setTxtExpenseDate(String date);
        void showDatePickerDialog(int year, int month, int day);
        Activity getContext();
    }

    interface Presenter{
        void creationExpenseProcess(ModelExpense modelExpense);
        void deleteExpense(ModelExpense modelExpense);
        void initInterface();
        void convertPickerBtnDate(int year, int month, int day);
        void initDatePickerDialog();
    }
}
