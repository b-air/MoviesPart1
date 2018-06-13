package com.example.android.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    // variables
    private String id;
    private String voteAverage;
    private String title;
    private String posterPath;
    private String imagePath;
    private String overview;
    private String releaseDate;

    // constructor
    public Movie(String idP, String voteAverageP, String titleP, String posterPathP, String imagePathP, String overviewP, String releaseDateP){
        id = idP;
        voteAverage = voteAverageP;
        title = titleP;
        posterPath = posterPathP;
        imagePath = imagePathP;
        overview = overviewP;
        releaseDate = releaseDateP;
    }

    // Get Set methods

    protected Movie(Parcel in) {
        id = in.readString();
        voteAverage = in.readString();
        title = in.readString();
        posterPath = in.readString();
        imagePath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(voteAverage);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(imagePath);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
    }

    // Parcel
    @Override
    public String toString(){
        return "Movie{" +
                "id='" + id + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", title='" + title + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
