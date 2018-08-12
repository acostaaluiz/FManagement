package com.apptest.accenture.accentureinterview.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.data.access.UserController;
import com.apptest.accenture.accentureinterview.data.api.DateRestAPI;
import com.apptest.accenture.accentureinterview.data.api.UserRestAPI;
import com.apptest.accenture.accentureinterview.model.ModelDate;
import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.apptest.accenture.accentureinterview.utility.MaskUtility;
import com.apptest.accenture.accentureinterview.utility.NetworkConnectionTest;
import com.apptest.accenture.accentureinterview.utility.RegexTest;
import com.apptest.accenture.accentureinterview.view.User;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fcost on 28/06/2018.
 */

public class PresenterUser implements User.Presenter {

    private UserRestAPI userRestAPI;
    private UserController userController;
    private User.View fragmentUser;
    private NetworkConnectionTest networkConnectionTest;
    private RegexTest regexTest;
    private ModelUser myModelUser;

    public PresenterUser(User.View fragmentUser, Context context){

        this.userController = new UserController(context);
        this.fragmentUser = fragmentUser;
        this.networkConnectionTest = new NetworkConnectionTest(context);
        this.userRestAPI = new UserRestAPI();
        this.regexTest = new RegexTest();
    }

    @Override
    public void isValidUser(ModelUser modelUser) {

        if(!userController.checkUser(modelUser))
            fragmentUser.userAlreadyExist();
    }

    @Override
    public void creationUserProcess(ModelUser modelUser) {

        this.myModelUser = modelUser;

        CreateUser createUser = new CreateUser();
        createUser.execute();
    }

    @Override
    public void maskTelephoneNumber(EditText txtTelephone) {
        fragmentUser.setTxtTelephoneMask(MaskUtility.insert(txtTelephone));
    }

    private class CreateUser extends
            AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            fragmentUser.initLoadProgressBar();
        }

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            try {

                if(!networkConnectionTest.isNetworkAvailable())
                    result = "WITHOUT_CONNECTION";
                else if(!regexTest.isValidFormat(myModelUser.getEmail(), RegexTest.RegexType.EMAIL))
                    result = "INVALID_FORMAT_EMAIL";
                else if(!regexTest.isValidFormat(myModelUser.getTelephone(), RegexTest.RegexType.TELEPHONE))
                    result = "INVALID_FORMAT_TELEPHONE";
                else if(myModelUser.getUser().isEmpty())
                    result = "EMPTY_USER";
                else if(myModelUser.getEmail().isEmpty())
                    result = "EMPTY_EMAIL";
                else if(myModelUser.getPassword().isEmpty())
                    result = "EMPTY_PASSWORD";
                else if(myModelUser.getTelephone().isEmpty())
                    result = "EMPTY_TELEPHONE";
                else{

                    myModelUser = userRestAPI.saveUser(myModelUser).execute().body();

                    result = myModelUser.getResponse();
                }

            } catch (IOException e) {
                return e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

           if(result.equals("WITHOUT_CONNECTION"))
               fragmentUser.thereIsNoInternetConnection();
           else if(result.equals("INVALID_FORMAT_EMAIL"))
               fragmentUser.emailInvalidFormat();
           else if(result.equals("INVALID_FORMAT_TELEPHONE"))
               fragmentUser.telephoneInvalidFormat();
           else if(result.equals("EMPTY_USER"))
               fragmentUser.userEmptyError();
           else if(result.equals("EMPTY_EMAIL"))
               fragmentUser.emailEmptyError();
           else if(result.equals("EMPTY_PASSWORD"))
               fragmentUser.passwordEmptyError();
           else if(result.equals("EMPTY_TELEPHONE"))
               fragmentUser.telefoneEmptyError();
           else if(result.equals("INVALID_USER"))
               fragmentUser.userAlreadyExist();
           else if(result.equals("OK"))
               fragmentUser.successfullyUserCreated();
           else
               fragmentUser.connectionServerError(result);

            fragmentUser.finishLoadProgressBar();
        }
    }
}
