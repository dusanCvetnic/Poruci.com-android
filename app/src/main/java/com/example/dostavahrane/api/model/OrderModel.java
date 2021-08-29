package com.example.dostavahrane.api.model;

public class OrderModel {
    private String order_id, user_id;
    private double totalPrice;
    private int[] quantity;
    private String[] dish;

    public OrderModel() { }

    public OrderModel(String order_id, String user_id, double totalPrice, int[] quantity, String[] dish) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.dish = dish;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public String[] getDish() {
        return dish;
    }

    public void setDish(String[] dish) {
        this.dish = dish;
    }
}
