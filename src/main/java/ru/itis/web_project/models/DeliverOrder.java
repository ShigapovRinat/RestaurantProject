package ru.itis.web_project.models;

import java.sql.Date;

public class DeliverOrder {
    private Integer id;
    private Integer id_user;
    private Date date;
    private Integer id_menu;
    private Integer count_id_menu;
    private String stringDate;
    private String nameDish;
    private String userAddress;

    public String getUserAddress() {
        return userAddress;
    }



    public String getNameDish() {
        return nameDish;
    }



    public Integer getCount_id_menu() {
        return count_id_menu;
    }



    public Integer getId() {
        return id;
    }



    public Integer getId_user() {
        return id_user;
    }


    public Date getDate() {
        return date;
    }


    public String getStringDate() {
        return stringDate;
    }



    public Integer getId_menu() {
        return id_menu;
    }

    public DeliverOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public DeliverOrder setId_user(Integer id_user) {
        this.id_user = id_user;
        return this;
    }

    public DeliverOrder setDate(Date date) {
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
        return this;
    }

    public DeliverOrder setId_menu(Integer id_menu) {
        this.id_menu = id_menu;
        return this;
    }

    public DeliverOrder setCount_id_menu(Integer count_id_menu) {
        this.count_id_menu = count_id_menu;
        return this;
    }

    public DeliverOrder setStringDate(String stringDate) {
        this.stringDate = stringDate;
        return this;
    }

    public DeliverOrder setNameDish(String nameDish) {
        this.nameDish = nameDish;
        return this;
    }

    public DeliverOrder setUserAddress(String userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    @Override
    public String toString() {
        return "DeliverOrder{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date=" + date +
                ", id_menu=" + id_menu +
                ", count_id_menu=" + count_id_menu +
                ", stringDate='" + stringDate + '\'' +
                ", nameDish='" + nameDish + '\'' +
                '}';
    }
}
