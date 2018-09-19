package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.apptest.accenture.accentureinterview.app.MyApplication;
import com.apptest.accenture.accentureinterview.data.access.LoginController;
import com.apptest.accenture.accentureinterview.data.api.UserRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelLogin;
import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.apptest.accenture.accentureinterview.view.Login;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by fcost on 28/06/2018.
 */

public class PresenterLogin implements Login.Presenter{

    private Login.View fragmentLogin;
    private LoginController loginController;
    private UserRestAPI userRestAPI;
    private ModelUser modelUser;

    public PresenterLogin(Login.View fragmentLogin, Context context){
        this.fragmentLogin = fragmentLogin;
        loginController = new LoginController(context);
        userRestAPI = new UserRestAPI();
    }

    @Override
    public void isValidLogin(final ModelUser mu){

        this.modelUser = mu;

        if(modelUser.getUser().isEmpty())
            fragmentLogin.userEmptyError();
        else if(modelUser.getPassword().isEmpty())
            fragmentLogin.passwordEmptyError();
        else {

            fragmentLogin.initLoadProgressBar();

            Call<ModelUser> checkUser = userRestAPI.checkUser(modelUser.getUser(), modelUser.getPassword());

            checkUser.enqueue(new Callback<ModelUser>() {
                @Override
                public void onResponse(Call<ModelUser> call, retrofit2.Response<ModelUser> response) {
                    if (!response.isSuccessful()) {

                        modelUser = new ModelUser(modelUser.getUser(), modelUser.getPassword(), String.valueOf(response.code()));

                    } else {

                        ModelUser modelUserChecked = response.body();
                        modelUser = modelUserChecked;

                        fragmentLogin.getMyApplication().setJwtToken(modelUser.getToken());

                        MyApplication myApplication = new MyApplication();
                        myApplication.setJwtToken(modelUser.getToken());

                        if (modelUser.getResponse().equals("INVALID_USER"))
                            fragmentLogin.invalidUserLogin();
                        else if (modelUser.getResponse().equals("INVALID_PASSWORD"))
                            fragmentLogin.invalidPasswordLogin();
                        else if (modelUser.getResponse().equals("OK"))
                            fragmentLogin.successfullyLoggedIn();
                        else
                            fragmentLogin.connectionServerError(modelUser.getResponse());



                        fragmentLogin.finishLoadProgressBar();
                    }
                }

                @Override
                public void onFailure(Call<ModelUser> call, Throwable t) {

                    Exception ex = new Exception(t);
                    modelUser = new ModelUser(modelUser.getUser(), modelUser.getPassword(), ex.toString());

                    fragmentLogin.connectionServerError(modelUser.getResponse());

                    fragmentLogin.finishLoadProgressBar();
                }
            });

        }
    }

    @Override
    public void doLogin(ModelLogin modelLogin) {
        loginController.saveLoginData(modelLogin);
    }
}
