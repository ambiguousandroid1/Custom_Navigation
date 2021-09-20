package com.example.custom_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_state extends RecyclerView.Adapter<Adapter_state.Myviewholder>{
    private List<Data_model> list;
    Context context;

    public Adapter_state(List<Data_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recyclernew, parent, false);

        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        Data_model data_model = list.get(position);
        Picasso.with(context.getApplicationContext()).load(data_model.getImage()).fit().placeholder(R.drawable.item).into(holder.imageView);
//
//        holder.imageView.setImageResource(data_model.getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class Myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView3);
        }
    }
}
