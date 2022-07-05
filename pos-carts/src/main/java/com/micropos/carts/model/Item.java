package com.micropos.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    private Product product;
    private int quantity;

    public Item() {
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return product.toString() +"\t" + quantity;
    }

    public void addAmount(int n) { quantity += n;}

    public int getAmount() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
