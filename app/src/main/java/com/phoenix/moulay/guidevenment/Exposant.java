package com.phoenix.moulay.guidevenment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.phoenix.moulay.guidevenment.widget.IndexableListView;

import org.xmlpull.v1.XmlPullParserFactory;

public class Exposant extends AppCompatActivity {

    protected IndexableListView mListView;
    XmlPullParserFactory xmlFactoryObject ;
    TextView tvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_exposant);
            tvtitle = findViewById(R.id.tvtitle);
            tvtitle.setTypeface(Typeface.createFromAsset(Exposant.this.getAssets(), "odessa.ttf"));

            /************************ read xml Exposant file ****************/
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            xmlFactoryObject.setNamespaceAware(false);
            ((ShareData)getApplication()).myParser = xmlFactoryObject.newPullParser();
            ((ShareData)getApplication()).input = getResources().openRawResource(R.raw.pollutec);
            ((ShareData)getApplication()).myParser.setInput(((ShareData)getApplication()).input,null);
            ((ShareData)getApplication()).getExposant();
            mListView = findViewById(R.id.listExposant);
            ContentAdapter adapter = new ContentAdapter(this,
                    android.R.layout.simple_list_item_1, (((ShareData)getApplication()).Name));
            adapter.mSections = "";
            mListView.setAdapter(adapter);
            mListView.setFastScrollEnabled(true);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        ((ShareData)getApplication()).selectedExposant = ((ShareData)getApplication()).exposants.get(position);
                        startActivity(new Intent("com.phoenix.moulay.guidevenment.ExposantsInfo"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
