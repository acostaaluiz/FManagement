package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;
import com.apptest.accenture.accentureinterview.model.ModelFrequency;
import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.ArrayList;

/**
 * Created by fcost on 02/07/2018.
 */

public interface Income {

    interface View{

        void incomeEmptyError();
        void frequencyEmptyError();
        void incomeDateEmptyError();
        void incomeToDateEmptyError();
        void priceEmptyError();
        void categoryIncomeEmptyError();
        void incomeAlreadyExists();
        void loadIncomeFrequency(ArrayList<ModelFrequency> frequencies);
        void loadIncomeCategories(ArrayList<ModelCategoryIncome> incomeCategories);
        void successfullyRegister(ArrayList<ModelIncome> incomes);
        void connectionServerError(String error);
        void initLoadProgressBar();
        void finishLoadProgressBar();
        void setTxtIncomeDate(String date);
        void setTxtIncomeToDate(String date);
        void showDatePickerDialog(int year, int month, int day, String txtId);
    }

    interface Presenter{

        void creationIncomeProcess(ModelIncome modelIncome);
        void deleteIncomeData(ModelIncome modelIncome);
        void initInterface();
        void convertPickerBtnDate(int year, int month, int day);
        void convertPickerBtnToDate(int year, int month, int day);
        void initDatePickerDialog(String txtId);
    }
}
