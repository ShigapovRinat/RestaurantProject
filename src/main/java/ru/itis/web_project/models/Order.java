package ru.itis.web_project.models;

import java.sql.Date;

public class Order {
    private Integer id_table;
    private Integer id_menu;
    private Integer count_id_menu;
    private Date date;

    public Integer getId_table() {
        return id_table;
    }

    public void setId_table(Integer id_table) {
        this.id_table = id_table;
    }

    public Integer getId_menu() {
        return id_menu;
    }

    public void setId_menu(Integer id_menu) {
        this.id_menu = id_menu;
    }

    public Integer getCount_id_menu() {
        return count_id_menu;
    }

    public void setCount_id_menu(Integer count_id_menu) {
        this.count_id_menu = count_id_menu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
