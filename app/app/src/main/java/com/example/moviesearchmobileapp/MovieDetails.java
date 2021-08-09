package com.example.moviesearchmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.moviesearchmobileapp.databinding.ActivityMovieDetailsBinding;

public class MovieDetails extends AppCompatActivity {
    ImageButton prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Movie movie = getIntent().getParcelableExtra("Movie"); //reading the movie from bundle
        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        binding.setMovie(movie); // pass the current movie to this object

        prev = (ImageButton) findViewById(R.id.btPrev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}