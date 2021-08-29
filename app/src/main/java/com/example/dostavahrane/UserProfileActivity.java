package com.example.dostavahrane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dostavahrane.api.ApiCalls;
import com.example.dostavahrane.api.Stanje;
import com.example.dostavahrane.api.model.RestaurantModel;
import com.example.dostavahrane.api.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener{
    public static boolean isEmail = false, isPhone = false , isAddress = false;
    public static TextView userProfileName, userProfileEmail, userProfilePhone, userProfileAddress;

    private RecyclerView recyclerView;
    private  recyclerAdapter adapter;
    private ArrayList<RestaurantModel> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        restaurantList = new ArrayList<>();

        ImageButton buttonCart= findViewById(R.id.buttonCart);
        buttonCart.setOnClickListener(this);

        ImageButton buttonUpdateEmail= findViewById(R.id.buttonUpdateEmail);
        buttonUpdateEmail.setOnClickListener(this);
        ImageButton buttonUpdatePhone= findViewById(R.id.buttonUpdatePhone);
        buttonUpdatePhone.setOnClickListener(this);
        ImageButton buttonUpdateAddress= findViewById(R.id.buttonUpdateAddress);
        buttonUpdateAddress.setOnClickListener(this);

        ImageButton buttonLogout= findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        initComponents();
        setRestaurantInfo();
        //initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        adapter = new recyclerAdapter(this, this.restaurantList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setRestaurantInfo() {
        String url = "http://192.168.1.3:3000/api/";

        //Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        ApiCalls apiCalls = retrofit.create(ApiCalls.class);

        Call<ArrayList<RestaurantModel>> call = apiCalls.getRestaurants();

        call.enqueue(new Callback<ArrayList<RestaurantModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RestaurantModel>> call, Response<ArrayList<RestaurantModel>> response) {
                if(response.code() != 200){
                    //GRESKA
                }
                ArrayList<RestaurantModel> restaurants = response.body();
                System.out.println(restaurants);
                restaurantList = restaurants;
                initRecyclerView();
                System.out.println(restaurantList);
            }
            @Override
            public void onFailure(Call<ArrayList<RestaurantModel>> call, Throwable t) {
            }
        });
    }

    public void initComponents(){
        userProfileName = findViewById(R.id.userProfileName);
        userProfileEmail = findViewById(R.id.userProfileEmail);
        userProfilePhone = findViewById(R.id.userProfilePhone);
        userProfileAddress = findViewById(R.id.userProfileAddress);

        userProfileName.setText(Stanje.currentUser.getName() + " " + Stanje.currentUser.getSurname());
        userProfileEmail.setText(Stanje.currentUser.getEmail());
        userProfilePhone.setText(Stanje.currentUser.getPhone());
        userProfileAddress.setText(Stanje.currentUser.getAddress());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogout:
                this.logout();
                break;
            case R.id.buttonCart:
                this.goToCart();
                break;
            case R.id.buttonUpdateEmail:
                isEmail = true;
                openUpdateDialog();
                break;
            case R.id.buttonUpdatePhone:
                isPhone = true;
                openUpdateDialog();
                break;
            case R.id.buttonUpdateAddress:
                isAddress = true;
                openUpdateDialog();
                break;
        }
    }

    private void openUpdateDialog() {
        UpdateDialog updateDialog = new UpdateDialog();
        updateDialog.show(getSupportFragmentManager(), "update dialog");
    }

    private void goToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
        System.out.println("Korpa!");
    }

    private void logout() {
        Stanje.currentUser = null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        System.out.println("Logout!");
        this.finish();
    }
}