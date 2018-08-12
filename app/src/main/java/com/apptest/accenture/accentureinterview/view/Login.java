package com.apptest.accenture.accentureinterview.view;

import com.apptest.accenture.accentureinterview.model.ModelLogin;
import com.apptest.accenture.accentureinterview.model.ModelUser;

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
        void connectionServerError(String error);
        void initLoadProgressBar();
        void finishLoadProgressBar();
    }

    interface Presenter{
        void isValidLogin(ModelUser modelUser);
        void doLogin(ModelLogin modelLogin);
    }
}
