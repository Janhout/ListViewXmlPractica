package com.example.administrador.listviewxml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorLista extends ArrayAdapter<Juego> {

    private Context contexto;
    private ArrayList<Juego> datos;
    private int recurso;
    private static LayoutInflater inflador;

    public AdaptadorLista(Context contexto, int recurso, ArrayList<Juego> datos){
        super(contexto, recurso, datos);
        this.contexto = contexto;
        this.recurso = recurso;
        this.datos = datos;
        this.inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = inflador.inflate(recurso, null);
            vh = new ViewHolder();
            vh.fotoElemento = (ImageView)convertView.findViewById(R.id.fotoElemento);
            vh.nombre = (TextView)convertView.findViewById(R.id.tvNombre);
            vh.fecha = (TextView)convertView.findViewById(R.id.tvFecha);
            vh.puntuacion = (TextView)convertView.findViewById(R.id.tvPuntuacion);
            vh.tipo = (TextView)convertView.findViewById(R.id.tvTipo);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.nombre.setText(datos.get(position).getNombre());
        vh.fecha.setText(String.valueOf(datos.get(position).getPublicacion()));
        vh.puntuacion.setText(contexto.getString(R.string.puntuacion) + " " + String.valueOf(datos.get(position).getPuntuacion()));
        vh.tipo.setText(datos.get(position).getTipo());
        vh.fotoElemento.setTag(position);

        if(datos.get(position).getFoto()!=null) {
            //Si cambio las fotos por Objeto, aqui irá el codigo
        }else{
            String tipo = datos.get(position).getTipo();
            int imagen = Principal.conseguirImagen(contexto, tipo);
            vh.fotoElemento.setImageResource(imagen);
        }

        return convertView;
    }

    public static class ViewHolder{
        public TextView nombre, fecha, puntuacion, tipo;
        public ImageView fotoElemento;
    }
}