package com.phoenix.moulay.guidevenment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.phoenix.moulay.guidevenment.widget.IndexableListView;

import org.xmlpull.v1.XmlPullParserFactory;

public class Conferences extends AppCompatActivity {

    protected IndexableListView mListView;
    XmlPullParserFactory xmlFactoryObject ;
    TextView tvtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_conference);
            tvtitle = findViewById(R.id.tvtitle);
            tvtitle.setTypeface(Typeface.createFromAsset(Conferences.this.getAssets(), "odessa.ttf"));

            /************************ read xml Exposant file ****************/
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            xmlFactoryObject.setNamespaceAware(false);
            ((ShareData)getApplication()).myParser = xmlFactoryObject.newPullParser();
            ((ShareData)getApplication()).input = getResources().openRawResource(R.raw.present);
            ((ShareData)getApplication()).myParser.setInput(((ShareData)getApplication()).input,null);
            ((ShareData)getApplication()).getConference();
            mListView = findViewById(R.id.list);
            ContentAdapter adapter = new ContentAdapter(this,
                    android.R.layout.simple_list_item_1, (((ShareData)getApplication()).Name));
            adapter.mSections = "";
            mListView.setAdapter(adapter);
            mListView.setFastScrollEnabled(true);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        ((ShareData)getApplication()).selectedConference = ((ShareData)getApplication()).listconferences.get(position);
                        startActivity(new Intent("com.phoenix.moulay.guidevenment.ConferenceInfo"));
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
