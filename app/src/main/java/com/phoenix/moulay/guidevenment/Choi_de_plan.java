package com.phoenix.moulay.guidevenment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Choi_de_plan extends AppCompatActivity {
    LinearLayout planuniv,planmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_de_plan);

        planuniv = findViewById(R.id.planuniv);
        planmar = findViewById(R.id.planmarkaz);

        planuniv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Planuniv"));
            }
        });

        planmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Planmarkaz"));
            }
        });
    }

}
