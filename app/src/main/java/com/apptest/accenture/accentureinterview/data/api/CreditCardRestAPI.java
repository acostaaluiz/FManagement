package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelCreditCard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreditCardRestAPI implements CreditCardRetrofitAPI {

    @Override
    public Call<ModelCreditCard> checkCreditCard(String creditcard) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreditCardRetrofitAPI creditCardRetrofitAPI = retrofit.create(CreditCardRetrofitAPI.class);

        Call<ModelCreditCard> requestCheckCreditCard = creditCardRetrofitAPI.checkCreditCard(creditcard);

        return requestCheckCreditCard;
    }

    @Override
    public Call<ArrayList<ModelCreditCard>> getAllCreditCards() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreditCardRetrofitAPI creditCardRetrofitAPI = retrofit.create(CreditCardRetrofitAPI.class);

        Call<ArrayList<ModelCreditCard>> requestGetAllCreditCards = creditCardRetrofitAPI.getAllCreditCards();

        return requestGetAllCreditCards;
    }

    @Override
    public Call<ModelCreditCard> saveCreditCard(ModelCreditCard modelCreditCard) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreditCardRetrofitAPI creditCardRetrofitAPI = retrofit.create(CreditCardRetrofitAPI.class);

        Call<ModelCreditCard> requestSaveCreditCard = creditCardRetrofitAPI.saveCreditCard(modelCreditCard);

        return requestSaveCreditCard;
    }
}
