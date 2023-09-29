package com.projeto.conciliador.model.repository;

import com.projeto.conciliador.model.entity.RecebimentoLinha;
import com.projeto.conciliador.model.entity.VendaLinha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvLinhaParse {

    public static RecebimentoLinha csvRecebimentoParse(String[] dados, Integer linha) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        RecebimentoLinha recebimentoLinha = new RecebimentoLinha();
        recebimentoLinha.setLinha(linha);
        recebimentoLinha.setCodigoCentralizador(dados[0]);
        recebimentoLinha.setCodigo(dados[1]);
        recebimentoLinha.setVencimento(LocalDate.parse(dados[2], formatterDate));
        recebimentoLinha.setVencimentoOriginal(LocalDate.parse(dados[3], formatterDate));
        recebimentoLinha.setProduto(dados[4]);
        recebimentoLinha.setLancamento(dados[5]);
        recebimentoLinha.setPlanoVenda(dados[6]);
        recebimentoLinha.setParcela(Integer.parseInt(dados[7]));
        recebimentoLinha.setTotalDeParcelas(Integer.parseInt(dados[8]));
        recebimentoLinha.setCartao(dados[9]);
        recebimentoLinha.setAutorizacao(dados[10]);
        recebimentoLinha.setNumeroCv(dados[11]);
        recebimentoLinha.setTerminal(dados[12]);
        recebimentoLinha.setDataVenda(LocalDate.parse(dados[13], formatterDate));
        recebimentoLinha.setValorOriginal(converteNumero(dados[14]));
        recebimentoLinha.setValorBruto(converteNumero(dados[15]));
        recebimentoLinha.setDescontos(converteNumero(dados[16]));
        recebimentoLinha.setLiquido(converteNumero(dados[17]));
        return recebimentoLinha;
    }

    public static VendaLinha csvVendaParse(String[] dados, Integer linha) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        VendaLinha vendaLinha = new VendaLinha();
        vendaLinha.setLinha(linha);
        vendaLinha.setCodigoEstabelecimento(dados[0]);
        vendaLinha.setCartoes(dados[1]);
        vendaLinha.setDataHoraDaVenda(LocalDateTime.parse(dados[3], formatter));
        vendaLinha.setDataPrevistaPagamento(LocalDate.parse(dados[4], formatterDate));
        vendaLinha.setNumeroDoCartao(dados[5]);
        vendaLinha.setNumeroDaAutorizacao(dados[6]);
        vendaLinha.setNumeroComprovanteVendas(dados[7]);
        vendaLinha.setNumeroDoTerminal(dados[9]);
        vendaLinha.setDescricaoDoLancamento(dados[11]);
        vendaLinha.setTotalDeParcelas(Integer.parseInt(dados[14]));
        //System.out.println("colunas=" + dados.length);
        //System.out.println("linha=" + linha + " " +
        //        dados[15].replaceAll("R","").replaceAll("\\$","") + " | " +
        //        dados[17].replaceAll("R","").replaceAll("\\$","") + " | " +
        //        dados[19].replaceAll("R","").replaceAll("\\$",""));
        vendaLinha.setValorBruto(converteNumero(dados[15]));
        vendaLinha.setValorDaTaxaTarifa(converteNumero(dados[17]));
        vendaLinha.setValorLiquido(converteNumero(dados[19]));
        vendaLinha.setEmissorCartao(dados[24]);
        return vendaLinha;
    }

    public static BigDecimal converteNumero(String numero) {
        numero = numero.replaceAll(" ","");
        numero = numero.replaceAll("R","");
        numero = numero.replaceAll("\\$","");
        numero = numero.replaceAll("\\.","");
        numero = numero.replaceAll(",",".");
        return new BigDecimal(numero);
    }

}
