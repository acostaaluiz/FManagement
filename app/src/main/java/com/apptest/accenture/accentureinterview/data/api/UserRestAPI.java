package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.google.gson.Gson;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRestAPI implements UserRetrofitAPI{

    public UserRestAPI(){

    }

    @Override
    public Call<ModelUser> checkUser(String user, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserRetrofitAPI.BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserRetrofitAPI userRetrofitAPI = retrofit.create(UserRetrofitAPI.class);
        Call<ModelUser> requestCheckUser = userRetrofitAPI.checkUser(user, password);

        return requestCheckUser;
    }

    @Override
    public Call<ModelUser> saveUser(ModelUser modelUser) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserRetrofitAPI.BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserRetrofitAPI userRetrofitAPI = retrofit.create(UserRetrofitAPI.class);
        Call<ModelUser> requestSaveUser = userRetrofitAPI.saveUser(modelUser);

        return requestSaveUser;
    }
}
