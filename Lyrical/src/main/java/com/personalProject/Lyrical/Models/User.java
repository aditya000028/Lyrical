package com.personalProject.Lyrical.Models;

public class User {

    private long id;
    private String email;
    private String name;
    private String address;
    private String birthdate;

    public User() {}

    public User(long id, String email, String name, String address, String birthdate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}