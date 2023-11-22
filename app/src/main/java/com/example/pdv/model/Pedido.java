package com.example.pdv.model;

import java.util.ArrayList;

public class Pedido {

    /**
     * Atributos da classe
     * Serão as colunas da tabela no banco de dados
     */
    private int codigoPedido;
    private int codigoCliente;

    public Pedido() {
    }

    /**
     * getters and setters
     */

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
}