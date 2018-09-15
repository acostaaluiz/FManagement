package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemRestAPI implements ItemRetrofitAPI{

    @Override
    public Call<ModelItem> checkItem(String item, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemRetrofitAPI itemRetrofitAPI = retrofit.create(ItemRetrofitAPI.class);

        Call<ModelItem> requestCheckItem = itemRetrofitAPI.checkItem(item, token);

        return requestCheckItem;
    }

    @Override
    public Call<ArrayList<ModelItem>> getAllItems(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemRetrofitAPI itemRetrofitAPI = retrofit.create(ItemRetrofitAPI.class);

        Call<ArrayList<ModelItem>> requestGetAllItems = itemRetrofitAPI.getAllItems(token);

        return requestGetAllItems;
    }

    @Override
    public Call<ModelItem> saveItem(ModelItem modelItem, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemRetrofitAPI itemRetrofitAPI = retrofit.create(ItemRetrofitAPI.class);

        Call<ModelItem> requestSaveItem = itemRetrofitAPI.saveItem(modelItem, token);

        return requestSaveItem;
    }
}
