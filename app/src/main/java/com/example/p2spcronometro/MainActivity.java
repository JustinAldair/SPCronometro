package com.example.p2spcronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int seg = 0, nvuleta = 1;
    private TextView time;
    private boolean reanudar = true;
    private List<String> Vuelta = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.tvTiempo);
        Button btnReanudar = findViewById(R.id.btnPausa);
        Button btnBandera = findViewById(R.id.btnRegistrar);
        Button btnReiniciarTiempo = findViewById(R.id.btnReiniciarCro);
        Button btnReiniciarLista = findViewById(R.id.btnReiniciarLis);

        btnReanudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reanudar(view);
            }
        });

        btnBandera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bandera(view);
            }
        });

        btnReiniciarTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarTiempo(view);
            }
        });

        btnReiniciarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarLista(view);
            }
        });

        comenzar();
    }

    public void reanudar(View view) {
        if (reanudar) {
            reanudar = false;
        } else {
            reanudar = true;
        }
    }

    public void comenzar() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int horas = seg / 3600;
                int min = (seg % 3600) / 60;
                int segun = seg % 60;
                String periodo = String.format("%02d:%02d:%02d", horas, min, segun);
                time.setText(periodo);
                if (reanudar) {
                    seg++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void bandera(View view) {
        String tiempo = time.getText().toString();
        String tiempoConVuelta = nvuleta + ".- " + tiempo;
        Vuelta.add(tiempoConVuelta);
        nvuleta++;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Vuelta);
        ListView listView = findViewById(R.id.LvTiempo);
        listView.setAdapter(adapter);
    }

    public void reiniciarTiempo(View view) {
        seg = 0;
        time.setText("00:00:00");
    }

    public void reiniciarLista(View view) {
        Vuelta.clear();
        nvuleta = 1;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Vuelta);
        ListView listView = findViewById(R.id.LvTiempo);
        listView.setAdapter(adapter);
    }
}