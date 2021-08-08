package com.example.moviesearchmobileapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Scanner;

public class APiUtil {

    private APiUtil(){}

    public static final String BASE_API_URL =
            "https://api.themoviedb.org/3/discover/movie?";
    public static final String SEARCH_API_URL = "https://api.themoviedb.org/3/search/movie?";
    public static final String KEY = "api_key";
    public static final String API_KEY = "3cba4c9355b19d38a59557cb2a8bff87";
    public static final String LanguageParam = "language";
    public static final String language = "en-US"; // default language
    public static final String Sort_ByParam = "sort_by";
    public static final String Query = "query=";

    public static URL buildURL(String sortby){ //selecting sort by options
        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(KEY, API_KEY)
                .appendQueryParameter(LanguageParam, language)
                .appendQueryParameter(Sort_ByParam, sortby).build();

        try{
            url = new URL(uri.toString()); // Uri to Url
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static URL buildSearchURL(String query){
        URL url = null;
        Uri uri = Uri.parse(SEARCH_API_URL).buildUpon()
                .appendQueryParameter(KEY, API_KEY)
                .appendQueryParameter(LanguageParam, language)
                .appendQueryParameter(Query, query).build();

        try{
            url = new URL(uri.toString()); // Uri to Url
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
        InputStream stream = connection.getInputStream();
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter("\\A"); //takes everything

        try{
            boolean hasData = scanner.hasNext();
            if(hasData){
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }

    public static ArrayList<Movie> getMovieFromJson(String json){ //parse the API data to the Movie object
        final String id = "id";
        final String movie_image = "poster_path";
        final String movie_name = "title";
        final String movie_description = "overview";
        final String original_language = "original_language";
        final String release_date = "release_date";
        final String vote_average = "vote_average";
        //final String genre_ids = "genre_ids";
        final String results =  "results"; //the part that we need to read in the json

        ArrayList<Movie> movies = new ArrayList<Movie>();
        try{
            JSONObject jsonMovies = new JSONObject(json);
            JSONArray arrayMovies = jsonMovies.getJSONArray(results); //parse the "result" array from json to array
            int movielist_len = arrayMovies.length();
            for (int i = 0; i < movielist_len; i ++){
                JSONObject movieJSON = arrayMovies.getJSONObject(i);
                //int genre_len = movieJSON.getJSONArray(genre_ids).length();
                //String[] genres = new String[genre_len];
                //for(int j = 0 ; j < genre_len ; j ++){

                //}
                Movie movie = new Movie(
                        movieJSON.getString(id),
                        movieJSON.getString(movie_image),
                        movieJSON.getString(movie_name),
                        movieJSON.getString(movie_description),
                        movieJSON.getString(original_language),
                        movieJSON.getString(release_date),
                        movieJSON.getString(vote_average));
                movies.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
