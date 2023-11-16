package com.example.pdv.model;

public class Produtos {
    /**
     * Atributos da classe
     */
    private int codigoProduto;
    private String descricaoProduto;
    private double valorProduto;

    public Produtos() {
    }

    /**
     * Getters and Setters
     */
    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
}
