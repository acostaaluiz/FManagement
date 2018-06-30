package com.apptest.accenture.accentureinterview.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.Button;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ModelUser;
import com.apptest.accenture.accentureinterview.presenter.PresenterUser;
import com.apptest.accenture.accentureinterview.utility.MaskUtility;
import com.apptest.accenture.accentureinterview.view.User;

/**
 * Created by fcost on 28/06/2018.
 */

public class FragmentUser extends android.support.v4.app.Fragment implements User.View{

    private User.Presenter userPresenter;
    private EditText txtUserValue;
    private EditText txtEmailValue;
    private EditText txtPasswordValue;
    private EditText txtTelephoneValue;
    private Button btnSignin;
    private ModelUser modelUser;

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
                //userPresenter.isValidUser(modelUser);
                userPresenter.createUser(modelUser);
            }
        });

        return vw;
    }

    @Override
    public void userEmptyError() {
        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.empty_user));
    }

    @Override
    public void passwordEmptyError() {
        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.empty_password));
    }

    @Override
    public void emailEmptyError() {
        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.empty_email));
    }

    @Override
    public void telefoneEmptyError() {

        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.empty_telephone));

    }

    @Override
    public void userAlreadyExist() {
        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.user_already_exist));
    }

    @Override
    public void successfullyUserCreated() {

        showDialog(getResources().getString(R.string.success),
                getResources().getString(R.string.user_created_success));
    }

    @Override
    public void errorUserCreated() {

        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.user_created_error));
    }

    @Override
    public void emailInvalidFormat() {
        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.invalid_email));
    }

    @Override
    public void telephoneInvalidFormat() {
        showDialog(getResources().getString(R.string.error),
                getResources().getString(R.string.invalid_phone_number));
    }

    @Override
    public void thereIsNoInternetConnection() {

        showDialog(getResources().getString(R.string.attention),
                getResources().getString(R.string.no_internet_connection));
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

    @Override
    public void setTxtTelephone(String txtTelephoneValue) {
        this.txtTelephoneValue.setText(txtTelephoneValue);
    }

    @Override
    public void setTxtTelephoneMask(TextWatcher textWatcher) {
        this.txtTelephoneValue.addTextChangedListener(textWatcher);
    }
}
