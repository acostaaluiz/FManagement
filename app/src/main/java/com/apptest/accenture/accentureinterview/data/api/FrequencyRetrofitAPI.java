package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelFrequency;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface FrequencyRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:3000/frequencycontroller/";

    @GET("getallfrequencies")
    Call<ArrayList<ModelFrequency>> loadAllFrequencies(@Header("x-access-token") String token);
}
