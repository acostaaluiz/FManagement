package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRestAPI implements CategoryRetrofitAPI {

    @Override
    public Call<ModelCategory> checkCategory(String category, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryRetrofitAPI categoryRetrofitAPI = retrofit.create(CategoryRetrofitAPI.class);

        Call<ModelCategory> requestCheckCategory = categoryRetrofitAPI.checkCategory(category, token);

        return requestCheckCategory;
    }

    @Override
    public Call<ArrayList<ModelCategory>> getAllCategories(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryRetrofitAPI categoryRetrofitAPI = retrofit.create(CategoryRetrofitAPI.class);

        Call<ArrayList<ModelCategory>> requestGetAllCategories = categoryRetrofitAPI.getAllCategories(token);

        return requestGetAllCategories;
    }

    @Override
    public Call<ModelCategory> saveCategory(ModelCategory modelCategory, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryRetrofitAPI categoryRetrofitAPI = retrofit.create(CategoryRetrofitAPI.class);
        Call<ModelCategory> requestSaveCategory = categoryRetrofitAPI.saveCategory(modelCategory, token);

        return requestSaveCategory;
    }
}
