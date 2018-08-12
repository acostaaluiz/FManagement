package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelDate;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DateRestAPI implements DateRetrofitAPI{

    public DateRestAPI(){

    }

    @Override
    public Call<ModelDate> getDateTimeNow() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DateRetrofitAPI.BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DateRetrofitAPI dateRetrofitAPI = retrofit.create(DateRetrofitAPI.class);
        Call<ModelDate> requestCheckUser = dateRetrofitAPI.getDateTimeNow();

        return requestCheckUser;
    }
}
