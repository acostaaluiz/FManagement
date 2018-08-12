package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelFrequency;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FrequencyRestAPI implements FrequencyRetrofitAPI{

    @Override
    public Call<ArrayList<ModelFrequency>> loadAllFrequencies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FrequencyRetrofitAPI frequencyRetrofitAPI = retrofit.create(FrequencyRetrofitAPI.class);

        Call<ArrayList<ModelFrequency>> requestGetAllFrequencies = frequencyRetrofitAPI.loadAllFrequencies();

        return requestGetAllFrequencies;
    }
}
