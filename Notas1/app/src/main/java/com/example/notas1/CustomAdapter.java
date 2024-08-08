package com.example.notas1;

import static java.lang.Boolean.valueOf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList nota_id, nota_titulo, nota_descripcion, nota_categoria;







    CustomAdapter(Activity activity, Context context, ArrayList nota_id, ArrayList nota_titulo,
                  ArrayList nota_descripcion, ArrayList nota_categoria){
        this.activity = activity;
        this.context = context;
        this.nota_id = nota_id;
        this.nota_titulo = nota_titulo;
        this.nota_descripcion = nota_descripcion;
        this.nota_categoria = nota_categoria;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);





    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.nota_titulo_txt.setText(String.valueOf(nota_titulo.get(position)));
        holder.nota_descripcion_txt.setText(String.valueOf(nota_descripcion.get(position)));

        if (nota_categoria.get(position).equals("Casa")) {
            holder.icono.setImageResource(R.drawable.ic_casa);
            holder.cl_background.setBackgroundResource(R.drawable.gradientazul);

        } else if (nota_categoria.get(position).equals("Trabajo")) {
            holder.icono.setImageResource(R.drawable.ic_trabajo);
            holder.cl_background.setBackgroundResource(R.drawable.gradientrojo);

        } else if (nota_categoria.get(position).equals("Ocio")) {
            holder.icono.setImageResource(R.drawable.ic_ocio);
            holder.cl_background.setBackgroundResource(R.drawable.gradientverde);
        }












        holder.mainLayaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(nota_id.get(position)));
                intent.putExtra("titulo",String.valueOf(nota_titulo.get(position)));
                intent.putExtra("descripcion",String.valueOf(nota_descripcion.get(position)));



                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return nota_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  nota_titulo_txt, nota_descripcion_txt;
        Spinner s_nota_categoria;
        ImageView icono;
        ConstraintLayout mainLayaout, cl_background;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nota_titulo_txt = itemView.findViewById(R.id.tvnotas_titulo);
            nota_descripcion_txt = itemView.findViewById(R.id.tvnotas_descripcion);
            s_nota_categoria = itemView.findViewById(R.id.s_categoria);
            icono = itemView.findViewById(R.id.iv_icono);
            cl_background = itemView.findViewById(R.id.cl_background);




            mainLayaout = itemView.findViewById(R.id.mainLayout);


        }
    }



}
