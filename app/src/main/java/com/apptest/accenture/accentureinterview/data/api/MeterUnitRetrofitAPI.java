package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelMeterUnit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MeterUnitRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:3000/meterunitcontroller/";

    @GET("getallmeterunits")
    Call<ArrayList<ModelMeterUnit>> loadAllMeterUnits(@Header("x-access-token") String token);
}
