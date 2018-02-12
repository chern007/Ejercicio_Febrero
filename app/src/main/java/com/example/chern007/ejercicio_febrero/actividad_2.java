package com.example.chern007.ejercicio_febrero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class actividad_2 extends AppCompatActivity {

    String eleccion;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_2);

        imageView = (ImageView) findViewById(R.id.imageView);

        eleccion = getIntent().getExtras().getString("imagen");

        cambiaFoto(eleccion);
    }


    private void cambiaFoto(String eleccion) {


        switch (eleccion) {


            case "Imagen Fibra":

                imageView.setImageResource(R.drawable.optical_fiber);

                break;

            case "Imagen Fútbol":

                imageView.setImageResource(R.drawable.futbol);

                break;

            case "Fórmula 1":

                imageView.setImageResource(R.drawable.f11);

                break;
            default:

                imageView.setImageResource(R.drawable.optical_fiber);

                break;


        }


    }

}
