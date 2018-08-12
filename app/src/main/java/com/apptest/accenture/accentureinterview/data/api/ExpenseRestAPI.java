package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelExpense;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpenseRestAPI implements ExpenseRetrofitAPI{

    @Override
    public Call<ModelExpense> checkExpense(String expense) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExpenseRetrofitAPI expenseRetrofitAPI = retrofit.create(ExpenseRetrofitAPI.class);

        Call<ModelExpense> requestCheckExpense = expenseRetrofitAPI.checkExpense(expense);

        return requestCheckExpense;
    }

    @Override
    public Call<ArrayList<ModelExpense>> getAllExpenses() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExpenseRetrofitAPI expenseRetrofitAPI = retrofit.create(ExpenseRetrofitAPI.class);

        Call<ArrayList<ModelExpense>> requestGetAllExpenses = expenseRetrofitAPI.getAllExpenses();

        return requestGetAllExpenses;
    }

    @Override
    public Call<ModelExpense> saveExpense(ModelExpense modelExpense) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExpenseRetrofitAPI expenseRetrofitAPI = retrofit.create(ExpenseRetrofitAPI.class);

        Call<ModelExpense> requestSaveExpense = expenseRetrofitAPI.saveExpense(modelExpense);

        return requestSaveExpense;
    }
}
