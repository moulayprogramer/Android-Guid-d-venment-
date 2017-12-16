package com.phoenix.moulay.guidevenment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class placesmarkaz extends AppCompatActivity {
    WebView plan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placesmarkaz);

        plan = findViewById(R.id.webView);
        plan.loadUrl("file:///android_asset/mapmaz.html");
        plan.getSettings().setBuiltInZoomControls(true);
        plan.getSettings().setLoadWithOverviewMode(true);
        plan.getSettings().setUseWideViewPort(true);
    }
}
