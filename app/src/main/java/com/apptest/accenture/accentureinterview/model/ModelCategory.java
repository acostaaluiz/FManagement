package com.apptest.accenture.accentureinterview.model;

/**
 * Created by fcost on 30/06/2018.
 */

public class ModelCategory {

    private String category;
    private String response;

    public ModelCategory(String category){
        this.category = category;
    }

    public ModelCategory(String category, String response){

        this.category = category;
        this.response = response;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResponse(){
        return this.response;
    }

    public void setResponse(String response){
        this.response = response;
    }
}
