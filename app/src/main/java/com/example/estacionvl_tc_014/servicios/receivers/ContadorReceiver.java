package com.example.estacionvl_tc_014.servicios.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by EstacionVL-TC-014 on 8/02/16.
 */
public class ContadorReceiver extends BroadcastReceiver{

    public static final String ACTION_CONTADOR="com.example.contador";

    public static final String EXTRA_SECONDS ="segundos";


    public interface ContadorI {
        void onReceiveSeconds(int s);
    }

    ContadorI contadorI;

    public ContadorReceiver(ContadorI contadorI){
        this.contadorI = contadorI;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int seconds = intent.getIntExtra(EXTRA_SECONDS,0);
        contadorI.onReceiveSeconds(seconds);
    }
}
