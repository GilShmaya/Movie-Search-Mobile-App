package com.example.moviesearchmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    static String sortby = "popularity.desc"; // default sorting by

    private ProgressBar LoadingProgress;
    private RecyclerView rv_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingProgress = (ProgressBar) findViewById(R.id.progressBar);
        rv_movie = (RecyclerView) findViewById(R.id.recyclerView_movies);
        LinearLayoutManager moviesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_movie.setLayoutManager(moviesLayoutManager);
        try{
            URL movieURL = APiUtil.buildURL(sortby);
            new MovieQueryTask().execute(movieURL);

        } catch (Exception e) {
            Log.d("error", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_list_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search); //get searching item
        final SearchView searchView = (SearchView) searchItem.getActionView(); //returns the search action view that we set for this menu
        searchView.setOnQueryTextListener(this); //to response to user's action
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) { //searching movies when the user submit a search
        /*try{
            URL movieURL = APiUtil.buildSearchURL(s);
            new MovieQueryTask().execute(movieURL);
        }
        catch (Exception e){
            Log.d("error", e.getMessage());
        } */
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
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
            TextView loading_error = (TextView) findViewById(R.id.loadingerror);
            LoadingProgress.setVisibility(View.INVISIBLE);
            if(result == null){ // in a case of an error
                rv_movie.setVisibility(View.INVISIBLE);
                loading_error.setVisibility(View.VISIBLE);
            }
            else{
                rv_movie.setVisibility(View.VISIBLE);
                loading_error.setVisibility(View.INVISIBLE);
            }
            ArrayList<Movie> movies = APiUtil.getMovieFromJson(result); // create a movies array from json

            MovieAdapter adapter = new MovieAdapter(movies); //adapts movie fields to layout
            rv_movie.setAdapter(adapter);
        }


    }
}