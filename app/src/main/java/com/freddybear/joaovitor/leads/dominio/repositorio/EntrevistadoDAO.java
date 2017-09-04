package com.freddybear.joaovitor.leads.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.freddybear.joaovitor.leads.EntrevistadoAdapter;
import com.freddybear.joaovitor.leads.dominio.entidades.Entrevistado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Vitor on 31/08/2017.
 */

public class EntrevistadoDAO {

    private SQLiteDatabase connection;

    public EntrevistadoDAO(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void inserir(Entrevistado entrevistado){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", entrevistado.getNome());
        contentValues.put("TELEFONE", entrevistado.getTelefone());
        connection.insertOrThrow("ENTREVISTADO", null,contentValues);

    }

    public void excluir(int id){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        connection.delete("ENTREVISTADO","ID = ? ", parametros );

    }

    public void alterar(Entrevistado entrevistado){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", entrevistado.getNome());
        contentValues.put("TELEFONE", entrevistado.getTelefone());

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(entrevistado.getId());

        connection.update("ENTREVISTADO",contentValues,"ID = ? ",parametros);


    }

    public List<Entrevistado> listarEntrevistados(){

        List<Entrevistado> entrevistados = new ArrayList<Entrevistado>();

        //SQL lista
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID, NOME, TELEFONE ");
        sql.append("FROM ENTREVISTADO");
        sql.append(" ORDER BY NOME");

        Cursor resultado = connection.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();

            do{
                Entrevistado entre = new Entrevistado();
                entre.setId(resultado.getInt(resultado.getColumnIndexOrThrow("ID")));
                entre.setNome(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                entre.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("TELEFONE")));

                entrevistados.add(entre);

            }while(resultado.moveToNext());
        }

        return entrevistados;
    }
}
