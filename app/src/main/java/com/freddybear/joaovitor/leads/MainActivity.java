package com.freddybear.joaovitor.leads;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.freddybear.joaovitor.leads.dominio.entidades.Entrevistado;
import com.freddybear.joaovitor.leads.dominio.facade.EntrevistadoFacade;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView lstEntrevistados;
    private FloatingActionButton fab;
    private ConstraintLayout layoutContentMain;

    //Banco de dados
    private EntrevistadoFacade entrevistadoFacade;


    private EntrevistadoAdapter entrevistadoAdapter;
    private List<Entrevistado> entrevistados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        lstEntrevistados =(RecyclerView)findViewById(R.id.lstEntrevistados);

        //Esconder e mostrar FAB no scroll
        lstEntrevistados.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 1)
                    fab.hide();
                else if (dy < 1)
                    fab.show();
            }
        });

        layoutContentMain = (ConstraintLayout)findViewById(R.id.layoutContentMain);



        lstEntrevistados.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstEntrevistados.setLayoutManager(linearLayoutManager);

        entrevistadoFacade     = new EntrevistadoFacade();
        entrevistados          = entrevistadoFacade.listarTodos(this);


        entrevistadoAdapter = new EntrevistadoAdapter(MainActivity.this,entrevistados);
        lstEntrevistados.setAdapter(entrevistadoAdapter);

    }



    public void Cadastrar(View view){
        Intent it = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(it);
    }

    @Override
    public void onResume() {
        super.onResume();
        entrevistadoAdapter.swapItems(entrevistadoFacade.listarTodos(this));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        MenuItem menuItem = menu.findItem(R.id.action_pesquisar);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String pesquisa) {
        pesquisa = pesquisa.toLowerCase();
        List<Entrevistado> listaPesquisa = new ArrayList<>();

        for(Entrevistado entrevistado : entrevistadoFacade.listarTodos(this)){
            String nome = entrevistado.getNome().toLowerCase();
            if(nome.contains(pesquisa))
                listaPesquisa.add(entrevistado);
        }

        entrevistadoAdapter.swapItems(listaPesquisa);
        return true;
    }
}
