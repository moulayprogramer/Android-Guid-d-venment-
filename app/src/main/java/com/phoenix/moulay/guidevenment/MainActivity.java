package com.phoenix.moulay.guidevenment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    Button bconfrs,bconfr,bMap,bpalces,bprog,binfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bconfrs =  findViewById(R.id.bconfrs);
        bconfr = findViewById(R.id.bConfr);
        bMap = findViewById(R.id.bMap);
        bpalces = findViewById(R.id.bplac);
        bprog = findViewById(R.id.bprog);
        binfo = findViewById(R.id.binfo);
        bconfr.setOnClickListener(this);
        bconfrs.setOnClickListener(this);
        bMap.setOnClickListener(this);
        bprog.setOnClickListener(this);
        bpalces.setOnClickListener(this);
        binfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bconfrs:
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Exposant"));
                break;
            case R.id.bConfr:
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Conferences"));
                break;
            case R.id.bplac:
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Places"));
                break;
            case R.id.bMap:
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Map"));
                break;
            case R.id.bprog:
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Pregramme"));
                break;
            case R.id.binfo:
                startActivity(new Intent("com.phoenix.moulay.guidevenment.Info"));
                break;
        }
    }
}
