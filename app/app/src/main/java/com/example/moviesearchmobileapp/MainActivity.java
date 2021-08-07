package com.example.moviesearchmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String sortby = "popularity.desc"; // default sorting by
    static String language = "en-US"; // default language

    private ProgressBar LoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingProgress = (ProgressBar) findViewById(R.id.progressBar);

        try{
            URL movieURL = APiUtil.buildURL(language, sortby);
            new MovieQueryTask().execute(movieURL);

        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    public class MovieQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) { //array of url
            URL searchURL = urls[0];
            String result = null;
            try{
                result = APiUtil.getJson(searchURL);
            }
            catch (IOException e){
                Log.e("error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPreExecute(){ //before loading the jason
            super.onPreExecute();
            LoadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) { //after done loading the jason
            //ScrollView movieslist = (ScrollView) findViewById(R.id.scrollViewmovie);
            TextView movieslist = (TextView) findViewById(R.id.movielist);
            TextView loading_error = (TextView) findViewById(R.id.loadingerror);
            LoadingProgress.setVisibility(View.INVISIBLE);
            if(result == null){ // in a case of an error
                movieslist.setVisibility(View.INVISIBLE);
                loading_error.setVisibility(View.VISIBLE);
            }
            else{
                movieslist.setVisibility(View.VISIBLE);
                loading_error.setVisibility(View.INVISIBLE);
            }
            ArrayList<Movie> movies = APiUtil.getMovieFromJson(result); // create a movies array from json
            String resultString = "";
            for(Movie movie: movies){
                resultString = resultString + movie.getMovie_image() + "\n" +
                        movie.getMovie_name() + "\n" +
                        movie.getOriginal_language() + "\n" +
                        movie.getVote_average() + "\n\n";
            }
            movieslist.setText(resultString);
        }


    }
}