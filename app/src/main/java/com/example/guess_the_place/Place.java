package com.example.guess_the_place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

class Place implements Parcelable {
    String name;
    String image;
    public LatLng position;


    public Place(){}

    public Place(String name1, String image1, LatLng position1){
        name = name1;
        image = image1;
        setPosition(position1);
    }

    protected Place(Parcel in) {
        name = in.readString();
        image = in.readString();
        setPosition((LatLng) in.readParcelable(GeoPoint.class.getClassLoader()));
       }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeParcelable((Parcelable) getPosition(), flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LatLng getPosition() {
        return position;
    }



    public void setPosition(LatLng position) {
        this.position = position;
    }
}