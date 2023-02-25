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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    RecycleAdapterOnClickListener recycleAdapterOnClickListener;
    public RecycleViewAdapter(List<Car>cars , RecycleAdapterOnClickListener recycleAdapterOnClickListener) {
        this.cars = cars;
        this.recycleAdapterOnClickListener = recycleAdapterOnClickListener;
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
        holder.imageView.setTag(cars.getId());

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

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int currentCar = (int) imageView.getTag();
                    recycleAdapterOnClickListener.onClick(currentCar);
                }
            });
        }
    }
}
