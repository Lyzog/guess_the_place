package com.example.guess_the_place;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private ImageView imageView;
    private Place currentPlace;

    private ArrayList<Place> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.Guess);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        imageView = findViewById(R.id.Guess1);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        GeoPoint one = new GeoPoint(48.858372, 2.294480);
        GeoPoint two = new GeoPoint(43.723028, 10.396611);
        GeoPoint three = new GeoPoint(40.689722, -74.044722);
        //  Place eiffelTower = new Place("Eiffel Tower", "https://firebasestorage.googleapis.com/v0/b/guesstheplace-3344a.appspot.com/o/eiffel-tower-975004_960_720.jpg?alt=media&token=c49a7c3a-6250-48d8-abc7-8b22a80a367e",one);
        //  Place leaningTowerOfPisa = new Place("Leaning Tower of Pisa", "https://firebasestorage.googleapis.com/v0/b/guesstheplace-3344a.appspot.com/o/leaning-tower-298223_1920.jpg?alt=media&token=f2bbbcc7-ec7d-4e8f-924b-7d2fef84525f",two);
        //  Place statueOfLiberty = new Place("Statue of Liberty", "https://firebasestorage.googleapis.com/v0/b/guesstheplace-3344a.appspot.com/o/imageproxy.jpg?alt=media&token=20ad1335-f1ed-4ca0-8bb6-36f16d8ca67e",three);
/*
        CollectionReference placeRef = db.collection("places");

        placeRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                list.clear();
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Place place = snapshot.toObject(Place.class);
                    list.add(place);
                }
                Log.d("!!!", "size: " + list.size());

                for (Place place : list) {
                    Log.d("!!!", String.valueOf(place));
                }
            }
        });
*/
        // placeRef.add(eiffelTower);
        // placeRef.add(leaningTowerOfPisa);
        // placeRef.add(statueOfLiberty);


        Random random = new Random();
        int n = random.nextInt(3);
/*
        if (n == 0){
            currentPlace = eiffelTower;
        }
        else if (n == 1){
            currentPlace = leaningTowerOfPisa;
        }
        else if (n == 2){
            currentPlace = statueOfLiberty;
        }
*/
        //currentPlace = eiffelTower;

      /*
       Glide.with(context)
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
                */
    }
    public void openMap(){
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);

        startActivity(intent);
    }
}