package com.example.notas1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText titulo2, descripcion2;
    Button actualizar, borrar;

    String id;
    String titulo;
    String descripcion;
    String categoria;
    Spinner s_categoria2;
    
    final String [] datos = new String[] {"Casa", "Trabajo", "Ocio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titulo2 = findViewById(R.id.et_titulo2);
        descripcion2 = findViewById(R.id.et_descripcion2);
        actualizar = findViewById(R.id.b_update);
        borrar = findViewById(R.id.b_borrar);

        s_categoria2 = (Spinner) findViewById(R.id.s_categoria2);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        s_categoria2.setAdapter(adaptador);

        getAndSetIntentData();

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                titulo = titulo2.getText().toString();
                descripcion = descripcion2.getText().toString();
                categoria = s_categoria2.getSelectedItem().toString();
                myDB.updateData(id, titulo, descripcion, categoria);
                finish();

            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog ();
            }
        });

        getAndSetIntentData();





    }
    void getAndSetIntentData () {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titulo") && getIntent().hasExtra("descripcion")){
            //Obtener los datos del Intent
            id = getIntent().getStringExtra("id");
            titulo = getIntent().getStringExtra("titulo");
            descripcion = getIntent().getStringExtra("descripcion");


            //Settear los datos del Intent
            titulo2.setText(titulo);
            descripcion2.setText(descripcion);


        }else {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Borrar " + titulo + "?");
        builder.setMessage("¿Esta seguro que quiere borrar " + titulo + "?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


}