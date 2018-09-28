package com.apptest.accenture.accentureinterview.model;

public class ModelBuget {

    private String buget;
    private String bugetName;
    private ModelCustomer customer;
    private ModelBugetState bugetState;
    private String bugetDate;
    private String bugetNotes;
    private String bugetPrice;

    public ModelBuget(String buget, String bugetName, ModelCustomer customer, ModelBugetState bugetState, String bugetDate, String bugetNotes, String bugetPrice){

        this. buget = buget;
        this.bugetName = bugetName;
        this.customer = customer;
        this.bugetState = bugetState;
        this.bugetDate = bugetDate;
        this.bugetNotes = bugetNotes;
        this.bugetPrice = bugetPrice;
    }

    public String getBuget() {
        return buget;
    }

    public void setBuget(String buget) {
        this.buget = buget;
    }

    public String getBugetName() {
        return bugetName;
    }

    public void setBugetName(String bugetName) {
        this.bugetName = bugetName;
    }

    public ModelCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(ModelCustomer customer) {
        this.customer = customer;
    }

    public ModelBugetState getBugetState() {
        return bugetState;
    }

    public void setBugetState(ModelBugetState bugetState) {
        this.bugetState = bugetState;
    }

    public String getBugetDate() {
        return bugetDate;
    }

    public void setBugetDate(String bugetDate) {
        this.bugetDate = bugetDate;
    }

    public String getBugetNotes() {
        return bugetNotes;
    }

    public void setBugetNotes(String bugetNotes) {
        this.bugetNotes = bugetNotes;
    }

    public String getBugetPrice() {
        return bugetPrice;
    }

    public void setBugetPrice(String bugetPrice) {
        this.bugetPrice = bugetPrice;
    }
}
