package com.example.administrador.listviewxml;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.Collator;
import java.util.Locale;

public class Juego implements Comparable<Juego>, Parcelable {
    private String nombre;
    private String publicacion;
    private String informacion;
    private String tipo;
    private String foto;
    private int dificultad;
    private int puntuacion;
    private boolean expansion;

    public static final Parcelable.Creator<Juego> CREATOR = new Parcelable.Creator<Juego>() {
        @Override
        public Juego createFromParcel(Parcel parcel) {
            return new Juego(parcel);
        }

        @Override
        public Juego[] newArray(int i) {
            return new Juego[i];
        }
    };

    public Juego() {
    }

    public Juego(Parcel parcel) {
        this.nombre = parcel.readString();
        this.publicacion = parcel.readString();
        this.informacion = parcel.readString();
        this.tipo = parcel.readString();
        this.foto = parcel.readString();
        this.dificultad = parcel.readInt();
        this.puntuacion = parcel.readInt();
        this.expansion = parcel.readByte() == 1;
    }

    public Juego(String nombre, String publicacion, String informacion, String tipo, String foto, int dificultad, int puntuacion, boolean expansion) {
        this.nombre = nombre;
        this.publicacion = publicacion;
        this.informacion = informacion;
        this.tipo = tipo;
        this.foto = foto;
        this.dificultad = dificultad;
        this.puntuacion = puntuacion;
        this.expansion = expansion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isExpansion() {
        return expansion;
    }

    public void setExpansion(boolean expansion) {
        this.expansion = expansion;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return nombre + "\n" + tipo + "\n" + informacion + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Juego juego = (Juego) o;

        if (!publicacion.equals(juego.publicacion)) return false;
        if (!nombre.equals(juego.nombre)) return false;
        return tipo.equals(juego.tipo);
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + publicacion.hashCode();
        result = 31 * result + tipo.hashCode();
        return result;
    }

    @Override
    public int compareTo(Juego j) {
        Collator collator = Collator.getInstance(Locale.getDefault());
        int compara = collator.compare(this.nombre, j.nombre);
        if (compara == 0) {
            compara = this.publicacion.compareTo(j.publicacion) >= 0 ? 0 : 1;
        }
        if (compara == 0){
            compara = this.tipo.compareTo(j.tipo) >=0 ? 0 : 1;
        }
        return compara;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(publicacion);
        parcel.writeString(informacion);
        parcel.writeString(tipo);
        parcel.writeString(foto);
        parcel.writeInt(dificultad);
        parcel.writeInt(puntuacion);
        parcel.writeByte((byte) (expansion ? 1 : 0));
    }
}
