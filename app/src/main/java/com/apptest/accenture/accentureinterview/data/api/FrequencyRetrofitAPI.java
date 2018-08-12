package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelFrequency;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FrequencyRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/frequencycontroller/";

    @GET("getallfrequencies")
    Call<ArrayList<ModelFrequency>> loadAllFrequencies();
}
