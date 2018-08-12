package com.apptest.accenture.accentureinterview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apptest.accenture.accentureinterview.R;
import com.apptest.accenture.accentureinterview.presenter.PresenterErrorMessage;
import com.apptest.accenture.accentureinterview.view.ErrorMessage;

public class ErrorMessageActivity extends AppCompatActivity implements ErrorMessage.View {

    private TextView txtErrorTypeValue;
    private TextView txtErrorMessageValue;
    private Button btnOk;
    private ErrorMessage.Presenter errorMessagePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_message);

        Bundle extras = getIntent().getExtras();

        txtErrorTypeValue = (TextView) findViewById(R.id.txtErrorTypeValue);
        txtErrorMessageValue = (TextView) findViewById(R.id.txtErrorMessage);
        btnOk = (Button) findViewById(R.id.btnOk);

        String errorType = extras.getString("errorType");
        String errorMessage = extras.getString("errorMessage");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        errorMessagePresenter = new PresenterErrorMessage(this);
        errorMessagePresenter.loadErrorMessage(errorType, errorMessage);

    }

    @Override
    public void setTxtErrorTypeValue(String value) {
        txtErrorTypeValue.setText(value);
    }

    @Override
    public void setTxtErrorMessageValue(String value) {
        txtErrorMessageValue.setText(value);
    }
}
