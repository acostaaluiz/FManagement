package com.apptest.accenture.accentureinterview.model;

public class ModelBugetStateWorkflow {

    private String bugetStateWorkflow;
    private String bugetStateWorkflowNextState;
    private String bugetState;

    public ModelBugetStateWorkflow(String bugetStateWorkflow, String bugetStateWorkflowNextState, String bugetState){
        this.bugetStateWorkflow = bugetStateWorkflow;
        this.bugetStateWorkflowNextState = bugetStateWorkflowNextState;
        this.bugetState = bugetState;
    }

    public String getBugetStateWorkflow() {
        return bugetStateWorkflow;
    }

    public void setBugetStateWorkflow(String bugetStateWorkflow) {
        this.bugetStateWorkflow = bugetStateWorkflow;
    }

    public String getBugetStateWorkflowNextState() {
        return bugetStateWorkflowNextState;
    }

    public void setBugetStateWorkflowNextState(String bugetStateWorkflowNextState) {
        this.bugetStateWorkflowNextState = bugetStateWorkflowNextState;
    }

    public String getBugetState() {
        return bugetState;
    }

    public void setBugetState(String bugetState) {
        this.bugetState = bugetState;
    }
}
