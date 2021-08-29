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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name, surname, email, password, phone, address;
    private Button registerButton;
    private TextView labelRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
    }
    public void register() {
        String url = "http://192.168.1.3:3000/api/";
        String inputName = name.getText().toString();
        String inputSurname = surname.getText().toString();
        String inputEmail = email.getText().toString();
        String inputPassword = password.getText().toString();
        String inputPhone = "00381" + phone.getText().toString();
        String inputAddress = address.getText().toString();

        if(inputName.isEmpty() || inputSurname.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty() || inputPhone.isEmpty() || inputAddress.isEmpty()){
            labelRegister.setText("Niste popunili sve podatke!");
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        //TEST
        UserModel userModel = new UserModel(inputName, inputSurname, inputEmail, inputPhone, inputPassword, inputAddress, null, null);

        Call<UserModel> call = apiCalls.addUser(userModel);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.code() != 200){
                    //GRESKA
                    labelRegister.setText("Greska prilikom registracije");
                    return;
                }
                //REZULTAT
                labelRegister.setText(response.body().get_id());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        System.out.println("Preusmeravanje na registraciju i gasenje logina!");
        this.finish();

    }
    private void initComponents(){
        name = (EditText) findViewById(R.id.registerName);
        surname = (EditText) findViewById(R.id.registerSurname);
        email = (EditText) findViewById(R.id.registerEmail);
        password = (EditText) findViewById(R.id.registerPassword);
        phone = (EditText) findViewById(R.id.registerPhone);
        address = (EditText) findViewById(R.id.registerAdress);
        labelRegister = (TextView) findViewById(R.id.labelRegister);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton: register();
                break;
        }
    }
}