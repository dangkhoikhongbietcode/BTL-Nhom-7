package com.example.btl_nhom_7.Motor;

import java.io.Serializable;

public class Motor implements Serializable {
    private String name;
    private int price;
    private String details;
    public Motor(String name, int price, String s) {
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDetails() {return details;}
}

