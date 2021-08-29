package com.example.dostavahrane.api.model;

public class DishModel {
    private String dish_id, name, description, category, restaurant_id;
    private double price;
    private String[] order;

    public DishModel() { }

    public DishModel(String dish_id, String name, String description, String category, String restaurant_id, double price, String[] order) {
        this.dish_id = dish_id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.restaurant_id = restaurant_id;
        this.price = price;
        this.order = order;
    }

    public String getDish_id() {
        return dish_id;
    }

    public void setDish_id(String dish_id) {
        this.dish_id = dish_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }
}
