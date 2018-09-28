package com.apptest.accenture.accentureinterview.model;

public class ModelBugetState {

    private String bugetState;
    private String bugetStateName;

    public ModelBugetState(String bugetState, String bugetStateName){

        this.bugetState = bugetState;
        this.bugetStateName = bugetStateName;
    }

    public String getBugetState() {
        return bugetState;
    }

    public void setBugetState(String bugetState) {
        this.bugetState = bugetState;
    }

    public String getBugetStateName() {
        return bugetStateName;
    }

    public void setBugetStateName(String bugetStateName) {
        this.bugetStateName = bugetStateName;
    }
}
