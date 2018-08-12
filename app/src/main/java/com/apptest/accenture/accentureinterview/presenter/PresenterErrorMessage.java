package com.apptest.accenture.accentureinterview.presenter;

import com.apptest.accenture.accentureinterview.view.ErrorMessage;

public class PresenterErrorMessage implements ErrorMessage.Presenter {

    private ErrorMessage.View fragmentErrorMessage;

    public PresenterErrorMessage(ErrorMessage.View fragmentErrorMessage){
        this.fragmentErrorMessage = fragmentErrorMessage;
    }

    @Override
    public void loadErrorMessage(String errorType, String errorMessage) {
        fragmentErrorMessage.setTxtErrorTypeValue(errorType);
        fragmentErrorMessage.setTxtErrorMessageValue(errorMessage);
    }
}
