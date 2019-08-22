package com.example.guess_the_place;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    final Context context = this;
    private GoogleMap mMap;
    private Place currentPlace;
    private ImageView imageView;
    private GeoPoint geoPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if (null != intent){
           currentPlace = (Place)intent.getParcelableExtra("Key");
           //Place2 p = (Place2) intent.getParcelableExtra("geo");
           // Log.d("!!!", "onCreate: " + p.geo.getLatitude());
        }

        imageView = findViewById(R.id.hint);

        Glide.with(this)
                .load(currentPlace.image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
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

                mMap.addMarker(new MarkerOptions().position(currentPlace.getPosition()).title("The Eiffel Tower"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(eiffelTower));
                //Log.d("!!!", "this is the Eiffel tower: " + point.getLatitude() + ", " + point.getLongitude());

                //Log.d("!!!","Map clicked " + point.getLatitude() + ", " + point.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(point));

              //  double lat = geoPoint.getLatitude();
              //  double lng = geoPoint.getLongitude();
              //  LatLng latLng = new LatLng(lat, lng);

                double x = (currentPlace.getPosition().longitude - point.longitude) * Math.cos((currentPlace.getPosition().latitude + point.latitude) / 2);
                double y = currentPlace.getPosition().latitude - point.latitude;

                double distance = Math.round(Math.sqrt(x * x + y * y)* 100000);
                double distance1 = (Math.sqrt(x * x + y * y)* 100000);

                DecimalFormat df = new DecimalFormat("0.0");
                String d0 = df.format(distance1);

                DecimalFormat df1 = new DecimalFormat("0.00");
                String d1 = df1.format(distance1);
                
                if (distance <= 1000){

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage("Distance: " + d0 + "m" + "\n" + "\n" + "your points: "  /*+ score*/);
                    alert.setCancelable(false);
                    alert.setPositiveButton("end", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapsActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert1 = alert.create();
                    alert1.show();

                } else {

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage("Distance: " + d1 + "km" + "\n" + "\n" + "your points: "  /*+ score*/);
                    alert.setCancelable(false);
                    alert.setPositiveButton("end", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapsActivity.this, MenuActivity.class);
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
