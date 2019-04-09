package com.example.guess_the_place;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

import static java.lang.Math.round;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    final Context context = this;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //ImageView imageView = new ImageView();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                // Add a marker in Sydney and move the camera
                LatLng eiffelTower = new LatLng(48.858372, 2.294480);
                mMap.addMarker(new MarkerOptions().position(eiffelTower).title("The Eiffel Tower"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(eiffelTower));
                Log.d("!!!", "this is the Eiffel tower: " + eiffelTower.latitude + ", " + eiffelTower.longitude);



                Log.d("!!!","Map clicked " + point.latitude + ", " + point.longitude);
                googleMap.addMarker(new MarkerOptions().position(point));


                double x = (eiffelTower.longitude - point.longitude) * Math.cos((eiffelTower.latitude + point.latitude) / 2);
                double y = (eiffelTower.latitude - point.latitude);
                double distance = Math.round(Math.sqrt(x * x + y * y)* 100000);
                double distance1 = (Math.sqrt(x * x + y * y)* 100000);

                DecimalFormat df = new DecimalFormat("0.0");
                String d0 = df.format(distance1);

                DecimalFormat df1 = new DecimalFormat("0.00");
                String d1 = df1.format(distance1);
                
                if (distance <= 1000){
                    Log.d("!!!", "distance change to km: " + distance / 1000);

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage("you points: " + d0 + "m");
                    alert.setCancelable(true);
                    alert.setPositiveButton("end", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert1 = alert.create();
                    alert1.show();

                } else {
                    Log.d("!!!", "distance: " + distance);

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage("distance: " + d1 + "km");
                    alert.setCancelable(true);
                    alert.setPositiveButton("end", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert1 = alert.create();
                    alert1.show();
                }


            }

        });

    }
}
