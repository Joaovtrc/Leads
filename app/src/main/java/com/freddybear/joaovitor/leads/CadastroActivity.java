package com.freddybear.joaovitor.leads;

import android.os.Bundle;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.freddybear.joaovitor.leads.dominio.entidades.Entrevistado;
import com.freddybear.joaovitor.leads.dominio.facade.EntrevistadoFacade;

public class CadastroActivity extends AppCompatActivity {

    //Layout
    private EditText edtNome;
    private EditText edtTelefone;
    private ConstraintLayout layoutContentCadastro;

    //Objetos
    private Entrevistado entrevistado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);

        layoutContentCadastro = (ConstraintLayout)findViewById(R.id.layoutContentCadastro);



    }


    //Adicionar ao BD
    private void confirmar(){
        entrevistado = new Entrevistado();
        if (validarCampos() == false){
            EntrevistadoFacade entrevistadoFacade = new EntrevistadoFacade();
            entrevistadoFacade.inserir(this,entrevistado);
            finish();

        }
    }

    //Métodos de validação de campos
    private boolean validarCampos(){
        boolean aviso;

        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();

        entrevistado.setNome(nome);
        entrevistado.setTelefone(telefone);

        if (aviso = isCampoVazio(nome)){
            edtNome.requestFocus();
        }else{
            if (aviso = isCampoVazio(telefone)) {
                edtTelefone.requestFocus();
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





    //Menu de toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_cadastro, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){

            case android.R.id.home:
                finish();
            break;

            case R.id.action_cadastar:
                confirmar();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
