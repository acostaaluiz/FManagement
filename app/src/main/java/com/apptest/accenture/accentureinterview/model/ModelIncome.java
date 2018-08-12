package com.apptest.accenture.accentureinterview.model;

/**
 * Created by fcost on 02/07/2018.
 */

public class ModelIncome {

    public enum IncomeFrequency {
        DAY, WEEK, MONTH, YEAR;
    }

    private String income;
    private String incomeFrequency;
    private String categoryIncome;
    private String incomeDate;
    private String incomeToDate;
    private String price;
    private String response;

    public ModelIncome(String income, String incomeFrequency, String incomeDate, String incomeToDate, String price, String categoryIncome){

        this.income = income;
        this.incomeDate = incomeDate;
        this. incomeToDate = incomeToDate;
        this.price = price;
        this.incomeFrequency = incomeFrequency;
        this.categoryIncome = categoryIncome;
    }

    public ModelIncome(String income, String incomeFrequency, String incomeToDate, String price){

        this.income = income;
        this. incomeToDate = incomeToDate;
        this.price = price;
        this.incomeFrequency = incomeFrequency;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getIncomeFrequency() {
        return incomeFrequency;
    }

    public void setIncomeFrequency(String incomeFrequency) {
        this.incomeFrequency = incomeFrequency;
    }
    public String getIncomeToDate() {
        return incomeToDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public void setIncomeToDate(String incomeToDate) {
        this.incomeToDate = incomeToDate;
    }

    public String getCategoryIncome() {
        return categoryIncome;
    }

    public void setCategoryIncome(String categoryIncome) {
        this.categoryIncome = categoryIncome;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
