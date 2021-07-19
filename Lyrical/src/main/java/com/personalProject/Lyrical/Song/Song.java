package com.personalProject.Lyrical.Song;

import java.time.LocalDate;

public class Song {
    private long id;
    private String name;
    private String artist;
    private String album;
    private LocalDate releaseDate;
    private String genre;
    private int length;
    private String label;

    public Song() {
    }

    public Song(long id,
                String name,
                String artist,
                String album,
                LocalDate releaseDate,
                String genre,
                int length,
                String label)
    {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.length = length;
        this.label = label;
    }

    public Song(String name,
                String artist,
                String album,
                LocalDate releaseDate,
                String genre,
                int length,
                String label)
    {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.length = length;
        this.label = label;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", length=" + length +
                ", label='" + label + '\'' +
                '}';
    }
}
