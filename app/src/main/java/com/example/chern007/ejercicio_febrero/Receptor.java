package com.example.chern007.ejercicio_febrero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receptor extends BroadcastReceiver {
    private final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
    private final String WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
    private final String STATE_CHANGED = "android.net.wifi.STATE_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Has conectado el cable de alimentación", Toast.LENGTH_LONG).show();
        }else if(intent.getAction().equals(WIFI_STATE_CHANGED)){
            Toast.makeText(context, "ALGO HA PASADO CON EL WIFI!!!", Toast.LENGTH_LONG).show();
        }else if(intent.getAction().equals(STATE_CHANGED)){
            Toast.makeText(context, "ALGO HA PASADO CON EL WIFI!!!", Toast.LENGTH_LONG).show();
        }else if(intent.getAction().equals("android.intent.action.AIRPLANE_MODE")){
            Toast.makeText(context, "¡¡¡MODO AVION!!!", Toast.LENGTH_LONG).show();
        }



    }

}


