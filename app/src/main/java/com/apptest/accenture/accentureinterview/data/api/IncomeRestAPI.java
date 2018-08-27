package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IncomeRestAPI implements IncomeRetrofitAPI {

    @Override
    public Call<ModelIncome> checkIncome(String income, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IncomeRetrofitAPI incomeRetrofitAPI = retrofit.create(IncomeRetrofitAPI.class);

        Call<ModelIncome> requestCheckIncome = incomeRetrofitAPI.checkIncome(income, token);

        return requestCheckIncome;
    }

    @Override
    public Call<ArrayList<ModelIncome>> getAllIncomes(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IncomeRetrofitAPI incomeRetrofitAPI = retrofit.create(IncomeRetrofitAPI.class);

        Call<ArrayList<ModelIncome>> requestGetAllIncomes = incomeRetrofitAPI.getAllIncomes(token);

        return requestGetAllIncomes;
    }

    @Override
    public Call<ModelIncome> saveIncome(ModelIncome modelIcome, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IncomeRetrofitAPI incomeRetrofitAPI = retrofit.create(IncomeRetrofitAPI.class);

        Call<ModelIncome> requestSaveIncome = incomeRetrofitAPI.saveIncome(modelIcome, token);

        return requestSaveIncome;
    }
}
