package com.projeto.conciliador.controller;

import com.projeto.conciliador.core.CSVParsing;
import com.projeto.conciliador.model.entity.DadosCsv;
import com.projeto.conciliador.model.entity.RecebimentoLinha;
import com.projeto.conciliador.model.entity.VendaLinha;
import com.projeto.conciliador.service.RecebimentosService;
import com.projeto.conciliador.service.VendasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/concilia")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class RecebeArquivoController {

    @Autowired
    private VendasService vendasService;

    @Autowired
    private RecebimentosService recebimentosService;

    @Value("${application.path}")
    private String applicationPath;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("teste")
    public ResponseEntity<?> teste() {
        System.out.println(this.applicationPath);
        System.out.println("teste");
        return ResponseEntity.ok().build();
    }

    @GetMapping("banco")
    public List<String> testaBanco() {
        try {
            System.out.println("Testando banco");
            return this.vendasService.listaLojas();
            //this.servico.alterarTeste();
            //return null; // (List<String>) ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null; // (List<String>) ResponseEntity.badRequest().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/arquivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void salvarCsv(@RequestParam("inicio") int inicio, @RequestParam int colunas, @RequestParam String tipo,
                          @RequestParam("arquivo") MultipartFile arquivo) throws Exception {
        System.out.println("salvarCSV");
        if (!arquivo.isEmpty()) {
            byte[] bytes = arquivo.getBytes();
            String completeData = new String(bytes);
            CSVParsing csvParsing = new CSVParsing();
            if (tipo.equals("v")) {
                List<VendaLinha> vendas = csvParsing.lerCsvVenda(completeData, inicio, tipo, this.applicationPath);
                this.vendasService.conciliarVendas(vendas);
            } else {
                if (tipo.equals("r")) {
                    List<RecebimentoLinha> recebimentos = csvParsing.lerCsvRecebimento(completeData, inicio, tipo, this.applicationPath);
                    //this.recebimentosService.conciliarRecebimentos(recebimentos);
                }
            }
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/vendas")
    public void salvarCsvVendasStr(@RequestBody List<DadosCsv> arquivo) throws Exception {
        System.out.println("Conciliar arquivo vendas CSV");
        CSVParsing csvParsing = new CSVParsing();
        List<VendaLinha> vendas = csvParsing.lerListaVenda(arquivo);
        this.vendasService.conciliarVendas(vendas);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/recebimentos")
    public void salvarCsvRecebimentoStr(@RequestBody List<DadosCsv> arquivo) throws Exception {
        System.out.println("Conciliar arquivo recebimentos CSV");
        CSVParsing csvParsing = new CSVParsing();
        List<RecebimentoLinha> recebimentos = csvParsing.lerListaRecebimento(arquivo);
        this.recebimentosService.conciliarRecebimentos(recebimentos);
    }


}
