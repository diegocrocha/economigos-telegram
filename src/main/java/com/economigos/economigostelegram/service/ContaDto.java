package com.economigos.economigostelegram.service;

public class ContaDto {

    private Long id;
    private String banco;
    private String apelido;
    private Double valorAtual;

    public ContaDto() {
    }

    public ContaDto(Long id, String banco, String apelido, Double valorAtual) {
        this.id = id;
        this.banco = banco;
        this.apelido = apelido;
        this.valorAtual = valorAtual;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

}