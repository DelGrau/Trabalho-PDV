package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.Vendedor;

import java.util.ArrayList;

public class VendedorDao implements GenericDao<Vendedor>{

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;

    private String tableName = "VENDEDOR";
    private String[]colunas = {"CODIGOVENDEDOR", "NOMEVENDEDOR", "LOGINVENDEDOR", "SENHAVENDEDOR"};

    private Context context;

    private static VendedorDao instancia;

    public static VendedorDao getInstancia(Context context){
        if (instancia == null) {
            return instancia = new VendedorDao(context);
        } else {
            return instancia;
        }
    }

    public VendedorDao (Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "PONTODEVENDA",
                null, 1);

        bd = openHelper.getWritableDatabase();
    }
    @Override
    public long insert(Vendedor obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodigoVendedor());
            valores.put(colunas[1], obj.getNomeVendedor());
            valores.put(colunas[2], obj.getLoginVendedor());
            valores.put(colunas[3], obj.getSenhaVendedor());

            return bd.insert(tableName, null, valores);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: VendedorDao.insert() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(Vendedor obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNomeVendedor()));
            valores.put(colunas[2], obj.getLoginVendedor());
            valores.put(colunas[3], obj.getSenhaVendedor());

            String[]identificador = {String.valueOf(obj.getCodigoVendedor())};
            bd.update(tableName, valores, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: VendedorDao.update() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Vendedor obj) {

        try {
            String[]identificador = {String.valueOf(obj.getCodigoVendedor())};

            return bd.delete(tableName, colunas[0]+" = ?", identificador);

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: VendedorDao.delete() " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Vendedor> getAll() {
        ArrayList<Vendedor> lista = new ArrayList<Vendedor>();

        try {
            Cursor cursor = bd.query(tableName, colunas,
                    null, null, null, null,
                    colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Vendedor vendedor = new Vendedor();
                    vendedor.setCodigoVendedor(cursor.getInt(0));
                    vendedor.setNomeVendedor(cursor.getString(1));
                    vendedor.setLoginVendedor(cursor.getString(2));
                    vendedor.setSenhaVendedor(cursor.getString(3));

                    lista.add(vendedor);
                } while (cursor.moveToNext());

            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: VendedorDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Vendedor getById(int id) {

        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = bd.query(tableName, colunas, colunas[0]+" = ?", identificador,
                    null, null, null);

            if (cursor.moveToNext()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setCodigoVendedor(cursor.getInt(0));
                vendedor.setNomeVendedor(cursor.getString(1));
                vendedor.setLoginVendedor(cursor.getString(2));
                vendedor.setSenhaVendedor(cursor.getString(3));

                return vendedor;
            }

        } catch (SQLException ex) {
            Log.e("ERRO", "ERRO: VendedorDao.getById "+ ex.getMessage());
        }

        return null;
    }
}
