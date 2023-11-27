package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.Produtos;

import java.util.ArrayList;

public class ProdutosDao implements GenericDao<Produtos>{

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private String tableName = "PRODUTOS";
    private String[]colunas = {"CODIGOPRODUTO", "DESCRICAOPRODUTO", "VALORPRODUTO"};

    private Context context;

    private static ProdutosDao instancia;

    public static ProdutosDao getInstancia(Context context){
        if (instancia == null) {
            return instancia = new ProdutosDao(context);
        } else {
            return instancia;
        }
    }

    public ProdutosDao (Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "PONTODEVENDA",
                null, 1);

        bd = openHelper.getWritableDatabase();
    }
    @Override
    public long insert(Produtos obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigoProduto());
            valores.put(colunas[1], obj.getDescricaoProduto());
            valores.put(colunas[2], obj.getValorProduto());

            return bd.insert(tableName, null, valores);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ProdutosDao.insert() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(Produtos obj) {

        try {

            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getDescricaoProduto());
            valores.put(colunas[2], obj.getValorProduto());

            String[]identificador = {String.valueOf(obj.getCodigoProduto())};
            bd.update(tableName, valores, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ProdutosDao.update " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Produtos obj) {

        try {
            String[]identificador = {String.valueOf(obj.getCodigoProduto())};

            return bd.delete(tableName, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ClienteDao.delete " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Produtos> getAll() {
        ArrayList<Produtos> lista = new ArrayList<Produtos>();

        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null, null, null,
                    colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Produtos produtos = new Produtos();
                    produtos.setCodigoProduto(cursor.getInt(0));
                    produtos.setDescricaoProduto(cursor.getString(1));
                    produtos.setValorProduto(cursor.getInt(2));

                    lista.add(produtos);
                } while (cursor.moveToNext());

            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ProdutosDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Produtos getById(int id) {

        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = bd.query(tableName, colunas, colunas[0]+" = ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {
                Produtos produtos = new Produtos();
                produtos.setCodigoProduto(cursor.getInt(0));
                produtos.setDescricaoProduto(cursor.getString(1));
                produtos.setValorProduto(cursor.getInt(2));

                return produtos;
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ProdutosDao.getById() " + ex.getMessage());
        }

        return null;
    }

    public int getLast() {
        return this.getAll().size();
    }
}