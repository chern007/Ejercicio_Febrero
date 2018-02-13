package com.example.chern007.ejercicio_febrero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button enviarWhatsapp;
    ImageView imagenPie;
    EditText cambioEstadoWIfi;
    WifiManager wifiManager;
    int estadoWifi;
    IntentFilter filtroBroadSMS;
    BroadcastReceiver escuchadorSMS;
    Spinner spnImagenFondo;

    IntentFilter filtroBroadCargador;
    BroadcastReceiver escuchadorCargador;

    IntentFilter filtroBroadBT;
    BroadcastReceiver escuchadorBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enviarWhatsapp = (Button) findViewById(R.id.btEnviar);
        imagenPie = (ImageView) findViewById(R.id.imgPie);
        cambioEstadoWIfi = (EditText) findViewById(R.id.txtWIFIchanged);
        spnImagenFondo = (Spinner) findViewById(R.id.spnImagenFondo);


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);




        //cargamos los elementos que estan el Strings en el spinner*********************************
        ArrayAdapter<CharSequence> adaptadorSpinner = ArrayAdapter.createFromResource(this,
                R.array.fondosParaImagen, android.R.layout.simple_spinner_item);
        adaptadorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImagenFondo.setAdapter(adaptadorSpinner);
        spnImagenFondo.setOnItemSelectedListener(this);//ponemos a escuchas el spinner
        //******************************************************************************************

        //1)
        filtroBroadSMS = new IntentFilter();
        filtroBroadSMS.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        escuchadorSMS = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                sacaMensaje2();
            }
        };

        //2)
        filtroBroadCargador = new IntentFilter();
        filtroBroadCargador.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        escuchadorCargador = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                cableCONECTADO();
            }
        };

        //3)
        filtroBroadBT = new IntentFilter();
        //filtroBroadBT.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filtroBroadBT.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        escuchadorBT = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                cableCONECTADO();
            }
        };


        String path = Environment.getExternalStorageDirectory().toString();


        estadoWifi = wifiManager.getWifiState();
        Toast.makeText(this, "Estado del WIFI = " + estadoWifi, Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(escuchadorSMS, filtroBroadSMS);
        registerReceiver(escuchadorCargador, filtroBroadCargador);
        registerReceiver(escuchadorBT,filtroBroadBT);

    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(escuchadorSMS);
        unregisterReceiver(escuchadorCargador);
        unregisterReceiver(escuchadorBT);

    }

    public void cableCONECTADO() {

        Toast.makeText(this, "Se ha conectado el cable de carga.", Toast.LENGTH_SHORT).show();
    }




    public void actividad_2(View view) {
        Intent tmp = new Intent();
        tmp.setAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        sendBroadcast(tmp);


        Intent actividad2 = new Intent(this, actividad_2.class);
        actividad2.putExtra("imagen", spnImagenFondo.getSelectedItem().toString());
        startActivity(actividad2);

    }

    public void actividad_3(View view) {

        Intent actividad_3 = new Intent(this, actividad_3.class);
        startActivity(actividad_3);

    }


    public void sacaMensaje() {

        Toast.makeText(this, "Has desactivado el WIFI???", Toast.LENGTH_SHORT).show();
        cambioEstadoWIfi.setText("Has dado al boton de WIFI!");

    }

    public void sacaMensaje2() {

        Toast.makeText(this, "Has recivido un SMS???", Toast.LENGTH_SHORT).show();
    }


    public void activarDesactivarWIFI(View view) {


        switch (view.getId()) {

            case R.id.btWIFIon:

                wifiManager.setWifiEnabled(true);
                Toast.makeText(this, "Se ha activado el WIFI del dispositivo.", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btWIFIoff:

                wifiManager.setWifiEnabled(false);
                Toast.makeText(this, "Se ha desactivado el WIFI del dispositivo.", Toast.LENGTH_SHORT).show();

                break;

        }

    }


    public void enviarWhatsapp(View view) {


        PackageManager pm = getPackageManager();

        Intent waIntent = new Intent(Intent.ACTION_SEND);

        waIntent.setType("text/plain");

        String mensaje = "Prueba mensaje Whatsapp";

        try {
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);//esta linea comprueba si existe la aplicacion whatsapp, la podemos desactivar
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
            startActivity(Intent.createChooser(waIntent, "Share with"));


        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
        }

    }


    //Metodos del Spinner...
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String eleccion = parent.getItemAtPosition(position).toString();

        switch (eleccion) {

            case "Imagen Fibra":

                imagenPie.setImageResource(R.drawable.optical_fiber);

                break;

            case "Imagen Fútbol":

                imagenPie.setImageResource(R.drawable.futbol);

                break;

            case "Fórmula 1":

                imagenPie.setImageResource(R.drawable.f11);

                break;


            default:

                break;

        }

        Toast.makeText(this, "Has seleccionado como image de pie: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

