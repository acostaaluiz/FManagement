package com.apptest.accenture.accentureinterview.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.widget.Button;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.ErrorMessageActivity;
import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.apptest.accenture.accentureinterview.presenter.PresenterUser;
import com.apptest.accenture.accentureinterview.utility.ProgressDialog;
import com.apptest.accenture.accentureinterview.view.User;

/**
 * Created by fcost on 28/06/2018.
 */

public class FragmentUser extends Fragment implements User.View{

    private User.Presenter userPresenter;
    private EditText txtUserValue;
    private EditText txtEmailValue;
    private EditText txtPasswordValue;
    private EditText txtTelephoneValue;
    private Button btnSignin;
    private ModelUser modelUser;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_signin, container, false);
        userPresenter = new PresenterUser(this, getActivity());

        txtUserValue = vw.findViewById(R.id.txtUserValue);
        txtPasswordValue = vw.findViewById(R.id.txtPasswordValue);
        txtEmailValue = vw.findViewById(R.id.txtEmailValue);
        txtTelephoneValue = vw.findViewById(R.id.txtTelephoneValue);
        btnSignin = vw.findViewById(R.id.btnSignin);
        userPresenter.maskTelephoneNumber(txtTelephoneValue);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = txtUserValue.getText().toString();
                String password = txtPasswordValue.getText().toString();
                String email = txtEmailValue.getText().toString();
                String telephone = txtTelephoneValue.getText().toString();

                modelUser = new ModelUser(user, password, email, telephone);

                userPresenter.creationUserProcess(modelUser);
            }
        });

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
    public void emailEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_email));
    }

    @Override
    public void telefoneEmptyError() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_telephone));

    }

    @Override
    public void userAlreadyExist() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.user_already_exist));
    }

    @Override
    public void successfullyUserCreated() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.user_created_success));
    }

    @Override
    public void emailInvalidFormat() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.invalid_email));
    }

    @Override
    public void telephoneInvalidFormat() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.invalid_phone_number));
    }

    @Override
    public void thereIsNoInternetConnection() {

        callErrorMessageActivity(
                getResources().getString(R.string.attention),
                getResources().getString(R.string.no_internet_connection));
    }

    @Override
    public void connectionServerError(String error) {

        callErrorMessageActivity(
                getResources().getString(R.string.error),
                error);
    }

    @Override
    public void setTxtTelephoneMask(TextWatcher textWatcher) {

        this.txtTelephoneValue.addTextChangedListener(textWatcher);
    }

    @Override
    public void initLoadProgressBar() {

        progressDialog = new ProgressDialog();
        progressDialog.show(getActivity().getSupportFragmentManager(),
                getResources().getString(R.string.creating));

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
