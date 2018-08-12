package com.apptest.accenture.accentureinterview.view;

import android.text.TextWatcher;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.model.ModelUser;

/**
 * Created by fcost on 28/06/2018.
 */

public interface User {

    interface View{

        void userEmptyError();
        void passwordEmptyError();
        void emailEmptyError();
        void telefoneEmptyError();
        void userAlreadyExist();
        void successfullyUserCreated();
        void emailInvalidFormat();
        void telephoneInvalidFormat();
        void thereIsNoInternetConnection();
        void setTxtTelephoneMask(TextWatcher textWatcher);
        void connectionServerError(String error);
        void initLoadProgressBar();
        void finishLoadProgressBar();
    }

    interface Presenter{
        void isValidUser(ModelUser modelUser);
        void creationUserProcess(ModelUser modelUser);
        void maskTelephoneNumber(EditText txtTelephone);
    }
}
