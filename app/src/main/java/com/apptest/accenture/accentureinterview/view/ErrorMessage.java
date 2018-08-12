package com.apptest.accenture.accentureinterview.view;

public interface ErrorMessage {

    interface View{
        void setTxtErrorTypeValue(String value);
        void setTxtErrorMessageValue(String value);
    }

    interface Presenter{
        void loadErrorMessage(String errorType, String errorMessage);
    }
}
