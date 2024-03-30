package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder2 {

    TextView id_user;
    TextView name_user;
    TextView score_user;
    ImageView country_user;
    ProgramViewHolder2(View v)
    {
        id_user = v.findViewById(R.id.id_user);
        name_user = v.findViewById(R.id.name_user);
        score_user = v.findViewById(R.id.score_user);
        country_user = v.findViewById(R.id.country_user);
    }
}