package com.example.carsgallery;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{
    List<Car> cars;
    public RecycleViewAdapter(List<Car>messages) {
        this.cars = messages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ContentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car_activity , null ,false);
        MyViewHolder myViewHolder = new MyViewHolder(ContentView);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car cars = this.cars.get(position);

        holder.model.setText(cars.getModel());
        holder.color.setText(cars.getColor());
        if (cars.getImage() != null && !cars.getImage().isEmpty())
            holder.imageView.setImageURI(Uri.parse(cars.getImage()));
        holder.DPL.setText(String.valueOf(cars.getDPL()));

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView model;
        TextView color;
        TextView DPL;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.customCar_car_iv);
            model = itemView.findViewById(R.id.customCar_model_tv);
            color = itemView.findViewById(R.id.customCar_color_tv);
            DPL = itemView.findViewById(R.id.customCar_DistancePerLiter_tv);
        }
    }
}
