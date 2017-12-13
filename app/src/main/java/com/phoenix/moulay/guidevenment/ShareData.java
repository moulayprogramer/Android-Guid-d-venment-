package com.phoenix.moulay.guidevenment;

import android.app.Application;

import com.phoenix.moulay.guidevenment.Modeles.Exposant;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by moulay on 13/12/17.
 **/

public class ShareData extends Application {
    public ArrayList<Exposant> exposants;
    public ArrayList<String> NameExpo;
    public Exposant selectedExposant;
    public XmlPullParser myParser;
    public InputStream input;

    public void getExposant(){
        exposants = new ArrayList<>();
        NameExpo = new ArrayList<>();
        Exposant exposant ;
        int event;
        String name;
        try {
            myParser.setInput(input,null);
            event = myParser.getEventType();
            if(event == XmlPullParser.END_DOCUMENT)  System.out.println("null -_-");
            else {
                exposant = new Exposant();
                while (event != XmlPullParser.END_DOCUMENT) {
                    name = myParser.getName();
                    switch (event) {
                        case XmlPullParser.START_TAG:
                            switch (name) {
                                case "N":
                                    exposant.setNum(Integer.valueOf(myParser.nextText()));
                                    break;
                                case "Nom":
                                    String nom = myParser.nextText();
                                    exposant.setNom(nom);
                                    NameExpo.add(nom);
                                    break;
                                case "experience":
                                    exposant.setExpr(myParser.nextText());
                                    break;
                                case "presente":
                                    exposant.setPresente(myParser.nextText());
                                    break;
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (name.equals("Exposant")) {
                                exposants.add(exposant);
                                exposant = new Exposant();
                            }
                            break;
                    }
                    myParser.next();
                    event = myParser.getEventType();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
