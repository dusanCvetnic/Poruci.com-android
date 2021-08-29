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
import com.example.dostavahrane.api.model.DishModel;
import com.example.dostavahrane.api.model.RestaurantModel;

import java.util.ArrayList;

public class recyclerAdapterDish extends RecyclerView.Adapter<recyclerAdapterDish.ViewHolder>{
    private Context context;
    private ArrayList<DishModel> dishList;

    public recyclerAdapterDish(Context context, ArrayList<DishModel> dishList){
        this.context = context;
        this.dishList = dishList;
    }

    @NonNull
    @Override
    public recyclerAdapterDish.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_items, parent, false);
        recyclerAdapterDish.ViewHolder holder = new recyclerAdapterDish.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapterDish.ViewHolder holder, int position) {
        holder.dishItemName.setText(dishList.get(position).getName());
        holder.dishItemDescription.setText(dishList.get(position).getDescription());
        Double price = dishList.get(position).getPrice();
        holder.dishItemPrice.setText(price.toString());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Uspesno ste porucili \"" + dishList.get(position).getName() ,  Toast.LENGTH_SHORT).show();
                Context context = v.getContext();
                //Stanje.dishToOrder.add(dishList.get(position));
                Stanje.dishToOrder = new ArrayList<DishModel>();
                DishModel d = new DishModel();
                d.setCategory(dishList.get(position).getCategory());
                d.setDescription(dishList.get(position).getDescription());
                d.setDish_id(dishList.get(position).getDish_id());
                d.setName(dishList.get(position).getName());
                d.setOrder(dishList.get(position).getOrder());
                d.setPrice(dishList.get(position).getPrice());
                d.setRestaurant_id(dishList.get(position).getRestaurant_id());
                Stanje.dishToOrder.add(d);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishItemName;
        TextView dishItemDescription;
        TextView dishItemPrice;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishItemName = itemView.findViewById(R.id.dishItemName);
            dishItemDescription = itemView.findViewById(R.id.dishItemDescription);
            dishItemPrice = itemView.findViewById(R.id.dishItemPrice);

            parentLayout = itemView.findViewById(R.id.dishDetails);
        }
    }
}
