package com.phoenix.moulay.guidevenment;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import static android.content.Context.LOCATION_SERVICE;

public class Location implements LocationListener {
    private Context c;
    private LocationManager locationManager;
    private String provider;
    private Application app;
    android.location.Location location;

    Location(Context con, LocationManager LM, Application ap) {
        try {
            locationManager = LM;
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            c = con;
            app = ap;
            provider = "gps";

            if (testGps()) {

                if (ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                }
                location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    onLocationChanged(location);
                } else {
                    locationManager.requestLocationUpdates(provider, 0, 20, this);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean testGps() {
        LocationManager locationManager = (LocationManager) app.getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        try {
            Toast.makeText(c,""+location.getLatitude(),Toast.LENGTH_SHORT).show();
            ((ShareData)app).mLastLocation = location;
            ((ShareData)app).MyLocation(location.getLatitude(),location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    void onDestroy() {
        if (locationManager != null)
            locationManager.removeUpdates(this);
    }

    void onStart() {
        if (locationManager != null && provider != null) {
            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            locationManager.requestLocationUpdates(provider, 0, 1, this);
    }
    }



}