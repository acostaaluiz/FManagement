package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryIncomeRestAPI implements CategoryIncomeRetrofitAPI {

    @Override
    public Call<ModelCategoryIncome> checkCategoryIncome(String categoryincome) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryIncomeRetrofitAPI categoryIncomeRetrofitAPI = retrofit.create(CategoryIncomeRetrofitAPI.class);

        Call<ModelCategoryIncome> requestCheckCategoryIncome = categoryIncomeRetrofitAPI.checkCategoryIncome(categoryincome);

        return requestCheckCategoryIncome;
    }

    @Override
    public Call<ArrayList<ModelCategoryIncome>> getAllIncomeCategories() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryIncomeRetrofitAPI categoryIncomeRetrofitAPI = retrofit.create(CategoryIncomeRetrofitAPI.class);

        Call<ArrayList<ModelCategoryIncome>> requestGetAllIncomeCategories = categoryIncomeRetrofitAPI.getAllIncomeCategories();

        return requestGetAllIncomeCategories;
    }

    @Override
    public Call<ModelCategoryIncome> saveIncome(ModelCategoryIncome modelCategory) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryIncomeRetrofitAPI categoryIncomeRetrofitAPI = retrofit.create(CategoryIncomeRetrofitAPI.class);

        Call<ModelCategoryIncome> requestSaveCategoryIncome = categoryIncomeRetrofitAPI.saveIncome(modelCategory);

        return requestSaveCategoryIncome;
    }
}
