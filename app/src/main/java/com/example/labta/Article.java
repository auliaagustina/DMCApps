package com.example.labta;

public class Article {
    private String title;
    private String date;
    private int imageResource;

    public Article(String title, String date, int imageResource) {
        this.title = title;
        this.date = date;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getImageResource() {
        return imageResource;
    }
}
