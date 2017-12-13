package com.phoenix.moulay.guidevenment;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConferenceInfo extends AppCompatActivity {
    TextView tvnom,tvtemp,tvlieu,tvdate,tvpresent, nom,temp,lieu,date,present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_info);

        tvnom = findViewById(R.id.tvnom);
        tvnom.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        tvtemp = findViewById(R.id.tvtemp);
        tvtemp.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        tvlieu = findViewById(R.id.tvlieu);
        tvlieu.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        tvdate = findViewById(R.id.tvdate);
        tvdate.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        tvpresent = findViewById(R.id.tvpresente);
        tvpresent.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));

        nom = findViewById(R.id.nom);
        temp = findViewById(R.id.temp);
        lieu = findViewById(R.id.lieu);
        date = findViewById(R.id.date);
        present = findViewById(R.id.presente);
        if (((ShareData)getApplication()).selectedConference != null) {
            nom.setText(((ShareData) getApplication()).selectedConference.getNom());
            temp.setText(((ShareData) getApplication()).selectedConference.getTemp());
            lieu.setText(((ShareData) getApplication()).selectedConference.getLieu());
            date.setText(((ShareData) getApplication()).selectedConference.getDate());
            present.setText(((ShareData) getApplication()).selectedConference.getPresent());
        }

    }
}
