package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelDate;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DateRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/datecontroller/";

    @GET("getdatetimenow")
    Call<ModelDate> getDateTimeNow();
}
