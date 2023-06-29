package com.example.btl_nhom_7.Motor;

import java.io.Serializable;
import java.util.Objects;

public class Motor implements Serializable {
    public int id;
    private String name;
    private int price;
    private String image;
    private String details;
    private String details2;
    private String detailshead;
    private String detailsweight;

    public Motor(int id, String name, int price, String image, String details, String details2, String detailshead, String detailsweight) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.details = details;
        this.details2 = details2;
        this.detailshead = detailshead;
        this.detailsweight = detailsweight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motor motor = (Motor) o;
        return id == motor.id && price == motor.price && Objects.equals(name, motor.name) && Objects.equals(image, motor.image) && Objects.equals(details, motor.details) && Objects.equals(details2, motor.details2) && Objects.equals(detailshead, motor.detailshead) && Objects.equals(detailsweight, motor.detailsweight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, details, details2, detailshead, detailsweight);
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails2() {
        return details2;
    }

    public void setDetails2(String details2) {
        this.details2 = details2;
    }

    public String getDetailshead() {
        return detailshead;
    }

    public void setDetailshead(String detailshead) {
        this.detailshead = detailshead;
    }

    public String getDetailsweight() {
        return detailsweight;
    }

    public void setDetailsweight(String detailsweight) {
        this.detailsweight = detailsweight;
    }
}

