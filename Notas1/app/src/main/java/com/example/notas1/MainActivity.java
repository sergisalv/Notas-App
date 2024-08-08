package com.example.notas1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView imagen_vacio;
    TextView t_vacio;


    MyDatabaseHelper myDB;
    ArrayList<String> nota_id, nota_titulo, nota_descripcion, nota_categoria;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        imagen_vacio = findViewById(R.id.iv_vacio);
        t_vacio = findViewById(R.id.tv_vacio);
        add_button = findViewById(R.id.add_Button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Crearnotas.class);
                startActivity(intent);

            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        nota_id = new ArrayList<>();
        nota_titulo = new ArrayList<>();
        nota_descripcion = new ArrayList<>();
        nota_categoria = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, nota_id, nota_titulo, nota_descripcion, nota_categoria);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays () {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            imagen_vacio.setVisibility(View.VISIBLE);
            t_vacio.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                nota_id.add(cursor.getString(0));
                nota_titulo.add(cursor.getString(1));
                nota_descripcion.add(cursor.getString(2));
                nota_categoria.add(cursor.getString(3));
            }
            imagen_vacio.setVisibility(View.GONE);
            t_vacio.setVisibility(View.GONE);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Borrar_todo){

            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Borrar todas las notas?");
        builder.setMessage("¿Esta seguro que quiere borrar todas las notas?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

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