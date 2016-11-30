package com.example.jean.jcplayersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    private JcPlayerView player;
    private RecyclerView recyclerView;
    private AudioAdapter audioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        player = (JcPlayerView) findViewById(R.id.jcplayer);

        ArrayList<String> urls = new ArrayList<>();
        player.addAudio("Hi, I am an web url :)", "http://www.villopim.com.br/android/Music_01.mp3", this);
//        player.addAudio("Oh man! I am an ASSET file.", getAssets().open(""));
        player.addAudio("Yes! I am an RAW file.", "R.raw.zodiaco.mp3", this);

        adapterSetup();
    }


    public void playAudio(JcAudio jcAudio){
        player.playAudio(jcAudio);
    }

    protected void adapterSetup() {
        audioAdapter = new AudioAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(audioAdapter);
        audioAdapter.setupItems(player.getMyPlaylist());
    }


    @Override
    public void onPause(){
        super.onPause();
        player.createNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.kill();
    }
}