package com.apptest.accenture.accentureinterview.model;

public class ModelItem {

    private String item;
    private String itemName;
    private String itemPrice;
    private String itemStoredQuantity;
    private String creationData;
    private String lastUpdateData;
    private String meterUnit;
    private String response;

    public ModelItem (String item, String itemName, String itemPrice, String itemStoredQuantity, String meterUnit){

        this.item = item;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStoredQuantity = itemStoredQuantity;
        this.meterUnit = meterUnit;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemStoredQuantity() {
        return itemStoredQuantity;
    }

    public void setItemStoredQuantity(String itemStoredQuantity) {
        this.itemStoredQuantity = itemStoredQuantity;
    }

    public String getMeterUnit() {
        return meterUnit;
    }

    public void setMeterUnit(String meterUnit) {
        this.meterUnit = meterUnit;
    }

    public String getCreationData() {
        return creationData;
    }

    public void setCreationData(String creationData) {
        this.creationData = creationData;
    }

    public String getLastUpdateData() {
        return lastUpdateData;
    }

    public void setLastUpdateData(String lastUpdateData) {
        this.lastUpdateData = lastUpdateData;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
