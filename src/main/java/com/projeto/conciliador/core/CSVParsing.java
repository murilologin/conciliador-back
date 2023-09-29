package com.projeto.conciliador.core;

import com.projeto.conciliador.model.entity.DadosCsv;
import com.projeto.conciliador.model.entity.RecebimentoLinha;
import com.projeto.conciliador.model.entity.VendaLinha;
import com.projeto.conciliador.model.repository.CsvLinhaParse;
import com.projeto.conciliador.service.VendasService;
import com.projeto.conciliador.utils.ManipuladorArquivo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParsing {

    @Autowired
    private VendasService vendasService;

    public List<VendaLinha> lerCsvVenda(String csv, int inicio, String tipo, String appPath) throws IOException {
        List<VendaLinha> vendas = new ArrayList<>();
        Integer contador = 0;

        //System.out.println(csv);
        ManipuladorArquivo.escritor(appPath + "temp.csv", csv);

        BufferedReader buffRead = new BufferedReader(new FileReader(appPath + "temp.csv"));
        String linha = "";
        String[] dados = null;
        while (true) {
            if (linha != null) {
                if (contador >= inicio) {
                    //System.out.println("dados=" + linha);
                    if (!linha.equals("")) {
                        dados = linha.split(";");
                        vendas.add(CsvLinhaParse.csvVendaParse(dados, contador));
                    }
                }
            } else
                break;
            linha = buffRead.readLine();
            contador++;
        }
        buffRead.close();

        return vendas;

    }

    public List<RecebimentoLinha> lerCsvRecebimento(String csv, int inicio, String tipo, String appPath) throws IOException {
        List<RecebimentoLinha> recebimentos = new ArrayList<>();
        Integer contador = 0;

        //System.out.println(csv);
        ManipuladorArquivo.escritor(appPath + "temp.csv", csv);

        BufferedReader buffRead = new BufferedReader(new FileReader(appPath + "temp.csv"));
        String linha = "";
        String[] dados = null;
        while (true) {
            if (linha != null) {
                if (contador >= inicio) {
                    //System.out.println("dados=" + linha);
                    if (!linha.equals("")) {
                        dados = linha.split(";");
                        recebimentos.add(CsvLinhaParse.csvRecebimentoParse(dados, contador));
                    }
                }
            } else
                break;
            linha = buffRead.readLine();
            contador++;
        }
        buffRead.close();

        return recebimentos;

    }


    public List<VendaLinha> lerListaVenda(List<DadosCsv> dadosCsv) throws IOException {
        List<VendaLinha> vendas = new ArrayList<>();
        String[] dados = null;
        for (int i = 0; i < dadosCsv.size(); i++) {
            dados = dadosCsv.get(i).getDados().split(";");
            vendas.add(CsvLinhaParse.csvVendaParse(dados, i));
        }
        return vendas;
    }

    public List<RecebimentoLinha> lerListaRecebimento(List<DadosCsv> dadosCsv) throws IOException {
        List<RecebimentoLinha> recebimentos = new ArrayList<>();
        String[] dados = null;
        for (int i = 0; i < dadosCsv.size(); i++) {
            dados = dadosCsv.get(i).getDados().split(";");
            recebimentos.add(CsvLinhaParse.csvRecebimentoParse(dados, i));
        }
        return recebimentos;
    }


}
