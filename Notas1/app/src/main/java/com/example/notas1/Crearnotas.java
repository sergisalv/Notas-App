package com.example.notas1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Crearnotas extends AppCompatActivity {
    EditText titulo, descripcion;
    Button b_add;
    Spinner s_categoria;
    final String [] datos = new String[] {"Casa", "Trabajo", "Ocio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearnotas);

        titulo = findViewById(R.id.et_titulo);
        descripcion = findViewById(R.id.et_descripcion);
        s_categoria = (Spinner) findViewById(R.id.s_categoria);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        s_categoria.setAdapter(adaptador);

        b_add = findViewById(R.id.b_add);
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Crearnotas.this);
                myDB.addnota(titulo.getText().toString(), descripcion.getText().toString(),s_categoria.getSelectedItem().toString());

            }
        });



    }
}