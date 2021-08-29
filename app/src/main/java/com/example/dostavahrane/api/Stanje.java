package com.example.dostavahrane.api;

import com.example.dostavahrane.api.model.DishModel;
import com.example.dostavahrane.api.model.OrderModel;
import com.example.dostavahrane.api.model.RestaurantModel;
import com.example.dostavahrane.api.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class Stanje {
    public static UserModel currentUser = null;

    public static OrderModel currentOrder = null;

    public static RestaurantModel currentRestaurant = null;

    public static ArrayList<DishModel> dishToOrder = new ArrayList<DishModel>();
}
