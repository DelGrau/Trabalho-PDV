package com.example.pdv.model;

import java.util.ArrayList;

public class Pedido {

    /**
     * Atributos da classe
     * Serão as colunas da tabela no banco de dados
     */
    private int codigoPedido;
    private ArrayList<Produtos> itensPedido;
    private Vendedor vendedor;
    private double valorPedido;

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

    public ArrayList<Produtos> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ArrayList<Produtos> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }
}
