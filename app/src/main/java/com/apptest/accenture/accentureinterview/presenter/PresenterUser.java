package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.data.access.UserController;
import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.apptest.accenture.accentureinterview.utility.MaskUtility;
import com.apptest.accenture.accentureinterview.utility.NetworkConnectionTest;
import com.apptest.accenture.accentureinterview.utility.RegexTest;
import com.apptest.accenture.accentureinterview.view.User;

/**
 * Created by fcost on 28/06/2018.
 */

public class PresenterUser implements User.Presenter {

    private UserController userController;
    private User.View fragmentUser;
    private NetworkConnectionTest networkConnectionTest;
    private RegexTest regexTest;

    public PresenterUser(User.View fragmentUser, Context context){

        this.userController = new UserController(context);
        this.fragmentUser = fragmentUser;
        this.networkConnectionTest = new NetworkConnectionTest(context);
    }

    @Override
    public void isValidUser(ModelUser modelUser) {

        if(!userController.checkUser(modelUser))
            fragmentUser.userAlreadyExist();
    }

    @Override
    public void createUser(ModelUser modelUser) {

        regexTest = new RegexTest();

        if(!networkConnectionTest.isNetworkAvailable())
            fragmentUser.thereIsNoInternetConnection();
        else if(!regexTest.isValidFormat(modelUser.getEmail(), RegexTest.RegexType.EMAIL))
            fragmentUser.emailInvalidFormat();
        else if(!regexTest.isValidFormat(modelUser.getTelephone(), RegexTest.RegexType.TELEPHONE))
            fragmentUser.telephoneInvalidFormat();
        else if(modelUser.getUser().isEmpty())
            fragmentUser.userEmptyError();
        else if(modelUser.getEmail().isEmpty())
            fragmentUser.emailEmptyError();
        else if(modelUser.getPassword().isEmpty())
            fragmentUser.passwordEmptyError();
        else if(modelUser.getTelephone().isEmpty())
            fragmentUser.telefoneEmptyError();
        else if(!userController.checkUser(modelUser)) {

            if (userController.saveUserData(modelUser))
                fragmentUser.successfullyUserCreated();
            else
                fragmentUser.errorUserCreated();
        } else
            fragmentUser.userAlreadyExist();
    }

    @Override
    public void maskTelephoneNumber(EditText txtTelephone) {

        fragmentUser.setTxtTelephoneMask(MaskUtility.insert(txtTelephone));
    }
}
