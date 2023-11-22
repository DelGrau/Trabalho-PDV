package com.example.pdv.model;

public class Vendedor {
    /**
     * Atributos da classe
     */

    private int codigoVendedor;
    private String nomeVendedor;
    private String loginVendedor;
    private String senhaVendedor;

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

    public String getLoginVendedor() {
        return loginVendedor;
    }

    public void setLoginVendedor(String loginVendedor) {
        this.loginVendedor = loginVendedor;
    }

    public String getSenhaVendedor() {
        return senhaVendedor;
    }

    public void setSenhaVendedor(String senhaVendedor) {
        this.senhaVendedor = senhaVendedor;
    }
}
