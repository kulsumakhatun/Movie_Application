package com.niit.MovieService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

@Document
public class Movie {
    private String backdrop_path;
    @Id
    private int id;
    private String title;
    private String release_date;
    private int[] genre_ids;
    private String original_language;
    private String overview;
    private double vote_average;

    private String poster_path;
    private List<String> keyWords;
    private Map<String,Integer> rating ;
    
    
    public Movie() {
    }

    public Movie(int id, String title){
        this.id = id;
        this.title = title;
    }

    public Movie(int id, String poster_path,Map<String,Integer> rating,String title, String release_date, String backdrop_path, int[] genre_ids, String original_language, String overview, double vote_average, List<String> keyWords) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path ;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.original_language = original_language;
        this.overview = overview;
        this.vote_average = vote_average;
        this.keyWords = keyWords;
        this.rating = rating ;

    }

    public <T> Movie(int i, String twilight, String[] strings, String s, String jhbfvjhdsvy, int[] ints, String tamil, String s1, double v, List<T> asList) {
    }

    public Movie(int i, String twilight, String ncvn, String s, String jhbfvjhdsvy, int[] ints, String english, String s1, double v, List<String> popular) {
    }

    public Map<String, Integer> getRating() {
        return rating;
    }

    public void setRating(Map<String, Integer> rating) {
        this.rating = rating;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "backdrop_path='" + backdrop_path + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", genre_ids=" + Arrays.toString(genre_ids) +
                ", original_language='" + original_language + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", poster_path='" + poster_path + '\'' +
                ", keyWords=" + keyWords +
                ", rating=" + rating +
                '}';
    }
}
