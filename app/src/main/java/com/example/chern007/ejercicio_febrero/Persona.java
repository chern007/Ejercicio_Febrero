package com.example.chern007.ejercicio_febrero;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chern007 on 09/02/2018.
 */

public class Persona implements Parcelable {

    String DNI;
    String nombre;
    int edad;
    int altura;


    public Persona(String DNI, String nombre, int edad, int altura) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.edad = edad;
        this.altura = altura;
    }

    protected Persona(Parcel in) {
        DNI = in.readString();
        nombre = in.readString();
        edad = in.readInt();
        altura = in.readInt();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(DNI);
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeInt(altura);
    }
}
