package com.phoenix.moulay.guidevenment;

import android.app.Application;
import android.widget.Toast;

import com.phoenix.moulay.guidevenment.Modeles.Conference;
import com.phoenix.moulay.guidevenment.Modeles.Exposant;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by moulay on 13/12/17.
 **/

public class ShareData extends Application {
    public ArrayList<Exposant> exposants;
    public ArrayList<String> Name;
    public ArrayList<Conference> listconferences;
    public Exposant selectedExposant;
    public Conference selectedConference;
    public XmlPullParser myParser;
    public InputStream input;

    public void getExposant(){
        exposants = new ArrayList<>();
        if (Name != null){
            Name.clear();
        }else {
            Name = new ArrayList<>();
        }
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
                                    Name.add(nom);
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
    public void getConference(){
        listconferences = new ArrayList<>();
        if (Name != null){
            Name.clear();
        }else {
            Name = new ArrayList<>();
        }
        Conference conference;
        int event;
        String name;
        try {
            myParser.setInput(input,null);
            event = myParser.getEventType();
            if(event == XmlPullParser.END_DOCUMENT)  System.out.println("null -_-");
            else {
                int j = 0;
                conference = new Conference();
                while (event != XmlPullParser.END_DOCUMENT) {
                    name = myParser.getName();
                    switch (event) {
                        case XmlPullParser.START_TAG:
                            switch (name) {
                                case "N":
                                    conference.setNum(Integer.valueOf(myParser.nextText()));
                                    break;
                                case "Nom":

                                    String nom = myParser.nextText();
                                    conference.setNom(nom);
                                    Name.add(nom);
                                    break;
                                case "Temps":
                                    conference.setTemp(myParser.nextText());
                                    break;
                                case "Present":
                                    conference.setPresent(myParser.nextText());
                                    break;
                                case "Date":
                                    conference.setDate(myParser.nextText());
                                    break;
                                case "Lieu":
                                    conference.setLieu(myParser.nextText());
                                    break;
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (name.equals("conference")) {
                                listconferences.add(conference);
                                conference = new Conference();
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
