package com.phoenix.moulay.guidevenment;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class Pregramme extends AppCompatActivity {
    TextView tvtitle;
    WebView webView,wbs1,wbs2,wbs3,wbs4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregramme);
        webView = findViewById(R.id.wb);
        wbs1 = findViewById(R.id.wbsc1);
        wbs2 = findViewById(R.id.wbs2);
        wbs3 = findViewById(R.id.wbs3);
        wbs4 = findViewById(R.id.wbs4);
        tvtitle = findViewById(R.id.tvtitle);


        tvtitle.setTypeface(Typeface.createFromAsset(getAssets(), "odessa.ttf"));

        webView.loadData("<html><head></head><body><table><tr><td width=60px valign=top ><font color=#1EABF1  >14h00 :</font></td><td> <font color=black><b>Accueil des participants </b>- Cocktail de beinvenue.</font> " +
                "</td></tr></table><table><tr><td width=60px valign=top><font color=#1EABF1 >14h30 :</font></td><td> <font color=black><b>Inaugurationofficielle :</b> Séance d'ouverture et allocutions.</font> </td></tr></table><table><tr><td width=60px valign=top >" +
                "<font color=#1EABF1 >15h00 :</font></td><td><font color=black><b>Ethique et Recherche :</b> le scientifique moderne entre Dogmatisme et Scepaticisme. </font></td></tr></table>" +
                "<table><tr><td width=60px valign=top ><font color=#1EABF1 >15h20 :</font></td><td><font color=black><b>Message adressé de M.Mohamed BEDJAOUI :</b> Bioéthique et santé.</font></td></tr></table></body></html>","text/html; charset=utf-8","utf-8");
        wbs1.loadData("<html><head></head><body><table><tr><td width=60px valign=top ></td><td><font color=black><b>Allocution de bienvenue</b></font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >08h30 :</font></td><td><font color=black><b>Bioéthique, principes et régles</b>" +
                "</font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >08h50 :</font></td>" +
                "<td><font color=black><b>Place de l'éthique dans le droit musulman</b></font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >09h10 :</font></td>" +
                "<td><font color=black><b>Débat</b></font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >10h00 :</font></td><td><font color=black><b>Pause-café</b></font></td></tr></table></body></html>", "text/html; charset=utf-8", "utf-8");
        wbs2.loadData("<html><head></head><body><table><tr><td width=60px valign=top ><font color=#1EABF1 >10h30 :</font></td><td><font color=black><b>Equation entre les faits scientifiques et leurs fins légitimes, approche théologique.</b></font></td></tr></table><table><tr>" +
                "<td width=60px valign=top ><font color=#1EABF1 >10h50 :</font></td><td><font color=black><b>Bioéthique, quels enjeux ?</b></font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >11h10 :" +
                "</font></td><td><font color=black><b>Débat</b></font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >13h00 :</font><" +
                "/td><td><font color=black><b>Pause</b></font></td></tr></table></body></html>", "text/html; charset=utf-8", "utf-8");
        wbs3.loadData("<html><head></head><body><table><tr><td width=60px valign=top ><font color=#1EABF1 >14h00 :</font></td><td><font color=black><b>Ethique médicale, principes généraux</b></font></td></tr></table><table><tr>" +
                "<td width=60px valign=top ><font color=#1EABF1 >14h20 :</font></td><td><font color=black><b>La promotion de l'éthique dans les établissements de recherche.</b></font></td></tr></table><table><tr><td width=60px valign=top >" +
                "<font color=#1EABF1 >14h50 :</font></td><td><font color=black><b>Débat</b></font></td></tr></table><table><tr><td width=60px valign=top ><font color=#1EABF1 >16h00 :</font></td><td><font color=black><b>Pause</b></font></td>" +
                "</tr></table></body></html>", "text/html; charset=utf-8", "utf-8");
        wbs4.loadData("<html><head></head><body><table><tr><td width=60px valign=top ><font color=#1EABF1 >16h30 :</font></td><td><font color=black><b>Ateliers</b></font><br><font color=black><b>1er Atelier : </b>" +
                " Charte ethique de l'atrss.</font><br><font color=black><b>2ème Atelier : </b>Consentement eclaire.</font><br><font color=black><b>3ème Atelier : </b>Institutionnalisation de l'éthique</font></td></tr>" +
                "</table><table><tr><td width=60px valign=top ><font color=#1EABF1 >18h00 :</font></td><td><font color=black><b>Synthèse et présentation des recommandations en plénière</b></font></td></tr></table><table>" +
                "<tr><td width=60px valign=top ><font color=#1EABF1 >18h30 :</font></td><td><font color=black><b>Cloture</b></font></td></tr></table></body></html>", "text/html; charset=utf-8", "utf-8");


    }
}
