package com.upb.retoxtasis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> participantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alerta");
        builder.setMessage("Mediante la Ley 124 de 1994 se prohíbe el expendio de bebidas alcohólicas a menores de edad y se adoptan medidas respecto de menores que sean encontrados consumiendo tales bebidas o en estado de beodez; estableciendo además que en \"toda publicidad, identificación o promoción sobre bebidas embriagantes se debe hacer referencia expresa a la prohibición establecida en la presente ley\" y adicionalmente, se consagra como deber de los establecimientos que venden bebidas alcohólicas colocar en un lugar visible la prohibición de expendio a menores.");
        AlertDialog dialog = builder.create();
        dialog.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos referencias a los elementos de la interfaz de usuario
        final EditText participantesEditText = findViewById(R.id.participantes);
        Button agregarButton = findViewById(R.id.agregar);
        final TableLayout tablaParticipantes = findViewById(R.id.tableLayout);
        Button continuarButton = findViewById(R.id.continuar);

        // Configuramos el listener para el botón "Agregar"
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos el texto ingresado por el usuario y lo agregamos a la lista de participantes
                String participante = participantesEditText.getText().toString().trim();
                if (participantes.size()<11) {
                    if (!participante.isEmpty()) {
                        participantes.add(participante);
                        // Agregamos una nueva fila a la tabla con el participante agregado
                        TableRow fila = new TableRow(MainActivity.this);
                        TextView textView = new TextView(MainActivity.this);
                        textView.setText(participante);
                        fila.addView(textView);
                        tablaParticipantes.addView(fila);
                        // Limpiamos el EditText
                        participantesEditText.setText("");
                    }
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("El limite de participantes son 10.");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }
    public void Siguiente(View v) {
        // Creamos un Intent para iniciar la actividad GanadorActivity
        Intent intent = new Intent(this, GanadorActivity.class);

        // Pasamos la lista de participantes al Intent
        intent.putStringArrayListExtra("participantes", participantes);

        // Iniciamos la actividad GanadorActivity
        startActivity(intent);
    }



}
