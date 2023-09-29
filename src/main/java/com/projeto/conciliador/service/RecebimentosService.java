package com.projeto.conciliador.service;

import com.projeto.conciliador.model.entity.RecebimentoLinha;
import com.projeto.conciliador.model.entity.VendaLinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class RecebimentosService {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Integer conciliarRecebimentos(List<RecebimentoLinha> recebimentos) {
        String codChave = "";
        //final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var sql = "";
        for (int i = 0; i < recebimentos.size(); i++) {
            sql = "SELECT COD_CHAVE FROM TAB_FLUXO " +
                  " INNER JOIN TAB_LOJA ON TAB_FLUXO.COD_LOJA = TAB_LOJA.COD_LOJA  " +
                  " WHERE " +
                  " TAB_LOJA.NUM_CGC = '61730818000154'\n" +
                  " AND TAB_FLUXO.DTA_EMISSAO = " + formataData(LocalDate.from(recebimentos.get(i).getDataVenda())) +
                  " AND TAB_FLUXO.COD_AUTORIZACAO_TEF = '1234546'";

            System.out.println(recebimentos.get(i).getCodigo() + "|" + recebimentos.get(i).getAutorizacao() +
                    "|" + recebimentos.get(i).getDataVenda() + "|" + formataData(LocalDate.from(recebimentos.get(i).getDataVenda())));

            //System.out.println("--------------------------------------");
            //System.out.println(sql);
            //System.out.println("--------------------------------------");
            //para testes
            //sql = "SELECT DTA_ENTRADA, DTA_VENCIMENTO, VAL_TAXA_ADM, COD_CHAVE, VAL_PARCELA, " +
            //        " COALESCE(COD_AUTORIZACAO_TEF, 0)" +
            //        "FROM TAB_FLUXO WHERE DTA_EMISSAO = '11.9.2023'";

            List query = manager.createNativeQuery(sql).getResultList();
            List<Object[]> resultList = (List<Object[]>) query;
            /*
            for (int x = 0; x < resultList.size(); x++) {
                dtaEntrada = formataDataSql((Timestamp) resultList.get(x)[0]);
                dtaVencimento = formataDataSql((Timestamp) resultList.get(x)[1]);
                valTaxaAdm = formataNumeroSql(resultList.get(x)[2].toString());
                codChave = resultList.get(x)[3].toString();
                valParcela = formataNumeroSql(resultList.get(x)[4].toString());
                codAutorizacaoTef = resultList.get(x)[5].toString();

                if (!dtaVencimento.equals(formataDataObj(vendas.get(i).getDataPrevistaPagamento())) ||
                        !new BigDecimal(valTaxaAdm).equals(vendas.get(i).getValorDaTaxaTarifa())) {
                    System.out.println(dtaVencimento + "<>" + formataDataObj(vendas.get(i).getDataPrevistaPagamento()));
                    System.out.println(valTaxaAdm + "<>" + vendas.get(i).getValorDaTaxaTarifa());

                    try {
                        sql = "UPDATE TAB_FLUXO " +
                                " SET DTA_VENCIMENTO = '" + formataDataObj(vendas.get(i).getDataPrevistaPagamento()) + "', " +
                                " DTA_ENTRADA = '" + formataDataObj(LocalDate.from(vendas.get(i).getDataHoraDaVenda())) + "'," +
                                " VAL_TAXA_ADM = " + vendas.get(i).getValorDaTaxaTarifa()  + " " +
                                " WHERE COD_CHAVE = " + codChave;
                        System.out.println(sql);
                        Query q = manager.createNativeQuery(sql);
                        int rows = q.executeUpdate();
                        System.out.println("Rows afected: " + rows);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }

                } else {
                    System.out.println(dtaVencimento + "==" + formataDataObj(vendas.get(i).getDataPrevistaPagamento()));
                    System.out.println(valTaxaAdm + "==" + vendas.get(i).getValorDaTaxaTarifa());
                }
            }

             */
        }
        return 1;
    }


    static String formataData(LocalDate data) {
        int day = data.getDayOfMonth();
        int month = data.getMonthValue();
        int year = data.getYear();
        return "'" + day + "." + month + "." + year + "'";
    }

    static String formataDataSql(Timestamp dt) {
        var teste = dt.toString().split("-");
        var teste2 = teste[2].split(" ");
        //2023-09-11 00:00:00.0
        return teste2[0] + "." + teste[1] + "." + teste[0];
    }

    static String formataDataObj(LocalDate dt) {
        var data = dt.toString().split("-");
        return data[2] + "." + data[1] + "." + data[0];
    }

    static String soNumeros(String str) {
        str = str.replaceAll("[^0-9]", ""); // regular expression
        str = str.replaceAll(" +", " ");
        if (str.trim().equals("")) {
            str = "0";
        }
        return str;
    }

    public static String formataNumeroSql(String numero) {
        numero = numero.replaceAll(" ","");
        numero = numero.replaceAll("\\.","");
        numero = numero.replaceAll(",",".");
        return numero;
    }

}
