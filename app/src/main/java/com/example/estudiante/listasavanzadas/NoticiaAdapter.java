package com.example.estudiante.listasavanzadas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoticiaAdapter extends BaseAdapter{


    ArrayList<Noticia> noticias;
    Activity activity;

    public NoticiaAdapter(Activity activity){

        this.activity = activity;
        noticias = new ArrayList<>();
    }




    @Override
    public int getCount() {
        return noticias.size();
    }

    @Override
    public Object getItem(int i) {
        return noticias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    // generar un renglon por objeto
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = activity.getLayoutInflater();
        // pasa de xml a view

        View renglon = inflater.inflate(R.layout.renglon, null, false);
        TextView item_titulo = renglon.findViewById(R.id.item_noticia);
        TextView item_fecha = renglon.findViewById(R.id.item_fecha);
        TextView item_descripcion = renglon.findViewById(R.id.item_descripcion);
        Button item_llamar = renglon.findViewById(R.id.item_llamar);

        item_titulo.setText(noticias.get(position).getTitulo());
        item_fecha.setText(noticias.get(position).getFecha());
        item_descripcion.setText(noticias.get(position).getDescripcion());
        item_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noticias.remove(position);
                //notifyDataSetChanged();

                final int request_call = 1;
                Intent llamar = new Intent(Intent.ACTION_CALL);
                llamar.setData(Uri.parse("tel:3116435652"));

                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},request_call);

                    } else {
                        activity.startActivity(llamar);
                    }
                }


                //ir a otra actividad
                //Intent intento = new Intent(activity, NoticiaView.class);
                //activity.startActivity(intento);

                // Intent i = new Intent(MainActivity.this,NoticiaView.class);
                // startActivity(i);
            }
        });




        return renglon;
    }

    public void agregarNoticia(Noticia noticia){

        noticias.add(noticia);
        notifyDataSetChanged();

    }

}
