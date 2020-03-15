package ru.itis.web_project.models;

import java.sql.Date;

public class BookReview {
    private Integer id;
    private Integer id_user;
    private String username;
    private String message;
    private Integer raiting;
    private Date date;
    private String stringDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRaiting() {
        return raiting;
    }

    public void setRaiting(Integer raiting) {
        this.raiting = raiting;
    }

    public Date getDate() {
        return date;
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
}
