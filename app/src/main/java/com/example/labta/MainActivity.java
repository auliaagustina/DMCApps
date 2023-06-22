package com.example.labta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

ImageView menuLaporan,menuRekruitmen,menuPeta,menuBencana;
TextView greetingTextView;

    private RecyclerView recommendationRecyclerView;
    private RecommendationAdapter recommendationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    menuLaporan = findViewById(R.id.menuLaporAktivitas);
    menuRekruitmen = findViewById(R.id.menuRekruitmen);
    menuPeta = findViewById(R.id.menuPeta);
    menuBencana = findViewById(R.id.menuLaporBencana);

    greetingTextView = findViewById(R.id.homeGretting);
    greetingTextView.setText("");


    menuLaporan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    });

    menuRekruitmen.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    });

    menuPeta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    });

    menuBencana.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    });


    Bundle bun = getIntent().getExtras();
    int val = bun.getInt("VAL");
    if (val==1){
        menuLaporan.setVisibility(View.VISIBLE);
        menuRekruitmen.setVisibility(View.VISIBLE);
        menuPeta.setVisibility(View.VISIBLE);
        menuBencana.setVisibility(View.VISIBLE);
    } else if(val==2){
        menuLaporan.setVisibility(View.GONE);
        menuRekruitmen.setVisibility(View.VISIBLE);
        menuPeta.setVisibility(View.VISIBLE);
        menuBencana.setVisibility(View.VISIBLE);
    } else if (val==3){
        menuLaporan.setVisibility(View.VISIBLE);
        menuRekruitmen.setVisibility(View.GONE);
        menuPeta.setVisibility(View.VISIBLE);
        menuBencana.setVisibility(View.VISIBLE);
    }
        Article[] articles = {
                new Article("Judul Artikel 1", "2023-06-01", R.drawable.artikel_1),
                new Article("Judul Artikel 2", "2023-06-02", R.drawable.artikel_2),
                new Article("Judul Artikel 3", "2023-06-03", R.drawable.artikel_3)
        };

        recommendationRecyclerView = findViewById(R.id.recommendationReyclerView);
        recommendationAdapter = new RecommendationAdapter(articles);
        recommendationRecyclerView.setAdapter(recommendationAdapter);
        recommendationRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

}