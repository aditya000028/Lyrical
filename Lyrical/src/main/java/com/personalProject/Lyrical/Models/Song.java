package com.personalProject.Lyrical.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Song {
    private long id;
    private String name;
    private String artist;
    private String album;
    private String releaseDate;
    private String genre;
    private int length;
    private String labels;
    private String imageURL;
    private String lyrics;

    public Song() {
    }

    public Song(long id,
                String name,
                String artist,
                String album,
                String releaseDate,
                String genre,
                int length,
                String labels,
                String imageURL,
                String lyrics)
    {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.length = length;
        this.labels = labels;
        this.imageURL = imageURL;
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Song(String name,
                String artist,
                String album,
                String releaseDate,
                String genre,
                int length,
                String labels,
                String imageURL,
                String lyrics)
    {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.length = length;
        this.labels = labels;
        this.imageURL = imageURL;
        this.lyrics = lyrics;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", genre='" + genre + '\'' +
                ", length=" + length +
                ", labels=" + labels +
                ", imageURL='" + imageURL + '\'' +
                ", lyrics='" + lyrics + '\'' +
                '}';
    }
}
