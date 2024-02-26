package com.example.profocusedtiming;

public class User {

    private String firstname;
    private String surname;
    private String username;
    private String email;

    public User() {
    }

    public User(String firstname, String surname, String username, String email) {
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
