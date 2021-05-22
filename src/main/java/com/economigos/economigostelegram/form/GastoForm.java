package com.economigos.economigostelegram.form;

import com.economigos.economigostelegram.service.EconomigosService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GastoForm {
    private String descricao, dataPagamento, contaNome, categoriaNome;
    private Long idConta, idCategoria;
    private Boolean gastoCartao, fixo, pago;
    private Double valor;

    public GastoForm() {
    }

    public GastoForm(Double valor, String descricao, String categoriaNome, String contaNome) {
        this.contaNome = contaNome;
        this.categoriaNome = categoriaNome;
        this.idConta = EconomigosService.requestContaByNome(contaNome).getId();
        this.idCategoria = EconomigosService.requestCategoriaByNome(categoriaNome).getId();
        this.descricao = descricao;
        this.gastoCartao = false;
        this.valor = valor;
        this.fixo = false;
        this.pago = true;
        this.dataPagamento = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getContaNome() {
        return contaNome;
    }

    public void setContaNome(String contaNome) {
        this.contaNome = contaNome;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Boolean getGastoCartao() {
        return gastoCartao;
    }

    public void setGastoCartao(Boolean gastoCartao) {
        this.gastoCartao = gastoCartao;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
