package com.apptest.accenture.accentureinterview.app;

import android.app.Application;

public class MyApplication extends Application {

    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public MyApplication getInstance(){
        return this;
    }
}
