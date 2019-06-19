package com.mvc.article.repository.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;

public class Article {
    int id;
    @NotEmpty
    String title;
    @NotEmpty
    String author;
    @NotEmpty
    String description;
    String thumbnail="default.png";
    int status;
    int category_id;

//    @Autowired
    private Category category;

    public  Article(){}
    public Article(int id){
        this(id, "", "", "", "",1,0);
    }

    public Article(int id, String title, String author, String description, String thumbnail, int status, int category_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.thumbnail = thumbnail;
        this.status = status;
        this.category_id = category_id;
    }

    public Article(String title, String author, String description, String thumbnail, int status, int category_id) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.thumbnail = thumbnail;
        this.status = status;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", status=" + status +
                ", category_id=" + category_id +
                ", category_id=" + category.getId() +
                ", category_id=" + category.getName()+
                '}';
    }
}
