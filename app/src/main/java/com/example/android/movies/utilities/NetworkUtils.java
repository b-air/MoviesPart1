package com.example.android.movies.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.movies.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by bruno on 23/02/2018. Udacity Android Nanodegree Challenge
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    final static String MOVIE_DB_API_KEY= "?api_key=" + BuildConfig.MY_MOVIE_DB_API_KEY;

    final static String MOVIE_DB_POPULARITY_URL = "https://api.themoviedb.org/3/movie/popular";

    final static String MOVIE_DB_TOP_RATED_URL = "https://api.themoviedb.org/3/movie/top_rated";


    /**
     * Building the movieDB URL
     */
    public static URL buildURL(String request){

        URL url = null;
        /* form Url */
        try{
            url = new URL(request);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    /**
     * Return response from movieDB
     */
    public static String getMovieDBResponse(URL url) {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code : " + httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving The Movie JSON results", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            try {
                line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem reading The JSON Response", e);
            }
        }
        return output.toString();
    }

    public static String requestUrlForMovieList(String sort) {

        String sortURL = MOVIE_DB_POPULARITY_URL;

        if(sort.equals("top_rated")){
            sortURL = MOVIE_DB_TOP_RATED_URL;
        }

        Uri.Builder uriBuilder = Uri.parse(sortURL + MOVIE_DB_API_KEY)
                .buildUpon();
        return uriBuilder.toString();
    }

}
