package com.example.dostavahrane;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.dostavahrane.api.Stanje;

public class UpdateDialog extends AppCompatDialogFragment {
    private EditText newData;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_update_dialog, null);
        newData = view.findViewById(R.id.inputNewData);

        builder.setView(view)
                .setTitle("Unesite novu email adresu")
                .setNegativeButton("otkazi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("promeni", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String data = newData.getText().toString();
                        if(UserProfileActivity.isEmail){
                            Stanje.currentUser.setEmail(data);
                            UserProfileActivity.userProfileEmail.setText(Stanje.currentUser.getEmail());
                            UserProfileActivity.isEmail = false;
                        }
                        if(UserProfileActivity.isPhone){
                            Stanje.currentUser.setPhone(data);
                            UserProfileActivity.userProfilePhone.setText(Stanje.currentUser.getPhone());
                            UserProfileActivity.isPhone = false;
                        }
                        if(UserProfileActivity.isAddress){
                            Stanje.currentUser.setAddress(data);
                            UserProfileActivity.userProfileAddress.setText(Stanje.currentUser.getAddress());
                            UserProfileActivity.isAddress = false;
                        }
                        data = "";
                    }
                });
        return builder.create();
    }
}
