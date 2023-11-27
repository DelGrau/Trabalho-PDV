package com.example.pdv.controller;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.example.pdv.dao.ProdutosDao;
import com.example.pdv.model.Produtos;

import java.util.ArrayList;

public class ProdutosController {

    private Context context;

    public ProdutosController(Context context) {
        this.context = context;
    }

    public String salvarProduto (String descricao, String valor) {
        try {
            int last = ProdutosDao.getInstancia(context).getLast();

            if (descricao.isEmpty() || descricao.equals("")) {
                return "Informe a DESCRIÇÃO do produto!";
            }

            if (valor.isEmpty() || valor.equals("")) {
                return "Informe o VALOR do produto!";
            }

            Produtos produtos = new Produtos();
            produtos.setCodigoProduto(last+1);
            produtos.setDescricaoProduto(descricao);
            produtos.setValorProduto(Integer.parseInt(valor));

            ProdutosDao.getInstancia(context).insert(produtos);
        } catch (Exception ex) {
            return "Erro ao gravar produto.";
        }

        return null;
    }

    public ArrayList<Produtos> retornarTodosProdutos() {
        return ProdutosDao.getInstancia(context).getAll();
    }
}
