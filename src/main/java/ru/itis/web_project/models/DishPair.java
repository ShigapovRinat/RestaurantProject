package ru.itis.web_project.models;

public class DishPair {
    private Integer id;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public DishPair setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public DishPair setCount(Integer count) {
        this.count = count;
        return this;
    }
}
