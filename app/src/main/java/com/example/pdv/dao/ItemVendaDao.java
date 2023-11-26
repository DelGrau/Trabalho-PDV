package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.ItemVenda;

import java.util.ArrayList;

public class ItemVendaDao implements GenericDao<ItemVenda> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private String tableName = "ITEMVENDA";
    private String[]colunas = {"CODIGOPEDIDO", "CODIGOPRODUTO",
            "QUANTIDADEPRODUTO", "VALORUNITARIO"};

    private Context context;

    private static ItemVendaDao instancia;

    public static ItemVendaDao getInstancia(Context context){
        if (instancia == null) {
            return instancia = new ItemVendaDao(context);
        } else {
            return instancia;
        }
    }

    public ItemVendaDao (Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "PONTODEVENDA",
                null, 1);

        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(ItemVenda obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigoPedido());
            valores.put(colunas[1], obj.getCodigoProduto());
            valores.put(colunas[2], obj.getQuantidadeProduto());
            valores.put(colunas[3], obj.getValorUnitario());

            return bd.insert(tableName, null, valores);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ItemVendaDao.insert() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(ItemVenda obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getCodigoProduto());
            valores.put(colunas[2], obj.getQuantidadeProduto());
            valores.put(colunas[3], obj.getValorUnitario());

            String[]identificador = {String.valueOf(obj.getCodigoPedido())};
            bd.update(tableName, valores, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ItemVendaDao.update() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(ItemVenda obj) {

        try {
            String[]identificador = {String.valueOf(obj.getCodigoPedido())};

            return bd.delete(tableName, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ItemVendaDao.delete() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList getAll() {
        ArrayList<ItemVenda> lista = new ArrayList<ItemVenda>();

        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null, null, null,
                    colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    ItemVenda itemVenda = new ItemVenda();
                    itemVenda.setCodigoPedido(cursor.getInt(0));
                    itemVenda.setCodigoProduto(cursor.getInt(1));
                    itemVenda.setQuantidadeProduto(cursor.getInt(2));
                    itemVenda.setValorUnitario(cursor.getInt(3));

                    lista.add(itemVenda);
                } while (cursor.moveToNext());

            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ItemVendaDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public ItemVenda getById(int id) {

        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = bd.query(tableName, colunas, colunas[0]+" = ?", identificador,
                    null, null, null);

            if (cursor.moveToNext()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setCodigoPedido(cursor.getInt(0));
                itemVenda.setCodigoProduto(cursor.getInt(1));
                itemVenda.setQuantidadeProduto(cursor.getInt(2));
                itemVenda.setValorUnitario(cursor.getInt(3));

                return itemVenda;
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ItemVendaDao.getById "+ ex.getMessage());
        }

        return null;
    }
}
