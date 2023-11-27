package com.example.pdv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdv.R;
import com.example.pdv.model.Produtos;

import java.util.ArrayList;

public class ProdutosListAdapter extends
        RecyclerView.Adapter<ProdutosListAdapter.ViewHolder> {

    private ArrayList<Produtos> listaProdutos;
    private Context context;

    public ProdutosListAdapter(ArrayList<Produtos> listaProdutos, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_produtos, parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produtos produtoSelecionado = listaProdutos.get(position);
        holder.tvIdProduto.setText(String.valueOf(produtoSelecionado.getCodigoProduto()));
        holder.tvDescProduto.setText(produtoSelecionado.getDescricaoProduto());
        holder.tvValorUnit.setText(String.valueOf(produtoSelecionado.getValorProduto()));
    }

    @Override
    public int getItemCount() {
        return this.listaProdutos.size();
    }

    /**Classe que vincula o componente do xml para ser manipulado**/
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIdProduto;
        private TextView tvDescProduto;
        private TextView tvValorUnit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvIdProduto    = itemView.findViewById(R.id.tvIdProduto);
            this.tvDescProduto  = itemView.findViewById(R.id.tvDescProduto);
            this.tvValorUnit    = itemView.findViewById(R.id.tvValorUnit);
        }
    }

}
