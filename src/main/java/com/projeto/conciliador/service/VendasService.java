package com.projeto.conciliador.service;

import com.projeto.conciliador.model.entity.RecebimentoLinha;
import com.projeto.conciliador.model.entity.VendaLinha;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class VendasService {

    @PersistenceContext
    private EntityManager manager;

    public List<String> listaLojas() {
        var sql = "SELECT DES_FANTASIA FROM TAB_LOJA";
        List<String> ret = new ArrayList<>();
        String[] aux ;
        System.out.println("Vai executar query");

        List<String> query = manager.createNativeQuery(sql).getResultList();
        System.out.println("Executou query");

        for (int i = 0; i < query.size(); i++) {
            ret.add(query.get(i));
            System.out.println( query.get(i) );
        }
        return ret;
    }


    @Transactional
    public void alterarTeste() {
        var sql = "UPDATE TAB_LOJA SET DES_FANTASIA = 'TESTE CADASTRO OK' WHERE COD_LOJA = 22";
        try {
            System.out.println("vai executar update");
            Query q = manager.createNativeQuery(sql);
            int rows = q.executeUpdate();
            System.out.println("Rows afected: " + rows);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public Integer conciliarVendas(List<VendaLinha> vendas) {
        String dtaEntrada = null;
        String dtaVencimento = null;
        String valTaxaAdm = "";
        String codChave = "";
        String valParcela = "";
        String codAutorizacaoTef = "";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var sql = "";
        for (int i = 0; i < vendas.size(); i++) {
            sql = "SELECT DTA_ENTRADA, DTA_VENCIMENTO, VAL_TAXA_ADM,COD_CHAVE, VAL_PARCELA, COD_AUTORIZACAO_TEF " +
                    " FROM TAB_FLUXO WHERE COD_LOJA = 1 " +
                    " AND COD_AUTORIZACAO_TEF = " + soNumeros(vendas.get(i).getNumeroDaAutorizacao()) +
                    " AND DTA_EMISSAO = " + formataData(LocalDate.from(vendas.get(i).getDataHoraDaVenda()));
            System.out.println("--------------------------------------");
            System.out.println(sql);
            System.out.println("--------------------------------------");
            //para testes
            //sql = "SELECT DTA_ENTRADA, DTA_VENCIMENTO, VAL_TAXA_ADM, COD_CHAVE, VAL_PARCELA, " +
            //        " COALESCE(COD_AUTORIZACAO_TEF, 0)" +
            //        "FROM TAB_FLUXO WHERE DTA_EMISSAO = '11.9.2023'";

            List query = manager.createNativeQuery(sql).getResultList();
            List<Object[]> resultList = (List<Object[]>) query;

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
