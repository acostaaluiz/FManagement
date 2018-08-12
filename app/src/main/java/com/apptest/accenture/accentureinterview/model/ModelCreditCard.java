package com.apptest.accenture.accentureinterview.model;

public class ModelCreditCard {

    private String creditCard;
    private String bank;
    private String creditCardFlag;
    private String creditCardLimit;
    private String creditCardEndDate;
    private String creditCardPrice;
    private String response;

    public ModelCreditCard(String creditCard, String bank, String creditCardFlag, String creditCardLimit,
                                  String creditCardEndDate){

        this.creditCard = creditCard;
        this.bank = bank;
        this.creditCardFlag = creditCardFlag;
        this.creditCardLimit = creditCardLimit;
        this.creditCardEndDate = creditCardEndDate;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCreditCardFlag() {
        return creditCardFlag;
    }

    public void setCreditCardFlag(String creditCardFlag) {
        this.creditCardFlag = creditCardFlag;
    }

    public String getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(String creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public String getCreditCardEndDate() {
        return creditCardEndDate;
    }

    public void setCreditCardEndDate(String creditCardEndDate) {
        this.creditCardEndDate = creditCardEndDate;
    }

    public String getCreditCardPrice() {
        return creditCardPrice;
    }

    public void setCreditCardPrice(String creditCardPrice) {
        this.creditCardPrice = creditCardPrice;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
