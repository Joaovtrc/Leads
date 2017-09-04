package com.freddybear.joaovitor.leads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.freddybear.joaovitor.leads.dominio.entidades.Entrevistado;
import com.freddybear.joaovitor.leads.dominio.facade.EntrevistadoFacade;

public class EntrevistadoEditar extends AppCompatActivity {


    private EditText edtNomeEdt;
    private EditText edtTelefoneEdt;

    //Objetos
    private Entrevistado entrevistado;
    private int EntrevistadoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrevistado_editar);
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Intent com objeto 'Entrevistado'
        Intent intent = getIntent();
        entrevistado = (Entrevistado) intent.getParcelableExtra("entrevistadoIntent");


        //Find e set EditTexts
        edtNomeEdt = (EditText)findViewById(R.id.edtNomeEdt);
        edtNomeEdt.requestFocus();
        edtTelefoneEdt = (EditText)findViewById(R.id.edtTelefoneEdt);
        edtNomeEdt.setText(entrevistado.getNome());
        edtTelefoneEdt.setText(entrevistado.getTelefone());


    }



    //Métodos de validação de campos
    private boolean validarCampos(){
        boolean aviso;

        String nome = edtNomeEdt.getText().toString();
        String telefone = edtTelefoneEdt.getText().toString();

        entrevistado.setNome(nome);
        entrevistado.setTelefone(telefone);

        if (aviso = isCampoVazio(nome)){
            edtNomeEdt.requestFocus();
        }else{
            if (aviso = isCampoVazio(telefone)) {
                edtTelefoneEdt.requestFocus();
            }
        }

        if(aviso){
            Toast.makeText(this, R.string.message_preencher_campos, Toast.LENGTH_LONG).show();
        }

        return aviso;
    }


    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    //Editar no BD
    private void editar(){
        if (validarCampos() == false){
            EntrevistadoFacade entrevistadoFacade = new EntrevistadoFacade();
            entrevistadoFacade.alterar(this,entrevistado);
            Toast.makeText(this, "Entrevistado editado", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    //Menu de toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_editar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){

            case android.R.id.home:
                finish();
            break;

            case R.id.action_editar:
                editar();
            break;


        }

        return super.onOptionsItemSelected(item);
    }


}
