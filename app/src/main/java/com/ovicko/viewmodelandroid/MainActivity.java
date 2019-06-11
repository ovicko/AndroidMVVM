/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ovicko.viewmodelandroid.model.ApiMessage;
import com.ovicko.viewmodelandroid.model.Hit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageAdapter imageAdapter;
    List<Hit> imagelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ImageViewModel imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        imageAdapter = new ImageAdapter(MainActivity.this,imagelist);
        recyclerView.setAdapter(imageAdapter);

        imageViewModel.getPixaImages().observe(this, new Observer<List<Hit>>() {
            @Override
            public void onChanged(@Nullable List<Hit> hits) {
                imageAdapter.setImageAdapter(hits);
                //Toast.makeText(MainActivity.this, "SIZE_"+tags, Toast.LENGTH_SHORT).show();
            }
        });

        imageViewModel.getError().observe(this, new Observer<ApiMessage>() {
            @Override
            public void onChanged(@Nullable ApiMessage apiMessage) {
                Toast.makeText(MainActivity.this, apiMessage.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
