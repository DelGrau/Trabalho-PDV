package com.example.pdv.model;

public class Vendedor {
    /**
     * Atributos da classe
     */

    private int codigoVendedor;
    private String nomeVendedor;

    public Vendedor() {
    }

    /**
     * Getters and Setters
     */

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }
}
