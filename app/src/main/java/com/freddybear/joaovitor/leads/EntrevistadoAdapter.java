package com.freddybear.joaovitor.leads;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freddybear.joaovitor.leads.dominio.entidades.Entrevistado;
import com.freddybear.joaovitor.leads.dominio.facade.EntrevistadoFacade;

import java.util.List;

/**
 * Created by João Vitor on 31/08/2017.
 */

public class EntrevistadoAdapter extends RecyclerView.Adapter<EntrevistadoAdapter.ViewHolderEntrevistado> {

    private Context context;

    private EntrevistadoFacade entreFacade = new EntrevistadoFacade();

    private List<Entrevistado> entrevistadoList;


    public EntrevistadoAdapter(Context context, List<Entrevistado> entrevistadoList){
        this.context = context;
        this.entrevistadoList = entrevistadoList;

    }

    @Override
    public EntrevistadoAdapter.ViewHolderEntrevistado onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_entrevistado_teste, parent,false);
        ViewHolderEntrevistado holderEntrevistado = new ViewHolderEntrevistado(view);

        return holderEntrevistado;
    }

    @Override
    public void onBindViewHolder(final EntrevistadoAdapter.ViewHolderEntrevistado holder, final int position) {

        if( (entrevistadoList != null) && (entrevistadoList.size() > 0) ){

            final Entrevistado entrevistado = entrevistadoList.get(position);
            holder.txtNome.setText(entrevistado.getNome());
            holder.txtTelefone.setText(entrevistado.getTelefone());
            holder.imgOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Menu opções
                    final PopupMenu popupMenu = new PopupMenu(context, holder.itemView, Gravity.RIGHT);
                    popupMenu.inflate(R.menu.menu_context_main);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){
                                //Deletar
                                case R.id.deletar_context_menu:
                                    entreFacade.deletar(context,entrevistado);
                                    entrevistadoList.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Entrevistado deletado", Toast.LENGTH_LONG).show();
                                break;

                                //Editar
                                case R.id.editar_context_menu:
                                    Intent i = new Intent(context, EntrevistadoEditar.class);
                                    i.putExtra("entrevistadoIntent",entrevistado);
                                    context.startActivity(i);
                                break;


                            }
                            return false;
                        }
                    });

                    popupMenu.show();
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(context, "OnLongClick" + entrevistado.getNome(), Toast.LENGTH_SHORT).show();

                    return true;
                }
            });


        }

    }


    public class ViewHolderEntrevistado extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtTelefone;
        public ImageView imgOptions;


        public ViewHolderEntrevistado(View itemView) {
            super(itemView);
            txtNome     = (TextView) itemView.findViewById(R.id.txtNom);
            txtTelefone = (TextView)itemView.findViewById(R.id.txtTel);
            imgOptions  = (ImageView) itemView.findViewById(R.id.imgViewOptions);


        }

    }


    @Override
    public int getItemCount() {return entrevistadoList.size();}


    public void swapItems(List<Entrevistado> entrevistadoList){
        this.entrevistadoList= entrevistadoList;
        notifyDataSetChanged();
    }



}

