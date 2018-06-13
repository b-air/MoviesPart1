package com.example.android.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by bruno on 11/03/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    private List<Movie> mMovies;
    private Context mContext;


    public MovieAdapter(Context context, List<Movie> movies){
        mMovies = movies;
        mContext =context;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mMovieImageView;
        public final TextView mMovieTitle;

        public MovieAdapterViewHolder(View view){
            super(view);
            mMovieImageView = view.findViewById(R.id.movie_image);

            mMovieTitle = view.findViewById(R.id.movie_title);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int test) {
        final int position = movieAdapterViewHolder.getAdapterPosition();
        movieAdapterViewHolder.mMovieTitle.setText(mMovies.get(position).getTitle());

        /* Display movie image with Picasso */
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500/" + mMovies.get(position).getImagePath()).fit().centerCrop().into(movieAdapterViewHolder.mMovieImageView);
        movieAdapterViewHolder.mMovieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Movie.class.getSimpleName(), mMovies.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Return number items to display
     */
    @Override
    public int getItemCount() {
        if(mMovies == null) return 0;
        return mMovies.size();
    }


    public void updateItems(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }
}
