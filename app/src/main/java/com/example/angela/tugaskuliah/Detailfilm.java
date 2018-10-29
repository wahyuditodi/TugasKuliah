package com.example.angela.tugaskuliah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.angela.tugaskuliah.app.appkontrol;

public class Detailfilm extends AppCompatActivity {

    private ImageLoader image1 = appkontrol.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailfilm);

        init();
    }

    private void init(){
        Intent intent1 = getIntent();
        String judul = intent1.getStringExtra("judul");
        String tanggal = intent1.getStringExtra("tanggal");
        String sinopsis = intent1.getStringExtra("sinopsis");
        String gambar = intent1.getStringExtra("gambar");
        String rating = intent1.getStringExtra("rating");

        TextView tjudul = findViewById(R.id.judul);
        TextView ttanggal = findViewById(R.id.tgl_release);
        TextView trating = findViewById(R.id.rating);
        TextView tsinopsis = findViewById(R.id.sinopsis);
        NetworkImageView tgambar = findViewById(R.id.gambar);

        tjudul.setText(judul);
        ttanggal.setText(tanggal);
        trating.setText(rating);
        tsinopsis.setText(sinopsis);
        tgambar.setImageUrl(gambar, image1);
    }
}
