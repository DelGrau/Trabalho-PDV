package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        return 0;
    }

    @Override
    public long delete(Cliente obj) {
        return 0;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        return null;
    }

    @Override
    public Cliente getById(int id) {
        return null;
    }
}
