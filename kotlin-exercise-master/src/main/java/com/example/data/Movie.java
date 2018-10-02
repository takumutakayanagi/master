package com.example.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

public class Movie implements Serializable, Comparable<Movie> {

    @NotNull
    private String title;
    @Nullable
    private String distributed;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    private Double rating;

    public Movie(String title, String distributed, LocalDate releaseDate, Double rating) {
        this.title = title;
        this.distributed = distributed;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistributed() {
        return distributed;
    }

    public void setDistributed(String distributed) {
        this.distributed = distributed;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", distributed='" + distributed + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int compareTo(@NotNull Movie other) {
        if (!this.title.equalsIgnoreCase(other.title)) {
            return this.title.compareTo(other.title);
        }
        if (!this.releaseDate.equals(other.releaseDate)) {
            return this.releaseDate.compareTo(other.releaseDate);
        }
        return 0;
    }

}
