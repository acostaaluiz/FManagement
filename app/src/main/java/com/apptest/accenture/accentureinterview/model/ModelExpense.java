package com.apptest.accenture.accentureinterview.model;

/**
 * Created by fcost on 01/07/2018.
 */

public class ModelExpense {

    public enum ExpenseFrequency {
        DAY, WEEK, MONTH, YEAR
    }

    private String expense;
    private String expenseDate;
    private String hasCreditCard;
    private String creditCard;
    private String category;
    private String price;
    private String expenseFrequency;
    private String response;

    public ModelExpense(String expense, String expenseDate, String category, String hasCreditCard, String price, String expenseFrequency){

        this.expense = expense;
        this.expenseDate = expenseDate;
        this.hasCreditCard = hasCreditCard;
        this.category = category;
        this.price = price;
        this.expenseFrequency = expenseFrequency;
    }

    public ModelExpense(){

    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpenseFrequency() {
        return expenseFrequency;
    }

    public void setExpenseFrequency(ExpenseFrequency expenseFrequency) {

        if(expenseFrequency == ExpenseFrequency.DAY)
            this.expenseFrequency = "DAY";
        else if(expenseFrequency == ExpenseFrequency.WEEK)
            this.expenseFrequency = "WEEK";
        else if(expenseFrequency == ExpenseFrequency.MONTH)
            this.expenseFrequency = "MONTH";
        else if(expenseFrequency == ExpenseFrequency.YEAR)
            this.expenseFrequency = "YEAR";
    }

    public void setExpenseFrequency(String expenseFrequency) {
        this.expenseFrequency = expenseFrequency;
    }

    public String getResponse(){
        return this.response;
    }

    public void setResponse(String response){
        this.response = response;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getHasCreditCard() {
        return hasCreditCard;
    }

    public void setHasCreditCard(String hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }
}
