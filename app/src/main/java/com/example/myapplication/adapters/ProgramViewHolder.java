package com.example.myapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class ProgramViewHolder {
    ImageView itemImage;
    TextView programTitle;
    TextView programDescription;
    public ProgramViewHolder(View v)
    {
        itemImage = v.findViewById(R.id.imageView);
        programTitle = v.findViewById(R.id.textView567);
        programDescription = v.findViewById(R.id.textView2);
    }
}