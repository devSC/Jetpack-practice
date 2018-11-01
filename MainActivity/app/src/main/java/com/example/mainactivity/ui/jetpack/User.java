package com.example.mainactivity.ui.jetpack;

public class User {
    String firstName;
    String lastName;

    User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
