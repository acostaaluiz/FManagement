package com.apptest.accenture.accentureinterview.model;

public class ModelMeterUnit {

    private String meterUnit;
    private String meterUnitName;

    public ModelMeterUnit(String meterUnit, String meterUnitName){
        this.meterUnit = meterUnit;
        this.meterUnitName = meterUnitName;
    }

    public String getMeterUnit() {
        return meterUnit;
    }

    public void setMeterUnit(String meterUnit) {
        this.meterUnit = meterUnit;
    }

    public String getMeterUnitName() {
        return meterUnitName;
    }

    public void setMeterUnitName(String meterUnitName) {
        this.meterUnitName = meterUnitName;
    }
}
