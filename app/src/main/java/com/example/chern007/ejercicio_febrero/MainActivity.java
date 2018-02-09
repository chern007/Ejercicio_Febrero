package com.example.chern007.ejercicio_febrero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button enviarWhatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarWhatsapp = (Button) findViewById(R.id.btEnviar);

        IntentFilter filtro = new IntentFilter();
        filtro.addAction(WIFI_SERVICE);


        new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(null, "LLegó la señal.", Toast.LENGTH_SHORT).show();
            }
        };
    }


    public void enviarWhatsapp(View view) {

        PackageManager pm = getPackageManager();

        Intent waIntent = new Intent(Intent.ACTION_SEND);

        waIntent.setType("text/plain");

        String mensaje = "Prueba mensaje Whatsapp";

        try {
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
            startActivity(Intent.createChooser(waIntent, "Share with"));




        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
        }

    }


}
