package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class StoreAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] programName;
    String[] programDescription;

    public StoreAdapter(Context context, String[] programName, int[] images, String[] programDescription) {
        super(context, R.layout.singe_item, R.id.textView567, programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View singleItem = convertView;
        ProgramViewHolder holder;
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.singe_item, parent, false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else{
            holder = (ProgramViewHolder) singleItem.getTag();
        }
        holder.itemImage.setImageResource(images[position]);
        holder.programTitle.setText(programName[position]);
        holder.programDescription.setText(programDescription[position]);
        singleItem.setOnClickListener(view -> Toast.makeText(getContext(), "Вы купили:"+ programName[position], Toast.LENGTH_SHORT).show());
        return singleItem;
    }
}

