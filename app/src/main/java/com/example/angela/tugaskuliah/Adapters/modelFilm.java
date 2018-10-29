package com.example.angela.tugaskuliah.Adapters;

public class modelFilm {
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    private String judul,sinopsis, gambar, tanggal;
    private double rating;
    public modelFilm (String judul, String sinopsis, String gambar, String tanggal, double rating){
        this.judul = judul;
        this.sinopsis = sinopsis;
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.rating = rating;
    }

    public modelFilm(){

    }
}
