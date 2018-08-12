package com.apptest.accenture.accentureinterview.model;

public class ModelDate {

    private String dateType;
    private String date;

    public ModelDate(String dateType, String date){
        this.dateType = dateType;
        this.date = date;
    }

    public String getDateType(){
        return this.dateType;
    }

    public void setDateType(String dateType){
        this.dateType = dateType;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
