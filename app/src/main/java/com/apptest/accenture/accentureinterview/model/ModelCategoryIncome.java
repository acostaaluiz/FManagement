package com.apptest.accenture.accentureinterview.model;

public class ModelCategoryIncome {

    private String categoryIncome;
    private String response;

    public ModelCategoryIncome(String categoryIncome){
        this.categoryIncome = categoryIncome;
    }

    public ModelCategoryIncome(String categoryIncome, String response){
        this.categoryIncome = categoryIncome;
        this.response = response;
    }


    public String getCategoryIncome(){
        return this.categoryIncome;
    }

    public void setCategoryIncome(String categoryIncome){
        this.categoryIncome = categoryIncome;
    }

    public String getResponse(){
        return this.response;
    }

    public void setResponse(String response){

        this.response = response;
    }
}
