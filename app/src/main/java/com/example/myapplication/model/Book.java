package com.example.myapplication.model;

public class Book {
    private int id;
    private String name;
    private String author;
    private int genreId;

    public Book(String name, String author, int genreId) {
        this.name = name;
        this.author = author;
        this.genreId = genreId;
    }

    public Book(int id, String name, String author, int genreId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genreId = genreId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
