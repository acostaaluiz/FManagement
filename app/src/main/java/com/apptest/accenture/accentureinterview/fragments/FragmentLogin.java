package com.apptest.accenture.accentureinterview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.activities.LoggedInActivity;
import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.apptest.accenture.accentureinterview.presenter.PresenterLogin;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.Login;

/**
 * Created by fcost on 28/06/2018.
 */

public class FragmentLogin extends Fragment implements Login.View {

    private Login.Presenter loginPresenter;
    private EditText txtUserValue;
    private EditText txtPasswordValue;
    private CheckBox checkBoxRememberLogin;
    private Button btnLogin;
    private ModelUser modelUser;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_login, container, false);

        txtUserValue = vw.findViewById(R.id.txtUserValue);
        txtPasswordValue = vw.findViewById(R.id.txtPasswordValue);
        checkBoxRememberLogin = vw.findViewById(R.id.checkBoxRememberLogin);
        btnLogin = vw.findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = txtUserValue.getText().toString();
                String password = txtPasswordValue.getText().toString();

                modelUser = new ModelUser(user, password);

                loginPresenter.isValidLogin(modelUser);

            }
        });

        loginPresenter = new PresenterLogin(this, getActivity());

        return vw;
    }

    @Override
    public void userEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_user));
    }

    @Override
    public void passwordEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_password));

    }

    @Override
    public void invalidUserLogin() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.user_already_exist));
    }

    @Override
    public void invalidPasswordLogin() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.invalid_user_password));
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public void successfullyLoggedIn() {

        Intent myIntent = new Intent(getActivity(), LoggedInActivity.class);
        getActivity().startActivity(myIntent);
    }

    @Override
    public void initLoadProgressBar() {

        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),
                getResources().getString(R.string.loading));
    }

    @Override
    public void finishLoadProgressBar() {

        progressDialog.dismiss();
    }

    public void callErrorMessageActivity(String errorType, String errorMessage) {

        Intent myIntent = new Intent(getActivity(), ErrorMessageActivity.class);

        myIntent.putExtra("errorType", errorType);
        myIntent.putExtra("errorMessage", errorMessage);

        startActivity(myIntent);
    }
}
