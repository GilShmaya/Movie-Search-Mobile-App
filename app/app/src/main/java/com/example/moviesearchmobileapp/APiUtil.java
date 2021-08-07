package com.example.moviesearchmobileapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class APiUtil {

    private APiUtil(){}

    public static final String BASE_API_URL =
            "https://api.themoviedb.org/3/discover/movie?";
    public static final String KEY = "api_key";
    public static final String API_KEY = "3cba4c9355b19d38a59557cb2a8bff87";
    public static final String LanguageParam = "language";
    public static final String Sort_ByParam = "sort_by";

    public static URL buildURL(String language, String sortby){ //selecting sort by and language options
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
}
