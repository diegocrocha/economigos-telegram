package com.economigos.economigostelegram.dominio;

public class Gasto {
    private Double valor;
    private String descricao, categoria, conta;

    public Gasto(Double valor, String descricao, String categoria, String conta) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.conta = conta;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
