package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreditCardRestAPI implements CreditCardRetrofitAPI {

    @Override
    public Call<ModelCreditCard> checkCreditCard(String creditcard, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreditCardRetrofitAPI creditCardRetrofitAPI = retrofit.create(CreditCardRetrofitAPI.class);

        Call<ModelCreditCard> requestCheckCreditCard = creditCardRetrofitAPI.checkCreditCard(creditcard, token);

        return requestCheckCreditCard;
    }

    @Override
    public Call<ArrayList<ModelCreditCard>> getAllCreditCards(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreditCardRetrofitAPI creditCardRetrofitAPI = retrofit.create(CreditCardRetrofitAPI.class);

        Call<ArrayList<ModelCreditCard>> requestGetAllCreditCards = creditCardRetrofitAPI.getAllCreditCards(token);

        return requestGetAllCreditCards;
    }

    @Override
    public Call<ModelCreditCard> saveCreditCard(ModelCreditCard modelCreditCard, String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreditCardRetrofitAPI creditCardRetrofitAPI = retrofit.create(CreditCardRetrofitAPI.class);

        Call<ModelCreditCard> requestSaveCreditCard = creditCardRetrofitAPI.saveCreditCard(modelCreditCard, token);

        return requestSaveCreditCard;
    }
}
