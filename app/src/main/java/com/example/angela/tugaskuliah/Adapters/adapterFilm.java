package com.example.angela.tugaskuliah.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.angela.tugaskuliah.R;
import com.example.angela.tugaskuliah.app.appkontrol;

import java.util.List;

public class adapterFilm extends RecyclerView.Adapter<adapterFilm.ViewHolder> {
    private LayoutInflater inflater;
    private List<modelFilm> modelFilms;
    private Activity activity;
    private int layout;
    ImageLoader imageLoader = appkontrol.getInstance().getImageLoader();

    public adapterFilm(List<modelFilm> modelFilms, Activity activity){
        super();
        this.layout = layout;
        this.activity = activity;
        this.modelFilms = modelFilms;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (imageLoader == null)
            imageLoader = appkontrol.getInstance().getImageLoader();
        modelFilm Film = modelFilms.get(position);
        holder.judul.setText(Film.getJudul());
        holder.sinopsis.setText(Film.getSinopsis());
        holder.tanggal.setText(Film.getTanggal());
        holder.gambar.setImageUrl(Film.getGambar(), imageLoader);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView judul, sinopsis, tanggal;
        public NetworkImageView gambar;
        public ViewHolder(View itemView){
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            sinopsis = itemView.findViewById(R.id.sinopsis);
            tanggal = itemView.findViewById(R.id.tanggal);
            gambar = itemView.findViewById(R.id.thumnile);
        }
    }

    public modelFilm getItemAtPosition(int position){
        return modelFilms.get(position);
    }

    @Override
    public long getItemId(int position){
        return (position);
    }

    @Override
    public int getItemCount(){
        return modelFilms.size();
    }
}
