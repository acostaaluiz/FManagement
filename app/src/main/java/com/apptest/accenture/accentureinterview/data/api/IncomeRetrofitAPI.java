package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelIncome;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IncomeRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/incomecontroller/";

    @GET("checkincome/{income}")
    Call<ModelIncome> checkIncome(@Path("income") String income);

    @GET("getallincomes")
    Call<ArrayList<ModelIncome>> getAllIncomes();

    @POST("saveincome")
    Call<ModelIncome> saveIncome(@Body ModelIncome modelIcome);
}
