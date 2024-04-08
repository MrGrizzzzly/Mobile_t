package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList user_id;
    private final ArrayList user_name;
    private final ArrayList user_score;
    private final ArrayList user_country;



    CustomAdapter(Context context,
                  ArrayList user_id,
                  ArrayList user_name,
                  ArrayList user_score,
                  ArrayList user_country){
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_score = user_score;
        this.user_country = user_country;

    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singe_item2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_score_txt.setText(String.valueOf(user_score.get(position)));
        holder.user_country_img.setImageResource(Integer.parseInt(String.valueOf(user_country.get(position))));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_user.class);
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("name", String.valueOf(user_name.get(position)));
                intent.putExtra("country", String.valueOf(user_country.get(position)));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_name_txt, user_score_txt;
        ImageView user_country_img;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.id_user);
            user_name_txt = itemView.findViewById(R.id.name_user);
            user_score_txt = itemView.findViewById(R.id.score_user);
            user_country_img = itemView.findViewById(R.id.country_user);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
