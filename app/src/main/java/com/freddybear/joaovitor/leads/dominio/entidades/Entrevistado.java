package com.freddybear.joaovitor.leads.dominio.entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by João Vitor on 30/08/2017.
 */

public class Entrevistado implements Parcelable{

    private int id;
    private String nome;
    private String telefone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Entrevistado() {

    }

    public Entrevistado(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        telefone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(telefone);
    }

    public static final Creator<Entrevistado> CREATOR = new Creator<Entrevistado>() {
        @Override
        public Entrevistado createFromParcel(Parcel in) {
            return new Entrevistado(in);
        }

        @Override
        public Entrevistado[] newArray(int size) {
            return new Entrevistado[size];
        }
    };


}
