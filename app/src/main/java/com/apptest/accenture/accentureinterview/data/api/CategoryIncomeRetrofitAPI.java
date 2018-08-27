package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCategoryIncome;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryIncomeRetrofitAPI {

    //String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/categoryincomecontroller/";
    String BASE_SERVICE = "http://10.0.2.2:3000/categoryincomecontroller/";

    @GET("checkcategoryincome/{categoryincome}")
    Call<ModelCategoryIncome> checkCategoryIncome(@Path("categoryincome") String categoryincome, @Header("x-access-token") String token);

    @GET("getallincomecategories")
    Call<ArrayList<ModelCategoryIncome>> getAllIncomeCategories(@Header("x-access-token") String token);

    @POST("savecategoryincome")
    Call<ModelCategoryIncome> saveIncome(@Body ModelCategoryIncome modelCategory, @Header("x-access-token") String token);
}
