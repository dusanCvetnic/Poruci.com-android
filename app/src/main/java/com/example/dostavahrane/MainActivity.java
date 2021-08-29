package com.example.dostavahrane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dostavahrane.api.ApiCalls;
import com.example.dostavahrane.api.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGoToLogin= findViewById(R.id.buttonGoToLogin);
        buttonGoToLogin.setOnClickListener(this);

        Button buttonGoToRegister = findViewById(R.id.buttonGoToRegister);
        buttonGoToRegister.setOnClickListener(this);
    }

    private void goToRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        System.out.println("Preusmeravanje na registraciju!");
        this.finish();
    }
    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        System.out.println("Preusmeravanje na login!");
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonGoToLogin:
                this.goToLogin();
                break;
            case R.id.buttonGoToRegister:
                this.goToRegister();
                break;
        }
    }



    /*public void redirect(){
        //Pokretanje korisnickog profila
        if(Stanje.currentUser != null && Stanje.currentUser.getRole().equals("user")){
            Intent intent = new Intent(this, UserProfile.class);
            startActivity(intent);
            System.out.println("Preusmeravanje na korisnicki profil i gasenje logina!");
            this.finish();
        }

        //Pokretanje admin panela
        if(Stanje.currentUser != null && Stanje.currentUser.getRole().equals("admin")){
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
        }
    }*/
}