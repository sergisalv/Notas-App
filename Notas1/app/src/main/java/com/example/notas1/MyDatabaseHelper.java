package com.example.notas1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Notas.db";
    private static final int DATABASE_VERSION = 1;





     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String query = "CREATE TABLE mis_notas (id INTEGER PRIMARY KEY AUTOINCREMENT, Título TEXT, Descripción TEXT, Categoria TEXT)";

       db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS mis_notas");
        onCreate(db);

    }

    void addnota(String titulo, String descripcion, String categoria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Título", titulo);
        cv.put("Descripción", descripcion);
        cv.put("Categoria", categoria);
        long result = db.insert("mis_notas", null, cv);
        if (result == -1){
            Toast.makeText(context,"Fallo al añadir", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show();
        }



        }

    Cursor readAllData () {
        String query = "SELECT * FROM mis_notas";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
        }

    void updateData(String row_id, String titulo, String descripcion, String categoria) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
        cv.put("Título", titulo);
        cv.put("Descripción", descripcion);
        cv.put("Categoria", categoria);

        long result = db.update("mis_notas", cv, "id=?", new String[] {row_id});
        if(result == -1) {
            Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow (String row_id) {
         SQLiteDatabase db = this.getWritableDatabase();
         long result = db.delete("mis_notas", "id=?", new String [] {row_id});
         if (result == -1) {
             Toast.makeText(context, "Error al borrar", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();
         }
    }

    void deleteAllData () {
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM mis_notas");


    }



    }


