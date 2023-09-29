package com.projeto.conciliador.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VendaLinha {
    Integer linha;
    String codigoEstabelecimento;
    String cartoes;
    LocalDateTime dataHoraDaVenda;
    LocalDate dataPrevistaPagamento;
    String numeroDoCartao;
    String numeroDaAutorizacao;
    String numeroComprovanteVendas;
    String numeroDoTerminal;
    String descricaoDoLancamento;
    int totalDeParcelas;
    BigDecimal valorBruto;
    BigDecimal valorDaTaxaTarifa;
    BigDecimal valorLiquido;
    String EmissorCartao;

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public String getCodigoEstabelecimento() {
        return codigoEstabelecimento;
    }

    public void setCodigoEstabelecimento(String codigoEstabelecimento) {
        this.codigoEstabelecimento = codigoEstabelecimento;
    }

    public String getCartoes() {
        return cartoes;
    }

    public void setCartoes(String cartoes) {
        this.cartoes = cartoes;
    }

    public LocalDateTime getDataHoraDaVenda() {
        return dataHoraDaVenda;
    }

    public void setDataHoraDaVenda(LocalDateTime dataHoraDaVenda) {
        this.dataHoraDaVenda = dataHoraDaVenda;
    }

    public LocalDate getDataPrevistaPagamento() {
        return dataPrevistaPagamento;
    }

    public void setDataPrevistaPagamento(LocalDate dataPrevistaPagamento) {
        this.dataPrevistaPagamento = dataPrevistaPagamento;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getNumeroDaAutorizacao() {
        return numeroDaAutorizacao;
    }

    public void setNumeroDaAutorizacao(String numeroDaAutorizacao) {
        this.numeroDaAutorizacao = numeroDaAutorizacao;
    }

    public String getNumeroComprovanteVendas() {
        return numeroComprovanteVendas;
    }

    public void setNumeroComprovanteVendas(String numeroComprovanteVendas) {
        this.numeroComprovanteVendas = numeroComprovanteVendas;
    }

    public String getNumeroDoTerminal() {
        return numeroDoTerminal;
    }

    public void setNumeroDoTerminal(String numeroDoTerminal) {
        this.numeroDoTerminal = numeroDoTerminal;
    }

    public String getDescricaoDoLancamento() {
        return descricaoDoLancamento;
    }

    public void setDescricaoDoLancamento(String descricaoDoLancamento) {
        this.descricaoDoLancamento = descricaoDoLancamento;
    }

    public int getTotalDeParcelas() {
        return totalDeParcelas;
    }

    public void setTotalDeParcelas(int totalDeParcelas) {
        this.totalDeParcelas = totalDeParcelas;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorDaTaxaTarifa() {
        return valorDaTaxaTarifa;
    }

    public void setValorDaTaxaTarifa(BigDecimal valorDaTaxaTarifa) {
        this.valorDaTaxaTarifa = valorDaTaxaTarifa;
    }

    public BigDecimal getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(BigDecimal valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getEmissorCartao() {
        return EmissorCartao;
    }

    public void setEmissorCartao(String emissorCartao) {
        EmissorCartao = emissorCartao;
    }
}
