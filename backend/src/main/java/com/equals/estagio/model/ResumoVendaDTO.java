package com.equals.estagio.model;


import java.math.BigDecimal;
import java.util.Map;

public class ResumoVendaDTO {
    private long totalVendas;
    private BigDecimal totalValor;
    private BigDecimal totalLiquido;
    private Map<String, Long> vendasPorBandeira;
    private Map<String, BigDecimal> valorPorBandeira;

    // Construtor
    public ResumoVendaDTO(long totalVendas, BigDecimal totalValor, BigDecimal totalLiquido, BigDecimal totalTaxas, Map<String, Long> vendasPorBandeira, Map<String, BigDecimal> valorPorBandeira) {
        this.totalVendas = totalVendas;
        this.totalValor = totalValor;
        this.totalLiquido = totalLiquido;
        this.vendasPorBandeira = vendasPorBandeira;
        this.valorPorBandeira = valorPorBandeira;
    }

    // Getters e Setters
    public long getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(long totalVendas) {
        this.totalVendas = totalVendas;
    }

    public BigDecimal getTotalValor() {
        return totalValor;
    }

    public void setTotalValor(BigDecimal totalValor) {
        this.totalValor = totalValor;
    }

    public BigDecimal getTotalLiquido() {
        return totalLiquido;
    }

    public void setTotalLiquido(BigDecimal totalLiquido) {
        this.totalLiquido = totalLiquido;
    }

    public Map<String, Long> getVendasPorBandeira() {
        return vendasPorBandeira;
    }

    public void setVendasPorBandeira(Map<String, Long> vendasPorBandeira) {
        this.vendasPorBandeira = vendasPorBandeira;
    }

    public Map<String, BigDecimal> getValorPorBandeira() {
        return valorPorBandeira;
    }

    public void setValorPorBandeira(Map<String, BigDecimal> valorPorBandeira) {
        this.valorPorBandeira = valorPorBandeira;
    }
}
