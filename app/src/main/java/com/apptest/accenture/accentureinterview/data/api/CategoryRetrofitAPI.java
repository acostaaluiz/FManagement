package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryRetrofitAPI {

    //String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/categorycontroller/";
    String BASE_SERVICE = "http://10.0.2.2:3000/categorycontroller/";

    @GET("checkcategory/{category}")
    Call<ModelCategory> checkCategory(@Path("category") String category, @Header("x-access-token") String token);

    @GET("getallcategories")
    Call<ArrayList<ModelCategory>> getAllCategories(@Header("x-access-token") String token);

    @POST("savecategory")
    Call<ModelCategory> saveCategory(@Body ModelCategory modelCategory, @Header("x-access-token") String token);
}
