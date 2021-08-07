package com.example.moviesearchmobileapp;

import android.media.Image;

import java.util.Date;

public class Movie {

    private String id;
    private String movie_image;
    private String movie_name;
    private String movie_description;
    private String original_language;
    private String release_date;
    private int vote_average;
    //private int[] genre_ids;

    public Movie(String id, String movie_image, String movie_name, String movie_description, String original_language, String release_date, int vote_average) {
        this.id = id;
        this.movie_image = movie_image;
        this.movie_name = movie_name;
        this.movie_description = movie_description;
        this.original_language = original_language;
        this.release_date = release_date;
        this.vote_average = vote_average;
        //this.genre_ids = genre_ids;
    }

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

    public int getVote_average() {
        return vote_average;
    }
}
