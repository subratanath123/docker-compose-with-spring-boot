package com.example.FirstDockerappweb.controller;

import java.io.Serializable;

public class Product implements Serializable {

    private long id;
    private int price;
    private String name;

    public Product(long id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
