package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelExpense;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExpenseRetrofitAPI {

    //String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/expensecontroller/";
    String BASE_SERVICE = "http://10.0.2.2:3000/expensecontroller/";

    @GET("checkexpense/{expense}")
    Call<ModelExpense> checkExpense(@Path("expense") String expense, @Header("x-access-token") String token);

    @GET("getallexpenses")
    Call<ArrayList<ModelExpense>> getAllExpenses(@Header("x-access-token") String token);

    @POST("saveexpense")
    Call<ModelExpense> saveExpense(@Body ModelExpense modelExpense, @Header("x-access-token") String token);
}
