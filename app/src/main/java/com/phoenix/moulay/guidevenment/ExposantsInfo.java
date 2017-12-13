package com.phoenix.moulay.guidevenment;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExposantsInfo extends AppCompatActivity {
    TextView tvnom,tvpresent,tvpost,nom,post,present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposants_info);
        tvnom = findViewById(R.id.tvnom);
        tvnom.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        tvpresent = findViewById(R.id.tvpresent);
        tvpresent.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        tvpost = findViewById(R.id.tvpost);
        tvpost.setTypeface(Typeface.createFromAsset(getAssets(), "font-01.ttf"));
        nom = findViewById(R.id.tnom);
        post = findViewById(R.id.tpost);
        present = findViewById(R.id.tpresente);
        nom.setText(((ShareData)getApplication()).selectedExposant.getNom());
        post.setText(((ShareData)getApplication()).selectedExposant.getExpr());
        present.setText(((ShareData)getApplication()).selectedExposant.getPresente());
    }
}
