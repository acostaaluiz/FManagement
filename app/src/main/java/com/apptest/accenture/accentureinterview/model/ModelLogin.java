package com.apptest.accenture.accentureinterview.model;

/**
 * Created by fcost on 28/06/2018.
 */

public class ModelLogin {

    private String user;
    private String password;
    private boolean rememberPassword;
    private String lastAccess;

    public ModelLogin(String user, String password, boolean rememberPassword){

        this.user = user;
        this.password = password;
        this.rememberPassword = rememberPassword;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberPassword() {
        return rememberPassword;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberPassword(boolean rememberPassword) {
        this.rememberPassword = rememberPassword;
    }

    public String getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(String lastAccess){
        this.lastAccess = lastAccess;

    }
}
