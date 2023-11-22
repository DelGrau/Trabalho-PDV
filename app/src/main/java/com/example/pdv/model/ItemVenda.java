package com.example.pdv.model;

public class ItemVenda {

    private int codigoVenda;
    private int codigoPedido;
    private double quantidadeProduto;
    private double valorUnitario;

    public ItemVenda() {
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
