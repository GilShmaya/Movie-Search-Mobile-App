package com.example.moviesearchmobileapp;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import java.util.Date;

// uses Parcelable for passing data between components faster than Serializable
public class Movie implements Parcelable {
    static String Base_URL_Image = "https://image.tmdb.org/t/p/w500";

    private String id;
    private String movie_image;
    private String movie_name;
    private String movie_description;
    private String original_language;
    private String release_date;
    private String vote_average;
    //private int[] genre_ids;

    public Movie(String id, String movie_image, String movie_name, String movie_description, String original_language, String release_date, String vote_average) {
        this.id = id;
        this.movie_image = Base_URL_Image + movie_image;
        this.movie_name = movie_name;
        this.movie_description = movie_description;
        this.original_language = original_language;
        this.release_date = release_date;
        this.vote_average = vote_average;
        //this.genre_ids = genre_ids;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        movie_image = Base_URL_Image + in.readString();
        movie_name = in.readString();
        movie_description = in.readString();
        original_language = in.readString();
        release_date = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(movie_image);
        parcel.writeString(movie_name);
        parcel.writeString(movie_description);
        parcel.writeString(original_language);
        parcel.writeString(release_date);
        parcel.writeString(vote_average);
    }

    @BindingAdapter({"android:imageUrl"})
    public static void loadImage(ImageView imageView, String imageURL){
        Picasso.with(imageView.getContext()).load(imageURL).placeholder(R.drawable.movie_loading).into(imageView);
    }
}
