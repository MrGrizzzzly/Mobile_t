package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class StoreAdapter2 extends ArrayAdapter<String> {
    Context context;
    String[] id;
    String[] user;
    String[] score;
    int[] images;

    public StoreAdapter2(Context context, String[] id, String[] user, String[] score, int[] images) {
        super(context, R.layout.singe_item2, R.id.textView1, id);
        this.context = context;
        this.id = id;
        this.user = user;
        this.score = score;
        this.images = images;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolder2 holder = null;
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.singe_item2, parent, false);
            holder = new ProgramViewHolder2(singleItem);
            singleItem.setTag(holder);
        }
        else{
            holder = (ProgramViewHolder2) singleItem.getTag();
        }
        holder.id_user.setText(id[position]);
        holder.name_user.setText(user[position]);
        holder.score_user.setText(score[position]);
        holder.country_user.setImageResource(images[position]);
        singleItem.setOnClickListener(view -> Toast.makeText(getContext(), "Вы купили:"+ id[position], Toast.LENGTH_SHORT).show());
        return singleItem;
    }
}

