package com.example.moviesearchmobileapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    static String Base_URL_Image = "https://image.tmdb.org/t/p/w500";

    ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    // is called when the RecyclerView needs a new ViewHolder
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.activity_movie_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    // is called to display the data
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    //updates layout with data
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView movieimage;
        TextView title;
        TextView vote_average;
        TextView release_date;

        public MovieViewHolder(View itemView){
            super(itemView);
            movieimage = (ImageView) itemView.findViewById(R.id.movieimage);
            title = (TextView) itemView.findViewById(R.id.moviename);
            vote_average = (TextView) itemView.findViewById(R.id.vote_average);
            release_date = (TextView) itemView.findViewById(R.id.release_date);
            itemView.setOnClickListener(this);
        }

        public void bind (Movie movie){
            //movieimage.setImageURI(Uri.parse(Base_URL_Image + movie.getMovie_image()));
            title.setText(movie.getMovie_name());
            vote_average.setText("Vote average: " + movie.getVote_average());
            release_date.setText("Release date: " + movie.getRelease_date());
            Picasso.with(this.itemView.getContext()).load(Base_URL_Image + movie.getMovie_image()).into(movieimage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); //get the position of the movie which was clicked
            Movie selectedmovie = movies.get(position);
            Intent intent = new Intent(view.getContext(), MovieDetails.class); //An Intent provides a facility for performing late runtime binding between the code in different applications such launching of activities
            intent.putExtra("Movie", selectedmovie);
            view.getContext().startActivity(intent);
        }


    }
}
