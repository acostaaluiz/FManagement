package com.apptest.accenture.accentureinterview.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fcost on 26/06/2018.
 */

public class ContactModel {

    private String contactName;
    private String email;
    private String telephone;
    private boolean emailRegister;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String TEL_PATTERN = "^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public ContactModel(String contactName, String email, String telephone, boolean emailRegister){
        this.contactName = contactName;
        this.email = email;
        this.telephone = telephone;
        this.emailRegister = emailRegister;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public boolean isEmailRegister() {
        return emailRegister;
    }

    public boolean isValidTelephoneNumber() {
        return this.telephone.matches(TEL_PATTERN);
    }

    public boolean isValidEmail(){
        Matcher  matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

    public boolean isValidContactName(){
        if(this.contactName.length() > 0)
            return true;
        else
            return false;
    }
}
