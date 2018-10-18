package com.example.piwi_center.pi_w_i_game;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button Simon;
    ImageButton Ayuda, Score, Salida, Info;
    MediaPlayer Spotify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Simon = (Button)findViewById(R.id.Simon);
        Ayuda = (ImageButton)findViewById(R.id.Ayuda);
        Score = (ImageButton)findViewById(R.id.Score);
        Salida = (ImageButton)findViewById(R.id.Salida);
        Info = (ImageButton)findViewById(R.id.Info);

        Simon.setOnClickListener(this);
        Ayuda.setOnClickListener(this);
        Score.setOnClickListener(this);
        Salida.setOnClickListener(this);
        Info.setOnClickListener(this);


        Spotify = MediaPlayer.create(this, R.raw.summer);
        Spotify.start();
        Spotify.setLooping(true);
    }

    @Override
    public void onClick(View v) {

        Intent miIntent = null;

        switch ( v.getId()){

            case R.id.Simon:
                Spotify.stop();
                miIntent = new Intent(MainActivity.this, Simon.class);
                break;

            case R.id.Ayuda:
                miIntent = new Intent(MainActivity.this, Calificacion.class);
                break;

            case R.id.Score:
                miIntent = new Intent(MainActivity.this, Score.class);
                break;

            case R.id.Salida:
                finish();
                System.exit(0);
                break;

            case R.id.Info:
                miIntent = new Intent(MainActivity.this, Info.class);
                break;
        }

        startActivity(miIntent);
    }
}
