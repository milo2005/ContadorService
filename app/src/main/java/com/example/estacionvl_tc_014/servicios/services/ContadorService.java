package com.example.estacionvl_tc_014.servicios.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.estacionvl_tc_014.servicios.receivers.ContadorReceiver;

public class ContadorService extends Service {

    public static final String ACTION_START="start";
    public static final String ACTION_PAUSE="pause";
    public static final String ACTION_STOP="stop";

    ContadorHandler contadorHandler;

    boolean running;
    boolean paused;

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("Contador"
                , Thread.MAX_PRIORITY);
        thread.start();
        contadorHandler = new ContadorHandler(thread.getLooper());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction().equals(ACTION_STOP)){
            running = false;
            stopSelf();
        }else if(intent.getAction().equals(ACTION_PAUSE)){
            paused = true;
        }else{

            if(paused && running)
                paused = false;
            else if(!running){
                running = true;
                Message msg =  contadorHandler.obtainMessage();
                msg.obj = intent;
                contadorHandler.sendMessage(msg);
            }


        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    private class ContadorHandler extends Handler{

        public ContadorHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {

            int seconds = 0;

            while(running){
                try {
                    Thread.sleep(1000);
                    if(!paused) {
                        seconds++;

                        Intent intent = new Intent(ContadorReceiver.ACTION_CONTADOR);
                        intent.putExtra(ContadorReceiver.EXTRA_SECONDS, seconds);
                        sendBroadcast(intent);

                        Log.i("Segundos", "Segundos:" + seconds);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
