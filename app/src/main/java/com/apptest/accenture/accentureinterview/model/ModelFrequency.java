package com.apptest.accenture.accentureinterview.model;

public class ModelFrequency {

    private String position;
    private String frequency;

    public ModelFrequency(String position, String frequency){
        this.position = position;
        this.frequency = frequency;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
