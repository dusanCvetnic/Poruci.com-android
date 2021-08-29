package com.example.dostavahrane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dostavahrane.api.ApiCalls;
import com.example.dostavahrane.api.Stanje;
import com.example.dostavahrane.api.model.DishModel;
import com.example.dostavahrane.api.model.RestaurantModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantMenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  recyclerAdapterDish adapter;
    private ArrayList<DishModel> dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        dishList = new ArrayList<>();

        setDishInfo();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.dishRecyclerView);
        adapter = new recyclerAdapterDish(this, this.dishList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDishInfo() {
        String url = "http://192.168.1.3:3000/api/dish/" + Stanje.currentRestaurant.get_id() + "/";

        //Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        ApiCalls apiCalls = retrofit.create(ApiCalls.class);

        Call<ArrayList<DishModel>> call = apiCalls.getDishes();

        call.enqueue(new Callback<ArrayList<DishModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DishModel>> call, Response<ArrayList<DishModel>> response) {
                if(response.code() != 200){
                    //GRESKA
                }
                ArrayList<DishModel> dishes = response.body();

                System.out.println(dishes);
                dishList = dishes;
                initRecyclerView();
                System.out.println(dishList);
            }
            @Override
            public void onFailure(Call<ArrayList<DishModel>> call, Throwable t) {
            }
        });
    }
}