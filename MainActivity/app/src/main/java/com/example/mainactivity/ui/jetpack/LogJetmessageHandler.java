package com.example.mainactivity.ui.jetpack;

public class LogJetmessageHandler {

    LogJetmessageHandler() {

    }

    public void onSaveClick(User user) {
        System.out.println("user: " + user.firstName + user.lastName);
    }
}
