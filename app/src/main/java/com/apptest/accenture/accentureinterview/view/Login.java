package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.model.ModelLogin;

/**
 * Created by fcost on 28/06/2018.
 */

public interface Login {

    interface View{

        void userEmptyError();
        void passwordEmptyError();
        void invalidUserLogin();
        void invalidPasswordLogin();
        void successfullyLoggedIn();
    }

    interface Presenter{
        void isValidLogin(ModelLogin modelLogin);
        void doLogin(ModelLogin modelLogin);
    }
}
