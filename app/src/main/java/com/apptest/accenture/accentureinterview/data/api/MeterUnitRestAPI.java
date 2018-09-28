package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelMeterUnit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeterUnitRestAPI implements MeterUnitRetrofitAPI{

    @Override
    public Call<ArrayList<ModelMeterUnit>> loadAllMeterUnits(String token) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MeterUnitRetrofitAPI meterUnitRetrofitAPI = retrofit.create(MeterUnitRetrofitAPI.class);

        return meterUnitRetrofitAPI.loadAllMeterUnits(token);
    }
}
