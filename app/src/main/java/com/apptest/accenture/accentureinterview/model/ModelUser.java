package com.apptest.accenture.accentureinterview.model;

/**
 * Created by fcost on 28/06/2018.
 */

public class ModelUser {

    private String user;
    private String password;
    private String email;
    private String telephone;
    private String lastUpdateData;

    public ModelUser(String user, String password, String email, String telephone){

        this.user = user;
        this.password = password;
        this.email = email;
        this.telephone = telephone;

    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLastUpdateData() {
        return lastUpdateData;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLastUpdateData(String lastUpdateData) {
        this.lastUpdateData = lastUpdateData;
    }
}
