package com.apptest.accenture.accentureinterview.data.api;

import com.apptest.accenture.accentureinterview.model.ModelUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserRetrofitAPI {

    String BASE_SERVICE = "http://10.0.2.2:8080/FManagementAPI/webresources/usercontroller/";

    @GET("checkuser/{user}/{password}")
    Call<ModelUser> checkUser(@Path("user") String user, @Path("password") String password);

    @POST("saveuser")
    Call<ModelUser> saveUser(@Body ModelUser modelUser);
}
