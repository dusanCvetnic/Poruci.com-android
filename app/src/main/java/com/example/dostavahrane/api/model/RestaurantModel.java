package com.example.dostavahrane.api.model;

import java.util.Arrays;

public class RestaurantModel {
    private String _id, name, address, email, phone, manager;
    private String[] dish, order;

    public RestaurantModel() { }

    public RestaurantModel(String _id, String name, String address, String email, String phone, String manager, String[] dish, String[] order) {
        this._id = _id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.manager = manager;
        this.dish = dish;
        this.order = order;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager_id) {
        this.manager = manager;
    }

    public String[] getDish() {
        return dish;
    }

    public void setDish(String[] dish) {
        this.dish = dish;
    }

    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "restaurant_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", manager_id='" + manager + '\'' +
                ", dish=" + Arrays.toString(dish) +
                ", order=" + Arrays.toString(order) +
                '}';
    }
}
