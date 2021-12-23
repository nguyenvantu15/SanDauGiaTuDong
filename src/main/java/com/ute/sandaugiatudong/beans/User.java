package com.ute.sandaugiatudong.beans;

import java.time.LocalDate;

public class User {
    private int id,permission;
    private String username,password,email,phone,name;
    private LocalDate dob;

    public int getId() {
        return id;
    }

    public int getPermission() {
        return permission;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public User(int id, int permission, String username, String password, String email, String phone, String name, LocalDate dob) {
        this.id = id;
        this.permission = permission;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.dob = dob;
    }

    public User(int id, String email, String name, String phone, LocalDate dob,String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.password = password;
    }

    public User(int id, int permission, String username, String email, String phone, String name) {
        this.id = id;
        this.permission = permission;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }


    public User() {
    }

}
