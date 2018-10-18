package com.example.piwi_center.pi_w_i_game;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Simon extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();   //Creacion de Handler que ayudara a crear los lapsos de tiempo necesarios.

    //Creacion de variables globales
    TextView Score;
    ImageView imaVerde, imaRojo, imaAmarillo, imaAzul, imaSimon;
    ImageButton iBVolver;
    MediaPlayer Spotify, Uno, Dos, Tres, Cuatro;
    int Nivel[] = new int[20];
    int Tocado[] = new int[20];
    int niv , ComparaColor, valora, toques, Boton, conta;
    boolean EstaJugando=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        //Canciones que utilizaremos
        Uno = MediaPlayer.create(this, R.raw.uno);
        Dos = MediaPlayer.create(this, R.raw.dos);
        Tres = MediaPlayer.create(this, R.raw.tres);
        Cuatro = MediaPlayer.create(this, R.raw.cuatro);
        Spotify = MediaPlayer.create(this, R.raw.summer);

        //Enlazamiento de objetos
        Score = (TextView)findViewById(R.id.Score);
        imaVerde = (ImageView)findViewById(R.id.imaVerde);
        imaRojo = (ImageView)findViewById(R.id.imaRojo);
        imaAmarillo = (ImageView)findViewById(R.id.imaAmarillo);
        imaAzul = (ImageView)findViewById(R.id.imaAzul);
        imaSimon = (ImageView)findViewById(R.id.imaSimon);
        iBVolver = (ImageButton)findViewById(R.id.iBVolver);

        //Botones principales
        imaSimon.setOnClickListener(this);
        iBVolver.setOnClickListener(this);


        //Invocaion del llamado a aparetar
        imaVerde.setOnClickListener(this);
        imaRojo.setOnClickListener(this);
        imaAmarillo.setOnClickListener(this);
        imaAzul.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == imaSimon)
        {
            EstaJugando=true;
            Notas();
            mHandler.postDelayed(IniciaJuego, 500);
        }
        else if (v == iBVolver)
        {
            finish();
            Spotify.start();
            Spotify.setLooping(true);
        }
        else if ( v == imaVerde)
        {
            if ( EstaJugando == true)
            {
                ComparaColor=1;
                Iluminacion();
                mHandler.postDelayed(ApagaBrillo, 500);
                Tocado[toques]=ComparaColor;
                Comparador();
            }

        }
        else if (v == imaRojo)
        {
            if ( EstaJugando == true )
            {
                ComparaColor=2;
                Iluminacion();
                mHandler.postDelayed(ApagaBrillo, 500);
                Tocado[toques]=ComparaColor;
                Comparador();
            }
        }
        else if (v == imaAmarillo)
        {
            if ( EstaJugando == true )
            {
                ComparaColor=3;
                Iluminacion();
                mHandler.postDelayed(ApagaBrillo, 500);
                Tocado[toques]=ComparaColor;
                Comparador();
            }
        }
        else if (v == imaAzul)
        {
            if ( EstaJugando == true)
            {
                ComparaColor=4;
                Iluminacion();
                mHandler.postDelayed(ApagaBrillo, 500);
                Tocado[toques]=ComparaColor;
                Comparador();
            }
        }
    }

    public Runnable IniciaJuego = new Runnable() {
        @Override
        public void run() {
            //imprimir(); //PRUEBA DE NUMEROS RANDOM
            Score.setText("Nivel: "+niv);
            mHandler.postDelayed(Recorrido,1000);
        }
    };


    public void Notas() //METODO GENERADOR DE MIS NUMEROS ALEATORIOS
    {
        for ( int i=0; i<Nivel.length; i++ ){
            Nivel[i]=(int)(Math.random()*4)+1;
        }
    }

    /*public void imprimir() //NOS MUESTRA EL ARREGLO CREADO
    {
        String prueba = "";
        for ( int i=0; i<Nivel.length; i++ ){
            prueba = prueba+" "+Nivel[i];
        }
        Toast.makeText(getBaseContext(), prueba, Toast.LENGTH_SHORT ).show();

    }*/


    private Runnable Recorrido = new Runnable() {
        @Override
        public void run() {

            Boton = Nivel[valora];

            switch (Boton)
            {
                case 1:
                    ComparaColor = 1;
                    Iluminacion();
                    valora = valora + 1;
                    mHandler.postDelayed(Apagabrillo, 500);
                    break;

                case 2:
                    ComparaColor = 2;
                    Iluminacion();
                    valora = valora + 1;
                    mHandler.postDelayed(Apagabrillo, 500);
                    break;

                case 3:
                    ComparaColor = 3;
                    Iluminacion();
                    valora = valora + 1;
                    mHandler.postDelayed(Apagabrillo, 500);
                    break;

                case 4:
                    ComparaColor = 4;
                    Iluminacion();
                    valora = valora + 1;
                    mHandler.postDelayed(Apagabrillo, 500);
                    break;
            }
        }
    };



    //Genera la "ILUSION XD" del encendio de las luces
    public void Iluminacion ()
    {
        if ( ComparaColor == 1 )
        {
            imaVerde.setColorFilter(Color.argb(255, 0, 255, 0));
            Uno.start();
        }
        else if( ComparaColor == 2 )
        {
            imaRojo.setColorFilter(Color.argb(255,255,0,0));
            Dos.start();
        }
        else if ( ComparaColor == 3 )
        {
            imaAmarillo.setColorFilter(Color.argb(255,255,255,0));
            Tres.start();
        }
        else if ( ComparaColor == 4 )
        {
            imaAzul.setColorFilter(Color.argb(255,0,0,255));
            Cuatro.start();
        }
    }

    //Genera la "ILUSION XD" del apagado de las luces
    private Runnable ApagaBrillo = new Runnable() {
        @Override
        public void run() {

                imaVerde.clearColorFilter();
                imaRojo.clearColorFilter();
                imaAmarillo.clearColorFilter();
                imaAzul.clearColorFilter();
        }
    };

    private Runnable Apagabrillo = new Runnable() {
        @Override
        public void run() {

            imaVerde.clearColorFilter();
            imaRojo.clearColorFilter();
            imaAmarillo.clearColorFilter();
            imaAzul.clearColorFilter();

            if ( valora <= niv)
            {
                mHandler.postDelayed(Recorrido, 500);
            }
            else
            {
                //Toast.makeText(getBaseContext(), "Son todos", Toast.LENGTH_SHORT ).show();
            }
        }
    };

    public void Comparador()
    {
        if (toques <= niv)
        {
            if ( Nivel[conta] == Tocado[toques])
            {
                //Toast.makeText(getBaseContext(), "Continua el juego", Toast.LENGTH_SHORT ).show();
                if (conta == niv)
                {
                    niv = niv+1;
                    //Toast.makeText(getBaseContext(), "Nuevo nivel", Toast.LENGTH_SHORT ).show();
                    toques=-1;
                    valora=0;
                    conta=-1;
                    mHandler.postDelayed(IniciaJuego, 1500);
                }
                conta = conta +1;
                toques = toques+1;
            }
            else
            {
                //Toast.makeText(getBaseContext(), "Lo siento persidte", Toast.LENGTH_SHORT ).show();
                Score.setText("Perdiste :(");
                niv = 0;
                toques=0;
                valora=0;
                conta=-0;
            }
        }
    }
}
