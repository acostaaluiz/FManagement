package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCustomer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:3000/customercontroller/";

    @GET("checkcustomer/{customer}")
    Call<ModelCustomer> checkCustomer(@Path("item") String item, @Header("x-access-token") String token);

    @GET("getallcustomers")
    Call<ArrayList<ModelCustomer>> getAllCustomers(@Header("x-access-token") String token);

    @POST("savecustomer")
    Call<ModelCustomer> saveCustomer(@Body ModelCustomer modelCustomer, @Header("x-access-token") String token);
}
