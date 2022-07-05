package com.micropos.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private String id;
    private String name;
    private double price;
    private String image;

    public Product(String id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return getId() + "\t" + getName() + "\t" + getPrice();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
