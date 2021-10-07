package com.techelevator;

public class Item {
    private String code;
    private String type;
    private String name;
    private double price;
    private int quantity;

    public Item(String code, String name, double price, String type ) {
        this.code = code;
        this.type = type;
        this.name = name;
        this.price = price;
        quantity = 5;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
