package com.apptest.accenture;

import junit.framework.TestCase;

/**
 * Created by fcost on 26/06/2018.
 */

public class ContactTest extends TestCase {

    public void testCompleteNameGreaterThanZero(){
        ContactModel contactModel = new ContactModel("FLAVIO", "email@email.com", "(11) 3761-5212", true);
        boolean testResult = contactModel.isValidContactName();
        assertEquals(true, testResult);
    }

    public void testCompleteNameLessThanZero(){
        ContactModel contactModel = new ContactModel("", "email@email.com", "(11) 3761-5212", true);
        boolean testResult = contactModel.isValidContactName();
        assertEquals(false, testResult);
    }

    public void testInvalidEmail(){
        ContactModel contactModel = new ContactModel("", "email.email.com", "(11) 3761-5212", true);
        boolean testResult = contactModel.isValidEmail();
        assertEquals(false, testResult);
    }

    public void testValidEmail(){
        ContactModel contactModel = new ContactModel("", "email@email.com", "(11) 3761-5212", true);
        boolean testResult = contactModel.isValidEmail();
        assertEquals(true, testResult);
    }

    public void testInvalidTelephoneNumber(){
        ContactModel contactModel = new ContactModel("", "email@email.com", "(11) 371-5212", true);
        boolean testResult = contactModel.isValidTelephoneNumber();
        assertEquals(false, testResult);
    }

    public void testValidTelephoneNumber(){
        ContactModel contactModel = new ContactModel("", "email@email.com", "(11) 3761-5212", true);
        boolean testResult = contactModel.isValidTelephoneNumber();
        assertEquals(true, testResult);
    }

    public void testInvalidCellePhoneNumber(){
        ContactModel contactModel = new ContactModel("", "email@email.com", "(11) 99761-521", true);
        boolean testResult = contactModel.isValidTelephoneNumber();
        assertEquals(false, testResult);
    }

    public void testValidCellePhoneNumber(){
        ContactModel contactModel = new ContactModel("", "email@email.com", "(11) 99761-5211", true);
        boolean testResult = contactModel.isValidTelephoneNumber();
        assertEquals(true, testResult);
    }
}
