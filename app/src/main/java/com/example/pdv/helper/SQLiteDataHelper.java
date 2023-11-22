package com.example.pdv.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Classe responsável em criar e manter o banco de dados
 */
public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Classe responsável por criar as tabelas do banco de dados
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE VENDEDOR  (CODIGOVENDEDOR INTEGER, NOMEVENDEDOR VARCHAR(100), LOGINVENDEDOR VARCHAR(100), SENHAVENDEDOR VARCHAR(100))");
        sqLiteDatabase.execSQL("CREATE TABLE PRODUTOS  (CODIGOPRODUTO INTEGER, DESCRICAOPRODUTO VARCHAR(100), VALORPRODUTO DECIMAL)");
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE   (CODIGOCLIENTE INTEGER, NOMECLIENTE VARCHAR(100), DOCUMENTOCLIENTE VARCHAR(100))");
        sqLiteDatabase.execSQL("CREATE TABLE ITEMVENDA (CODIGOPEDIDO INTEGER, CODIGOPRODUTO INTEGER, QUANTIDADEPRODUTO INTEGER, VALORUNITARIO DOUBLE)");
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO    (CODIGOPEDIDO INTEGER, CODIGOCLIENTE INTEGER)");
    }

    /**
     * Responsável por atualizar as tabelas durante a atualização do aplicativo
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
