package com.example.estacionvl_tc_014.servicios.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ContadorService extends Service {

    public static final String ACTION_START="start";
    public static final String ACTION_PAUSE="pause";
    public static final String ACTION_STOP="stop";

    public ContadorService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction().equals(ACTION_START)){
            Log.i("ServicioContador","Accion Start");
        }else if(intent.getAction().equals(ACTION_PAUSE)){
            Log.i("ServicioContador","Accion Pause");
        }else{
            Log.i("ServicioContador","Accion Stop");
        }

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}
