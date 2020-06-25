package com.kruthik.scanner;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItems implements Parcelable {
    String name;
    String imgurl;
    String Actors;
    String rating;
    public MovieItems(String name, String imgurl, String actors, String rating) {
        this.name = name;
        this.imgurl = imgurl;
        Actors = actors;
        this.rating = rating;
    }

    protected MovieItems(Parcel in) {
        name = in.readString();
        imgurl = in.readString();
        Actors = in.readString();
        rating = in.readString();
    }

    public static final Creator<MovieItems> CREATOR = new Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel in) {
            return new MovieItems(in);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imgurl);
        parcel.writeString(Actors);
        parcel.writeString(rating);
    }
}
