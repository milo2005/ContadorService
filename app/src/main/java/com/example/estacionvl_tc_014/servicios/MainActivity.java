package com.example.estacionvl_tc_014.servicios;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estacionvl_tc_014.servicios.services.ContadorService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView seconds;
    ImageView stop;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seconds = (TextView) findViewById(R.id.seconds);
        stop = (ImageView) findViewById(R.id.btn_stop);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setTag(false);

        stop.setOnClickListener(this);
        fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ContadorService.class);

        if(v == stop){
            intent.setAction(ContadorService.ACTION_STOP);
        }else{
            Boolean state = (Boolean) fab.getTag();
            fab.setTag(!state);
            if(state){
                intent.setAction(ContadorService.ACTION_PAUSE);
                fab.setImageResource(R.drawable.ic_play);
            }else{
                intent.setAction(ContadorService.ACTION_START);
                fab.setImageResource(R.drawable.ic_pause);
            }

        }

        startService(intent);

    }
}
