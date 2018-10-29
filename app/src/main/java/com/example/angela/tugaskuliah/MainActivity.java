package com.example.angela.tugaskuliah;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.angela.tugaskuliah.Adapters.adapterFilm;
import com.example.angela.tugaskuliah.Adapters.modelFilm;
import com.example.angela.tugaskuliah.app.appkontrol;
import com.example.angela.tugaskuliah.util.clicklistener;
import com.example.angela.tugaskuliah.util.touchlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String urlfilm = "https://api.themoviedb.org/3/movie/now_playing?api_key=e8cb49992d5e759d3cdc0a533a859c24";
    private RecyclerView recycleFilm;
    private List<modelFilm> modelFilms = new ArrayList<>();
    private adapterFilm adapterFilms;
    private SwipeRefreshLayout swipeFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void onRefresh(){
        refreshFilm();
    }


    private void init(){
        getObject();
        getData();
        getListener();
    }

    private void getObject(){
    recycleFilm = findViewById(R.id.recycle);
    modelFilms = new ArrayList<>();
    adapterFilms = new adapterFilm(modelFilms,this);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
            LinearLayoutManager.VERTICAL, false);
    recycleFilm.setItemAnimator(new DefaultItemAnimator());
    recycleFilm.setHasFixedSize(true);
    recycleFilm.setLayoutManager(layoutManager);
    recycleFilm.setAdapter(adapterFilms);
    recycleFilm.setNestedScrollingEnabled(false);
    swipeFilm = findViewById(R.id.swipe);
    }

    private void getData(){
    swipeFilm.setOnRefreshListener(this);
    swipeFilm.post(new Runnable() {
        @Override
        public void run() {
            swipeFilm.setRefreshing(true);
            refreshFilm();

        }
    });
    }

    private void refreshFilm(){
        swipeFilm.setRefreshing(true);
        JsonObjectRequest reqFilm = new JsonObjectRequest(Request.Method.GET, urlfilm, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONArray film = response.getJSONArray("results");
                            for (int i=0; i<film.length(); i++){
                                JSONObject film2 = film.getJSONObject(i);
                                modelFilm model = new modelFilm();
                                model.setJudul(film2.getString("original_title"));
                                model.setSinopsis(film2.getString("overview"));
                                model.setGambar("http://image.tmdb.org/t/p/w185/"+film2.getString("poster_path"));
                                model.setTanggal(film2.getString("release_date"));
                                model.setRating(film2.getInt("vote_average"));
                                modelFilms.add(model);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "server errors", Toast.LENGTH_SHORT).show();
                        }
                        adapterFilms.notifyDataSetChanged();
                        swipeFilm.setRefreshing(false);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "server errors", Toast.LENGTH_SHORT).show();
                adapterFilms.notifyDataSetChanged();
                swipeFilm.setRefreshing(false);
            }
        });
        appkontrol.getInstance().addToRequestQueue(reqFilm);
    }

    private void getListener(){
    recycleFilm.addOnItemTouchListener(new touchlist(getApplicationContext(), recycleFilm, new clicklistener() {
        @Override
        public void onClick(View view, int position) {
            modelFilm modelFilm1 = modelFilms.get(position);
            String judul = modelFilm1.getJudul();
            String tanggal = modelFilm1.getTanggal();
            String sinopsis = modelFilm1.getSinopsis();
            String gambar = modelFilm1.getGambar();
            Double rating = modelFilm1.getRating();
            String rating2 = Double.toString(rating);
            Intent intents = new Intent(MainActivity.this, Detailfilm.class);
            intents.putExtra("judul", judul);
            intents.putExtra("tanggal", tanggal);
            intents.putExtra("sinopsis", sinopsis);
            intents.putExtra("gambar", gambar);
            intents.putExtra("rating", rating2);

            startActivity(intents);
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    }));
    }
}
