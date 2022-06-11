package com.example.demo123.model;

public class BtcBidsAsksDTO {

    private double price;
    private double quantity;

    public BtcBidsAsksDTO() {
    }

    public double getPrice() {
        return price;
    }

    public BtcBidsAsksDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getQuantity() {
        return quantity;
    }

    public BtcBidsAsksDTO setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public String toString() {
        return "[ "+ this.getPrice() + ", " + this.getQuantity() + "]";
    }
}
