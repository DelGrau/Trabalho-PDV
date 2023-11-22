package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.Pedido;

import java.util.ArrayList;

public class PedidoDao implements GenericDao<Pedido>{
    // Variavel para abrir a conexão com a base de dados
    private SQLiteOpenHelper openHelper;

    // Base de Dados
    private SQLiteDatabase bd;

    // Nome da Tabela
    private String tableName = "PEDIDO";

    // nome das colunas da Tabela
    private String[]colunas = {"CODIGOPEDIDO", "CODIGOCLIENTE"};

    private Context context;

    private static PedidoDao instancia;

    public static PedidoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new PedidoDao(context);
        } else {
            return instancia;
        }
    }

    private PedidoDao(Context context) {
        this.context = context;

        // Abrir uma conexão da Base de Dados
        openHelper = new SQLiteDataHelper(this.context, "PONTODEVENDA",
                null, 1);

        // carrega base de dados e da permissão para escrever na tabela
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigoPedido());
            valores.put(colunas[1], obj.getCodigoCliente());

            return bd.insert(tableName, null, valores);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: PedidoDao.insert() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(Pedido obj) {

        try {

            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getCodigoCliente());

            String[]identificador = {String.valueOf(obj.getCodigoPedido())};
            bd.update(tableName, valores, colunas[0]+" = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: PedidoDao.update() "+ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Pedido obj) {

        try {
            String[]identificador = {String.valueOf(obj.getCodigoPedido())};

            return bd.delete(tableName, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: PedidoDao.delete() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        try {

            Cursor cursor = bd.query(tableName, colunas,
                    null, null, null, null,
                    colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido();
                    pedido.setCodigoPedido(cursor.getInt(0));
                    pedido.setCodigoCliente(cursor.getInt(1));

                    lista.add(pedido);

                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: PedidoDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Pedido getById(int id) {

        try {

            String[]identificador = {String.valueOf(id)};
            Cursor cursor = bd.query(tableName, colunas, colunas[0]+" = ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()){
                Pedido pedido = new Pedido();
                pedido.setCodigoPedido(cursor.getInt(0));
                pedido.setCodigoCliente(cursor.getInt(1));

                return pedido;
            }

        } catch (SQLException ex) {
        Log.e("ERRO", "ERRO: PedidoDao.getById() " + ex.getMessage());
        }

        return null;
    }
}
