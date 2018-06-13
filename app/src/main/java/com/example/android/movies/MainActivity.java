package com.example.android.movies;

import android.support.v4.app.LoaderManager;
import android.content.Context;
import android.support.v4.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.movies.models.Movie;
import com.example.android.movies.utilities.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    private MovieAdapter mMovieAdapter;
    private ProgressBar mLoadingIndicator;

    private static final int LOADER_ID_POPULAR = 0;
    private static final int LOADER_ID_TOP_RATED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
           Show Movies
         */
        RecyclerView mMovieList = findViewById(R.id.rv_movies);
        mMovieList.setLayoutManager(new GridLayoutManager(this, 2));
        mMovieList.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        mMovieList.setAdapter(mMovieAdapter);

        if (isConnected()) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        /*
           UI improvement
         */

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        String sortMovies;

        switch (i) {
            case 1:
                sortMovies = "top_rated";
                break;
            default:
                sortMovies = "popular";
        }

        String request = NetworkUtils.requestUrlForMovieList(sortMovies);
        return new MovieLoader(this, request);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        mLoadingIndicator.setVisibility(View.GONE);
        mMovieAdapter.updateItems(movies);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isConnected()) {
            switch (item.getItemId()) {
                case R.id.menu_popular:
                    getSupportLoaderManager().restartLoader(LOADER_ID_POPULAR, null, this);
                    return true;
                case R.id.menu_top_rated:
                    getSupportLoaderManager().restartLoader(LOADER_ID_TOP_RATED, null, this);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
        return false;
    }
}
