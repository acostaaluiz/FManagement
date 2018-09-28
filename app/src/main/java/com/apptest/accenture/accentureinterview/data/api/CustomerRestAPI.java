package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCustomer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerRestAPI implements CustomerRetrofitAPI {

    @Override
    public Call<ModelCustomer> checkCustomer(String customer, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerRetrofitAPI customerRetrofitAPI = retrofit.create(CustomerRetrofitAPI.class);

        Call<ModelCustomer> requestCheckCustomer = customerRetrofitAPI.checkCustomer(customer, token);

        return requestCheckCustomer;
    }

    @Override
    public Call<ArrayList<ModelCustomer>> getAllCustomers(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerRetrofitAPI customerRetrofitAPI = retrofit.create(CustomerRetrofitAPI.class);

        Call<ArrayList<ModelCustomer>> requestGetAllCustomers = customerRetrofitAPI.getAllCustomers(token);

        return requestGetAllCustomers;
    }

    @Override
    public Call<ModelCustomer> saveCustomer(ModelCustomer modelCustomer, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CustomerRetrofitAPI customerRetrofitAPI = retrofit.create(CustomerRetrofitAPI.class);

        Call<ModelCustomer> requestSaveCustomer = customerRetrofitAPI.saveCustomer(modelCustomer, token);

        return requestSaveCustomer;
    }
}
