package com.phoenix.moulay.guidevenment;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapAct extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location loc ;
    ArrayList<LatLng> MarkerPoints;
    private static final int  PERMISSION_REQUEST_CODE = 11;
    private Marker mCurrLocationMarkerInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // request Permission ...........
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermision();
        }

        // turn On Gps ........
        if (!testGps()){
            ((ShareData) getApplication()).turnGPSOn(MapAct.this);
        }

        // open Location Manager .............
        loc = new Location(MapAct.this,(LocationManager) getSystemService(Context.LOCATION_SERVICE),getApplication());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loc != null) loc.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ((ShareData)getApplication()).mGoogleMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_window, null);

                TextView tvLocality = v.findViewById(R.id.tv_locality);
                TextView tvLat = v.findViewById(R.id.tv_lat);
                TextView tvLng = v.findViewById(R.id.tv_lng);
                TextView tvSnippet = v.findViewById(R.id.tv_snippet);
                LatLng ll = marker.getPosition();

                tvLocality.setText(marker.getTitle());
                tvLat.setText("Latitude : " + ll.latitude);
                tvLng.setText("Longitude : " + ll.longitude);
                tvSnippet.setText(marker.getSnippet());
                //tvImage.setImageDrawable());
                return v;
            }
        });


        goToLocationZoom(33.8001282, 2.8697104, 15);
        //Initializing
        MarkerPoints = new ArrayList<>();
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mMap.moveCamera(update);
    }

    public boolean testGps(){
        LocationManager locationManager = (LocationManager) MapAct.this.getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                
                if (!(grantResults.length >0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        && (grantResults[1] == PackageManager.PERMISSION_GRANTED))){
                    // not allow all
                    RequestPermision();
                    Toast.makeText(getApplicationContext(),"not allow the permission",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void RequestPermision (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.INTERNET}
                        , PERMISSION_REQUEST_CODE);
            }
        }
    }
    
    /*************************** Tracing **********************************************/
    //**
    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }
//    *
//     * A method to download json data from url

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    public void gotoUATL(View view) {
        LatLng point = new LatLng(33.795964, 2.849461);
        // Already two locations
        if (MarkerPoints.size() <=2) {
            MarkerPoints.clear();
            mMap.clear();
        }
        //Place initial location marker
        final android.location.Location location1 = ((ShareData)getApplication()).mLastLocation;
        final LatLng latLng1 = new LatLng(location1.getLatitude(), location1.getLongitude());
        final MarkerOptions markerOptions1 = new MarkerOptions()
                .position(latLng1)
                .title("Position courante")
                .snippet("Je Suis La !")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));


        mCurrLocationMarkerInit = mMap.addMarker(markerOptions1);
        //20/11/2017
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        // Adding the initial position to the ArrayList
        MarkerPoints.add(latLng1);
        // Adding new item to the ArrayList
        MarkerPoints.add(point);
        // Creating MarkerOptions
        MarkerOptions options = new MarkerOptions();
        // Setting the position of the marker
        options.position(point)
                .title("UATL")
                .snippet("جامعة عمار ثليجي")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));;
        // Add new marker to the Google Map Android API V2
        mMap.addMarker(options);
        // Checks, whether start and end locations are captured
        if (MarkerPoints.size() > 1) {
            LatLng origin = MarkerPoints.get(0);
            LatLng dest = MarkerPoints.get(1);
            // Getting URL to the Google Directions API
            String url = getUrl(origin, dest);
            Log.d("gotoUATL", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
        }
    }
    public void gotoCRSIC(View view) {
        LatLng point = new LatLng(33.792207, 2.848128);
        // Already two locations
        if (MarkerPoints.size() <= 2) {
            MarkerPoints.clear();
            mMap.clear();
        }
        //Place initial location marker
        final android.location.Location location1 = ((ShareData)getApplication()).mLastLocation;
        final LatLng latLng1 = new LatLng(location1.getLatitude(), location1.getLongitude());
        final MarkerOptions markerOptions1 = new MarkerOptions()
                .position(latLng1);
        mCurrLocationMarkerInit = mMap.addMarker(markerOptions1);
        //20/11/2017

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        // Adding the initial position to the ArrayList
        MarkerPoints.add(latLng1);
        // Adding new item to the ArrayList
        MarkerPoints.add(point);
        // Creating MarkerOptions
        MarkerOptions options = new MarkerOptions();
        // Setting the position of the marker
        options.position(point)
                .title("CRSIC")
                .snippet("مركز البحث في العلوم الاسلامية");
        // Add new marker to the Google Map Android API V2
        mMap.addMarker(options);
        // point.showInfoWindow();
        // Checks, whether start and end locations are captured
        if (MarkerPoints.size() > 1) {
            LatLng origin = MarkerPoints.get(0);
            LatLng dest = MarkerPoints.get(1);
            // Getting URL to the Google Directions API
            String url = getUrl(origin, dest);
            Log.d("gotoCRSIC", url.toString());
            FetchUrl FetchUrl = new FetchUrl();
            // Start downloading json data from Google Directions API
            FetchUrl.execute(url);
            // move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        }
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
    //
//    *
//     * A class to parse the Google Places in JSON format
//
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(7);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }
}
