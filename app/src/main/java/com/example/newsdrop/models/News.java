package com.example.newsdrop.models;

import java.util.List;

public class News {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public int getTotalResults() {
        return totalResults;
    }

    public String getStatus() {
        return status;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
