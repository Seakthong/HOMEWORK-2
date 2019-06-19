package com.mvc.article.repository.model;

import javax.validation.constraints.NotEmpty;

public class Category {
    private int id;
    @NotEmpty
    private String name;
    private int status;

    public Category() {
    }
    public Category(int id) {
        this.id = id;
    }

    public Category(String name, int status){
        this.name = name;
        this.status = status;
    }

    public Category(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
