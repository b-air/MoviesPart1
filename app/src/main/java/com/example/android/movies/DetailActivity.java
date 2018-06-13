package com.example.android.movies;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mDisplayTitle = findViewById(R.id.movie_title);
        TextView mDisplayDetails = findViewById(R.id.movie_details);
        TextView mDisplayDate = findViewById(R.id.movie_release_date);
        TextView mDisplayVote = findViewById(R.id.movie_vote_average);
        ImageView mDisplayPoster = findViewById(R.id.movie_poster);

        Movie movieStartedActivity = getIntent().getExtras().getParcelable(Movie.class.getSimpleName());

        mDisplayTitle.setText(movieStartedActivity.getTitle());
        mDisplayDetails.setText(movieStartedActivity.getOverview());
        mDisplayDate.setText(movieStartedActivity.getReleaseDate());
        mDisplayVote.setText(movieStartedActivity.getVoteAverage());

        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/" + movieStartedActivity.getPosterPath()).into(mDisplayPoster);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
