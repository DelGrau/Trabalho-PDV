package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.appcompat.widget.ViewUtils;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.Cliente;

import java.util.ArrayList;

public class ClienteDao implements GenericDao<Cliente> {

    //abrir conexão com BD
    private SQLiteOpenHelper openHelper;

    // Base de Dados
    private SQLiteDatabase bd;

    // nome da tabela e colunas
    private String tableName = "CLIENTE";
    private String[]colunas = {"CODIGOCLIENTE", "NOMECLIENTE", "DOCUMENTOCLIENTE"};

    private Context context;

    private static ClienteDao instancia;

    public static ClienteDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new ClienteDao(context);
        } else {
            return instancia;
        }
    }

    public ClienteDao(Context context) {
        this.context = context;

        // abrindo conexão da base
        openHelper = new SQLiteDataHelper(this.context, "PONTODEVENDA",
                null, 1);

        //carregando base
        bd = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Cliente obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigoCliente());
            valores.put(colunas[1], obj.getNomeCliente());
            valores.put(colunas[2], obj.getDocumentoCliente());

            return bd.insert(tableName, null, valores);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ClienteDao.insert() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(Cliente obj) {

        try {

            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNomeCliente());
            valores.put(colunas[2], obj.getDocumentoCliente());

            String[]identificador = {String.valueOf(obj.getCodigoCliente())};
            bd.update(tableName, valores, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ClienteDao.update " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Cliente obj) {

        try {
            String[]identrificador = {String.valueOf(obj.getCodigoCliente())};

            return bd.delete(tableName, colunas[0]+" = ?", identrificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ClienteDao.delete " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null, null, null,
                    colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Cliente cliente = new Cliente();
                    cliente.setCodigoCliente(cursor.getInt(0));
                    cliente.setNomeCliente(cursor.getString(1));
                    cliente.setDocumentoCliente(cursor.getString(2));

                    lista.add(cliente);
                } while (cursor.moveToNext());

            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ClienteDao.getAll() "+ex.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente getById(int id) {

        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = bd.query(tableName, colunas, colunas[0]+" = ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(cursor.getInt(0));
                cliente.setNomeCliente(cursor.getString(1));
                cliente.setDocumentoCliente(cursor.getString(2));

                return cliente;
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: ClienteDao.getById() " + ex.getMessage());
        }

        return null;
    }
}
