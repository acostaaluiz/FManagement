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
        void errorUserCreated();
        void emailInvalidFormat();
        void telephoneInvalidFormat();
        void thereIsNoInternetConnection();
        void setTxtTelephone(String newTelephone);
        void setTxtTelephoneMask(TextWatcher textWatcher);
    }

    interface Presenter{
        void isValidUser(ModelUser modelUser);
        void createUser(ModelUser modelUser);
        void maskTelephoneNumber(EditText txtTelephone);
    }
}
