package com.personalProject.Lyrical.Models;

public class User {

    private String username;
    private String email;
    private String name;
    private String address;
    private String birthdate;

    public User() {}

    public User(String username, String email, String name, String address, String birthdate) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
