package ru.itis.web_project.models;

public class DishCategory {
    private Integer id;
    private String categ_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateg_name() {
        return categ_name;
    }

    public void setCateg_name(String categ_name) {
        this.categ_name = categ_name;
    }
}
