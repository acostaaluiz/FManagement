package com.apptest.accenture.accentureinterview.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.activities.LoggedInActivity;
import com.apptest.accenture.accentureinterview.model.ModelLogin;
import com.apptest.accenture.accentureinterview.presenter.PresenterLogin;
import com.apptest.accenture.accentureinterview.view.Login;

/**
 * Created by fcost on 28/06/2018.
 */

public class FragmentLogin extends android.support.v4.app.Fragment implements Login.View {

    private Login.Presenter loginPresenter;
    private EditText txtUserValue;
    private EditText txtPasswordValue;
    private CheckBox checkBoxRememberLogin;
    private Button btnLogin;
    private ModelLogin modelLogin;

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
                boolean rememberPassword = false;

                if(checkBoxRememberLogin.isChecked())
                    rememberPassword = true;
                else
                    rememberPassword = false;

                modelLogin = new ModelLogin(user, password, rememberPassword);

                loginPresenter.isValidLogin(modelLogin);

            }
        });

        loginPresenter = new PresenterLogin(this, getActivity());

        return vw;
    }

    @Override
    public void userEmptyError() {

        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_user));
    }

    @Override
    public void passwordEmptyError() {
        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.empty_password));

    }

    @Override
    public void invalidUserLogin() {
        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.invalid_user_login));
    }

    @Override
    public void invalidPasswordLogin() {
        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.invalid_user_password));
    }

    @Override
    public void successfullyLoggedIn() {


        Intent myIntent = new Intent(getActivity(), LoggedInActivity.class);
        getActivity().startActivity(myIntent);



        //showDialog(getResources().getString(R.string.attention),
          //      getResources().getString(R.string.login_success));
    }

    private void showDialog(String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
