package com.example.android.movies;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.movies.models.Movie;
import com.example.android.movies.utilities.MovieJsonUtils;

import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>>{
    String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        return MovieJsonUtils.getMoviesList(mUrl);

    }
}
