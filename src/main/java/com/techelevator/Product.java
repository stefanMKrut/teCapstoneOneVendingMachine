package com.techelevator;

public class Product {
    private String name;
    private String slotID;
    private Double price;
    private Integer quantity;

    public Product(String name, String slotID, Double price, Integer quantity) {
        this.name = name;
        this.slotID = slotID;
        this.price = price;
        this.quantity = quantity;

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
}
