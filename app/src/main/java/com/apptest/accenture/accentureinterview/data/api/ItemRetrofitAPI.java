package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:3000/itemcontroller/";

    @GET("checkitem/{income}")
    Call<ModelItem> checkItem(@Path("item") String item, @Header("x-access-token") String token);

    @GET("getallitems")
    Call<ArrayList<ModelItem>> getAllItems(@Header("x-access-token") String token);

    @POST("saveitem")
    Call<ModelItem> saveItem(@Body ModelItem modelItem, @Header("x-access-token") String token);
}
