package com.freddybear.joaovitor.leads.dominio.facade;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;

import com.freddybear.joaovitor.leads.EntrevistadoAdapter;
import com.freddybear.joaovitor.leads.R;
import com.freddybear.joaovitor.leads.database.DadosOpenHelper;
import com.freddybear.joaovitor.leads.dominio.entidades.Entrevistado;
import com.freddybear.joaovitor.leads.dominio.repositorio.EntrevistadoDAO;

import java.util.List;

/**
 * Created by João Vitor on 31/08/2017.
 */


public class EntrevistadoFacade{

    //BD
    private EntrevistadoDAO entrevistadoDAO;
    private SQLiteDatabase connection;
    private DadosOpenHelper dadosOpenHelper;

    private EntrevistadoAdapter entrevistadoAdapter;

        public void deletar(Context context, Entrevistado entrevistado){
            try{
                criarConexao(context);
                entrevistadoDAO.excluir(entrevistado.getId());

            }catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();
            }
        }

    public void inserir(Context context, Entrevistado entrevistado) {
        try {
            criarConexao(context);
            entrevistadoDAO.inserir(entrevistado);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }

    public void alterar(Context context, Entrevistado entrevistado){
        try {
            criarConexao(context);
            entrevistadoDAO.alterar(entrevistado);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }

    public List<Entrevistado> listarTodos(Context context){
        try {
            criarConexao(context);
            List<Entrevistado> entrevistadoList = entrevistadoDAO.listarEntrevistados();
            return entrevistadoList;
        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
        return null;
    }


        private void criarConexao(Context context){
        try{

            dadosOpenHelper = new DadosOpenHelper(context);

            //Criar conexão
            connection = dadosOpenHelper.getWritableDatabase();
            entrevistadoDAO = new EntrevistadoDAO(connection);

        }catch(SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }
}
