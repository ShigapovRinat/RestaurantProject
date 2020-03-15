package ru.itis.web_project.models;

import java.sql.Date;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String name;
    private Integer role;
    private Date date;
    private String phone_number;
    private String address;
    private String stringDate;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getDate() {
        return date;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setDate(Date date) {
        this.stringDate = "";
        String[] str = date.toString().split("-");

        for (int i = str.length - 1; i >= 0; i--) {
            if (i == 0) {
                this.stringDate = stringDate + str[i];
            } else {
                this.stringDate = this.stringDate + str[i] + ".";
            }
        }
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", date=" + date +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
