package com.example.dostavahrane.api;

import com.example.dostavahrane.api.model.DishModel;
import com.example.dostavahrane.api.model.RestaurantModel;
import com.example.dostavahrane.api.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCalls {
    //"http://192.168.1.3:3000/api/      user/5fe36363af6e5b3ef4778c15"
    ///restaurant/:resID
    @GET("user/5fe36379af6e5b3ef4778c16")
    Call<UserModel> getUser();

    @GET("user/")
    Call<List<UserModel>> getUsers();

    @GET("restaurant/")
    Call<ArrayList<RestaurantModel>> getRestaurants();

    @POST("user/register")
    Call<UserModel> addUser(@Body UserModel userModel);

    @GET("restaurant/")
    Call<ArrayList<DishModel>> getDishes();
}
