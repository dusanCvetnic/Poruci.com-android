package com.example.dostavahrane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dostavahrane.api.ApiCalls;
import com.example.dostavahrane.api.Stanje;
import com.example.dostavahrane.api.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private TextView labelLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Prijavljivanje!");
                login();
            }
        });
    }

    public void login(){
        String url = "http://192.168.1.3:3000/api/";
        labelLogin = findViewById(R.id.labelLogin);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCalls apiCalls = retrofit.create(ApiCalls.class);

        Call<List<UserModel>> call = apiCalls.getUsers();

        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(response.code() != 200){
                    //
                }
                List<UserModel> users = response.body();

                EditText email = findViewById(R.id.loginEmail);
                EditText password = findViewById(R.id.loginPassword);

                for(UserModel user : users){
                    if((user.getEmail().equals(email.getText().toString())) && (user.getPassword().equals(password.getText().toString()))){

                        Stanje.currentUser = new UserModel(user.getName(), user.getSurname(), user.getEmail(), user.getPhone(), user.getPassword(), user.getAddress(), user.getRole(), user.get_id());

                        redirect();
                    }
                }
                if(Stanje.currentUser == null){
                    labelLogin.setText("Korisnicko ime ili lozinka nisu pravilno uneti!");
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                //
            }
        });

    }

    public void redirect(){
        if(Stanje.currentUser != null && Stanje.currentUser.getRole().equals("user")){
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
            this.finish();
        }

        /*if(Stanje.currentUser != null && Stanje.currentUser.getRole().equals("admin")){
            Intent intent = new Intent(this, AdminProfile.class);
            startActivity(intent);
            System.out.println("Preusmeravanje na admin profil i gasenje logina!");
            this.finish();
        }

        //Pokretanje panela za menadzera
        if(Stanje.currentUser != null && Stanje.currentUser.getRole().equals("manager")){
            Intent intent = new Intent(this, ManagerProfile.class);
            startActivity(intent);
            System.out.println("Preusmeravanje na manager profil i gasenje logina!");
            this.finish();
        }

        //Pokretanje panela za dostavljaca
        if(Stanje.currentUser != null && Stanje.currentUser.getRole().equals("delivery")){
            Intent intent = new Intent(this, DeliveryProfile.class);
            startActivity(intent);
            System.out.println("Preusmeravanje na delivery profil i gasenje logina!");
            this.finish();
        }*/
    }
}