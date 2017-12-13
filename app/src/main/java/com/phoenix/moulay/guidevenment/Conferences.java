package com.phoenix.moulay.guidevenment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.phoenix.moulay.guidevenment.Modeles.Exposant;
import com.phoenix.moulay.guidevenment.widget.IndexableListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.Arrays;

public class Conferences extends AppCompatActivity {

    private IndexableListView mListView;
    XmlPullParserFactory xmlFactoryObject ;
    TextView tvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_conferences);
            tvtitle = findViewById(R.id.tvtitle);
            tvtitle.setTypeface(Typeface.createFromAsset(Conferences.this.getAssets(), "odessa.ttf"));

            /************************ read xml Exposant file ****************/
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            xmlFactoryObject.setNamespaceAware(false);
            ((ShareData)getApplication()).myParser = xmlFactoryObject.newPullParser();
            ((ShareData)getApplication()).input = getResources().openRawResource(R.raw.pollutec);
            ((ShareData)getApplication()).myParser.setInput(((ShareData)getApplication()).input,null);
            ((ShareData)getApplication()).getExposant();
            mListView = findViewById(R.id.listExposant);
            ContentAdapter adapter = new ContentAdapter(this,
                    android.R.layout.simple_list_item_1, (((ShareData)getApplication()).NameExpo));
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
