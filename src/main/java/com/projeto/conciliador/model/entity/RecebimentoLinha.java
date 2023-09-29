package com.projeto.conciliador.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RecebimentoLinha {
    private Integer linha;
    private String codigoCentralizador;
    private String codigo;
    private LocalDate vencimento;
    private LocalDate vencimentoOriginal;
    private String produto;
    private String lancamento;
    private String planoVenda;
    private int parcela;
    private int totalDeParcelas;
    private String cartao;
    private String autorizacao;
    private String numeroCv;
    private String terminal;
    private LocalDate dataVenda;
    private BigDecimal valorOriginal;
    private BigDecimal valorBruto;
    private BigDecimal descontos;
    private BigDecimal liquido;

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public String getCodigoCentralizador() {
        return codigoCentralizador;
    }

    public void setCodigoCentralizador(String codigoCentralizador) {
        this.codigoCentralizador = codigoCentralizador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getVencimentoOriginal() {
        return vencimentoOriginal;
    }

    public void setVencimentoOriginal(LocalDate vencimentoOriginal) {
        this.vencimentoOriginal = vencimentoOriginal;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getPlanoVenda() {
        return planoVenda;
    }

    public void setPlanoVenda(String planoVenda) {
        this.planoVenda = planoVenda;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getTotalDeParcelas() {
        return totalDeParcelas;
    }

    public void setTotalDeParcelas(int totalDeParcelas) {
        this.totalDeParcelas = totalDeParcelas;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String getNumeroCv() {
        return numeroCv;
    }

    public void setNumeroCv(String numeroCv) {
        this.numeroCv = numeroCv;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getDescontos() {
        return descontos;
    }

    public void setDescontos(BigDecimal descontos) {
        this.descontos = descontos;
    }

    public BigDecimal getLiquido() {
        return liquido;
    }

    public void setLiquido(BigDecimal liquido) {
        this.liquido = liquido;
    }
}
