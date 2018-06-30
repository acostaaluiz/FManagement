package com.apptest.accenture.accentureinterview.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.model.ContactModel;
import com.apptest.accenture.accentureinterview.model.InvestmentModel;
import com.apptest.accenture.accentureinterview.presenter.JSONData;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by fcost on 26/06/2018.
 */

public class FragmentContact extends android.support.v4.app.Fragment {

    private String contactName;
    private String email;
    private String telephone;
    private boolean emailRegister;
    private View vw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        vw = inflater.inflate(R.layout.fragment_contact, container, false);
        Button btnSend = vw.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtCompleteName = vw.findViewById(R.id.txtCompleteNameValue);
                TextView txtEmail = vw.findViewById(R.id.txtEmailValue);
                TextView txtTelephone = vw.findViewById(R.id.txtTelephoneValue);
                CheckBox checkBoxRegister = vw.findViewById(R.id.checkBoxRegister);

                contactName = txtCompleteName.getText().toString();
                email = txtEmail.getText().toString();
                telephone = txtTelephone.getText().toString();
                emailRegister = false;
                int telephoneLenth = telephone.length();

                if(telephoneLenth == 11) {
                    if (!telephone.startsWith("(")) {
                        telephone = "(" + telephone.substring(0, 2) + ") " + telephone.substring(2, 7) + "-" + telephone.substring(7, 11);
                        txtTelephone.setText(telephone);
                    }
                } else if(telephoneLenth == 10){

                    if (!telephone.startsWith("(")) {
                        telephone = "(" + telephone.substring(0, 2) + ") " + telephone.substring(2, 6) + "-" + telephone.substring(6, 10);
                        txtTelephone.setText(telephone);
                    }
                }

                if(checkBoxRegister.isChecked())
                    emailRegister = true;
                else
                    emailRegister = false;

                SendContact sendContact = new SendContact();
                sendContact.execute();
            }
        });

        setTextViewCustomFont(vw);

        return vw;
    }

    public void setTextViewCustomFont(View view){

        TextView txtCompleteName = view.findViewById(R.id.txtCompleteName);
        TextView txtEmail = view.findViewById(R.id.txtEmail);
        TextView txtTelephone = view.findViewById(R.id.txtTelephone);
        CheckBox checkBoxRegister = view.findViewById(R.id.checkBoxRegister);

        Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "DINNeuzeitGroteskStd-Light.ttf");

        txtCompleteName.setTypeface(mTypeface);
        txtEmail.setTypeface(mTypeface);
        txtTelephone.setTypeface(mTypeface);
        checkBoxRegister.setTypeface(mTypeface);

        txtCompleteName.setTextColor(Color.parseColor("#AFAFAF"));
        txtEmail.setTextColor(Color.parseColor("#AFAFAF"));
        txtTelephone.setTextColor(Color.parseColor("#AFAFAF"));
        checkBoxRegister.setTextColor(Color.parseColor("#AFAFAF"));
    }

    public class SendContact extends AsyncTask<String, String, String> {

        private ProgressDialog pdia;
        ContactModel contactModel;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdia = ProgressDialog.show(getActivity(),
                    getResources().getString(R.string.wait), getResources()
                            .getString(R.string.sendingMessage));
            pdia.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            contactModel = new ContactModel(contactName, email, telephone, emailRegister);

            if(!contactModel.isValidContactName())
                return "INVALID_CONTACT_NAME";
            else if(!contactModel.isValidTelephoneNumber())
                return "INVALID_PHONE_NUMBER";
            else if(!contactModel.isValidEmail())
                return "INVALID_EMAIL";

            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(pdia.isShowing())
                pdia.dismiss();

            if(result.equals("OK"))
                showDialog(getResources().getString(R.string.thankyou),
                            getResources().getString(R.string.message_success));
            else if(result.equals("INVALID_CONTACT_NAME"))
                showDialog(getResources().getString(R.string.attention),
                        getResources().getString(R.string.invalid_contact_name));
            else if(result.equals("INVALID_PHONE_NUMBER"))
                showDialog(getResources().getString(R.string.attention),
                        getResources().getString(R.string.invalid_phone_number));
            else if(result.equals("INVALID_EMAIL"))
                showDialog(getResources().getString(R.string.attention),
                        getResources().getString(R.string.invalid_email));
        }
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
