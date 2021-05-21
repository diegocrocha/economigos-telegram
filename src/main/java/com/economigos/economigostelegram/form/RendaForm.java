package com.economigos.economigostelegram.form;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RendaForm {

    private Long idConta;
    private Long idCategoria;
    private Double valor;
    private Boolean recebido;
    private String descricao;
    private Boolean fixo;
    private String dataPagamento;

    public RendaForm() {
    }

    public RendaForm(Double valor, String descricao) {
        this.idConta = 1L;
        this.idCategoria = 1L;
        this.valor = valor;
        this.recebido = true;
        this.descricao = descricao;
        this.fixo = false;
        this.dataPagamento = LocalDateTime.now().format(DateTimeFormatter.ofPattern("aaaa-MM-dd HH:mm:ss"));
    }

//    formatter = formatter . withLocale ( Locale . INGLÊS );


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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getRecebido() {
        return recebido;
    }

    public void setRecebido(Boolean recebido) {
        this.recebido = recebido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
