package com.techelevator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Product {
    private String name;
    private String slotID;
    private Double price;
    private String type;
    private Integer quantity;

    public Product(String slotID, String name, String price, String type) {
        this.slotID = slotID;
        this.name = name;
        String trimmed = price.trim();
        this.price = Double.parseDouble(trimmed);
        this.type = type;
        this.quantity = 5;

    }

    public String getName() {
        return name;
    }

    public String getSlotID() {
        return slotID;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType(){
        return type;
    }

    public String getProduct() {
        return slotID + ", " + name + ", " + type + ", " + price + ", " + quantity;
    }
}


