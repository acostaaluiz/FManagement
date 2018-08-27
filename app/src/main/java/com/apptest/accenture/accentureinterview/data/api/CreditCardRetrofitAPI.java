package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CreditCardRetrofitAPI {

    //String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/creditcardcontroller/";
    String BASE_SERVICE = "http://10.0.2.2:3000/creditcardcontroller/";

    @GET("checkcreditcard/{creditcard}")
    Call<ModelCreditCard> checkCreditCard(@Path("creditcard") String creditcard, @Header("x-access-token") String token);

    @GET("getallcreditcards")
    Call<ArrayList<ModelCreditCard>> getAllCreditCards(@Header("x-access-token") String token);

    @POST("savecreditcard")
    Call<ModelCreditCard> saveCreditCard(@Body ModelCreditCard modelCreditCard, @Header("x-access-token") String token);
}
