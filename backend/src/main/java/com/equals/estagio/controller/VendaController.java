
package com.equals.estagio.controller;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equals.estagio.model.ResumoVendaDTO;
import com.equals.estagio.model.Venda;
import com.equals.estagio.repository.VendaRepository;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaRepository repository;
    
    @GetMapping
    public List<Venda> listar(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        System.out.println("Parametros recebidos - inicio: " + inicio + ", fim: " + fim);

        if (inicio != null && fim != null) {
            return repository.findByDataTransacaoBetween(inicio, fim);
        }
        return repository.findAll();
    }

@GetMapping("/resumo")
public ResumoVendaDTO resumoVendas(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

    List<Venda> vendas = repository.findByDataTransacaoBetween(inicio, fim);

    long totalVendas = vendas.size();
    BigDecimal totalValor = vendas.stream().map(Venda::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal totalLiquido = vendas.stream().map(Venda::getValorLiquido).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal totalTaxas = totalValor.subtract(totalLiquido);

    Map<String, Long> vendasPorBandeira = vendas.stream()
        .collect(Collectors.groupingBy(Venda::getBandeira, Collectors.counting()));

    Map<String, BigDecimal> valorPorBandeira = vendas.stream()
        .collect(Collectors.groupingBy(Venda::getBandeira,
            Collectors.reducing(BigDecimal.ZERO, Venda::getValorTotal, BigDecimal::add)));

    return new ResumoVendaDTO(totalVendas, totalValor, totalLiquido, totalTaxas, vendasPorBandeira, valorPorBandeira);
}



}
