package com.upb.retoxtasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GanadorActivity extends AppCompatActivity {

    // Definimos los retos en una matriz de cadenas
    private String[] retos = {
            ", toma un shot por cada mascota que tengas",
            ", toma dos sorbos del trago del jugador a tu derecha",
            ", toma 4 shots si no has leído ningún libro este año",
            ", cuenta chistes en los siguientes 60 segundos y todo jugador que se ría debe tomar un shot",
            ", di la tabla de multiplicación del 7 lo más rápido posible en 10 segundos, si te equivocas o no terminas, toma 2 shots",
            ", si no te gusta tu trabajo, o no trabajas, toma 2 shots",
            ", di el nombre de los padres del jugador de la izquierda, si te equivocas, toma 2 shots",
            ", si tienes una cuenta de TikTok, toma 3 shots",
            ", si tu nombre al revés en menos de 5 segundos, si te equivocas o no lo logras toma 2 shots",
            ", toma 4 shots si vives con algún hermano",
            ", nombra 4 comidas que empiecen por A en menos de 10 segundos, si te equivocas o no lo logras toma 2 shots"
    };

    private TextView contadorTextView;
    private CountDownTimer contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganador);

        // Obtenemos la lista de participantes del Intent
        Intent intent = getIntent();
        ArrayList<String> participantes = intent.getStringArrayListExtra("participantes");

        // Seleccionamos un participante aleatorio
        Random random = new Random();
        String nombreGanador = participantes.get(random.nextInt(participantes.size()));

        // Seleccionamos un reto aleatorio
        String retoSeleccionado = retos[random.nextInt(retos.length)];

        // Mostramos el nombre del ganador y el reto en la vista
        TextView ganadorTextView = findViewById(R.id.ganador);
        ganadorTextView.setText(nombreGanador + retoSeleccionado);

        // Configuramos el listener para el botón "Regresar"
        Button regresarButton = findViewById(R.id.regresarButton);
        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detenemos el contador antes de volver a la vista principal
                contador.cancel();
                finish();
            }
        });

        // Configuramos el temporizador para 30 segundos
        contadorTextView = findViewById(R.id.contador);
        contador = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                contadorTextView.setText("Tiempo restante: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                contadorTextView.setText("Tiempo agotado!");
            }

        }.start();

        // Configuramos el listener para el botón "Continuar"
        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detenemos el contador antes de continuar
                contador.cancel();

                // Seleccionamos un nuevo participante aleatorio
                Random random = new Random();
                String nombreGanador = participantes.get(random.nextInt(participantes.size()));

                // Seleccionamos un nuevo reto aleatorio
                String retoSeleccionado = retos[random.nextInt(retos.length)];

                // Mostramos el nombre del ganador y el reto actualizados en la vista
                TextView ganadorTextView = findViewById(R.id.ganador);
                ganadorTextView.setText(nombreGanador + retoSeleccionado);

                // Reiniciamos el temporizador para 30 segundos
                contador = new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        contadorTextView.setText("Tiempo restante: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        contadorTextView.setText("Tiempo agotado!");
                    }

                }.start();
            }
        });

// Configuramos el listener para el botón "Terminar"
        Button terminarButton = findViewById(R.id.terminarButton);
        terminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detenemos el contador antes de finalizar la aplicación
                contador.cancel();
                finishAffinity();
            }
        });
    }
}


