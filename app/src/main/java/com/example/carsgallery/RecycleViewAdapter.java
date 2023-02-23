package com.example.carsgallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{
    List<Car> cars;
    public RecycleViewAdapter(List<Car>messages) {
        this.cars = messages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ContentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car_activity , null);
        MyViewHolder myViewHolder = new MyViewHolder(ContentView);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car cars = this.cars.get(position);


//        if (message.getSentBy().equals(message.SENT_BY_BOT)){
//            holder.leftLayout.setVisibility(View.GONE);
//            holder.rightLayout.setVisibility(View.VISIBLE);
//            holder.rightTextContent.setText(message.getContent());
//        }else{
//            holder.rightLayout.setVisibility(View.GONE);
//            holder.leftLayout.setVisibility(View.VISIBLE);
//            holder.leftTextContent.setText(message.getContent());
//        }

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
