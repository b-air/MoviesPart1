package com.example.android.movies.utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.movies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 15/03/2018.
 */

public class MovieJsonUtils {


    public static List<Movie> getMoviesList(String request){
        URL url = NetworkUtils.buildURL(request);
        String moviesJson = null;
        moviesJson = NetworkUtils.getMovieDBResponse(url);
        List<Movie> movies = getMoviesJson(moviesJson);

        Log.i("test", "getMoviesList: "+movies);
        return movies;
    }

    public static List<Movie> getMoviesJson(String moviesJsonParsed) {
        if (TextUtils.isEmpty(moviesJsonParsed)){
            return null;
        }

        List<Movie> movies = new ArrayList<>();
        String id = "";
        String voteAverage = "";
        String title = "";
        String posterPath = "";
        String imagePath = "";
        String overview = "";
        String releaseDate = "";

        /* parsing json */

        try {
        JSONObject moviesJson = new JSONObject(moviesJsonParsed);

        JSONArray moviesArray = moviesJson.getJSONArray("results");

        /* Loop through movies */
        for (int i = 0; i < moviesArray.length(); i++){
            JSONObject movieObject = moviesArray.getJSONObject(i);
            id = movieObject.getString("id");
            voteAverage = movieObject.getString("vote_average");
            title = movieObject.getString("title");
            posterPath = movieObject.getString("poster_path");
            imagePath = movieObject.getString("backdrop_path");
            overview = movieObject.getString("overview");
            releaseDate = movieObject.getString("release_date");
            movies.add(new Movie(id, voteAverage, title, posterPath, imagePath, overview, releaseDate));
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

        return movies;

    }
}
