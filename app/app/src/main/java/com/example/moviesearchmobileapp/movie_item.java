package com.example.moviesearchmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.moviesearchmobileapp.databinding.ActivityMovieDetailsBinding;

public class movie_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_item);
        Movie movie = getIntent().getParcelableExtra("Movie"); //reading the movie from bundle
        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_item);
        binding.setMovie(movie); // pass the current movie to this object
    }
}