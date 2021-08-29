package com.example.dostavahrane;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostavahrane.api.Stanje;
import com.example.dostavahrane.api.model.RestaurantModel;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RestaurantModel> restaurantList;

    public recyclerAdapter(Context context, ArrayList<RestaurantModel> restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.ViewHolder holder, int position) {
        holder.restaurantItemName.setText(restaurantList.get(position).getName());
        holder.restaurantItemAddress.setText(restaurantList.get(position).getAddress());
        holder.restaurantItemPhone.setText(restaurantList.get(position).getPhone());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked on restaurant \"" + restaurantList.get(position).getName() ,  Toast.LENGTH_SHORT).show();
                Stanje.currentRestaurant = new RestaurantModel();
                Stanje.currentRestaurant.set_id(restaurantList.get(position).get_id());
                Stanje.currentRestaurant.setAddress(restaurantList.get(position).getAddress());
                Stanje.currentRestaurant.setPhone(restaurantList.get(position).getPhone());
                Stanje.currentRestaurant.setName(restaurantList.get(position).getName());
                Context context = v.getContext();
                Intent intent = new Intent(context, RestaurantMenuActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantItemName;
        TextView restaurantItemAddress;
        TextView restaurantItemPhone;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantItemName = itemView.findViewById(R.id.restaurantItemName);
            restaurantItemAddress = itemView.findViewById(R.id.restaurantItemAddress);
            restaurantItemPhone = itemView.findViewById(R.id.restaurantItemPhone);

            parentLayout = itemView.findViewById(R.id.restaurantDetails);
        }
    }
}
