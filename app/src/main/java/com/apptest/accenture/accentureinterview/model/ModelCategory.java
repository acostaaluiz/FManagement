package com.apptest.accenture.accentureinterview.model;

/**
 * Created by fcost on 30/06/2018.
 */

public class ModelCategory {

    private String category;
    private String categoryInputDate;

    public ModelCategory(String category, String categoryInputDate){
        this.category = category;
        this.categoryInputDate = categoryInputDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryInputDate() {
        return categoryInputDate;
    }

    public void setCategoryInputDate(String categoryInputDate) {
        this.categoryInputDate = categoryInputDate;
    }
}
