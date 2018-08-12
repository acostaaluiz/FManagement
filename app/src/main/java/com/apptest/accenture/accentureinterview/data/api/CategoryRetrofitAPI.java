package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCategory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/categorycontroller/";

    @GET("checkcategory/{category}")
    Call<ModelCategory> checkCategory(@Path("category") String category);

    @GET("getallcategories")
    Call<ArrayList<ModelCategory>> getAllCategories();

    @POST("savecategory")
    Call<ModelCategory> saveCategory(@Body ModelCategory modelCategory);
}
