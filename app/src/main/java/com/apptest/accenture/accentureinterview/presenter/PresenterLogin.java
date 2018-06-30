package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;

import com.apptest.accenture.accentureinterview.data.access.LoginController;
import com.apptest.accenture.accentureinterview.data.access.UserController;
import com.apptest.accenture.accentureinterview.model.ModelLogin;
import com.apptest.accenture.accentureinterview.view.Login;

/**
 * Created by fcost on 28/06/2018.
 */

public class PresenterLogin implements Login.Presenter{

    private Login.View fragmentLogin;
    private LoginController loginController;

    public PresenterLogin(Login.View fragmentLogin, Context context){
        this.fragmentLogin = fragmentLogin;
        loginController = new LoginController(context);
    }

    @Override
    public void isValidLogin(ModelLogin modelLogin){

        if(modelLogin.getUser().isEmpty())
            fragmentLogin.userEmptyError();
        else if(modelLogin.getPassword().isEmpty())
            fragmentLogin.passwordEmptyError();

        if(!loginController.checkUser(modelLogin))
           fragmentLogin.invalidUserLogin();
        else{
            if(!loginController.checkPassword(modelLogin))
                fragmentLogin.invalidPasswordLogin();
            else
                fragmentLogin.successfullyLoggedIn();
        }
    }

    @Override
    public void doLogin(ModelLogin modelLogin) {

        boolean login = loginController.saveLoginData(modelLogin);

    }
}
