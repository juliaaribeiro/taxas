package com.equals.estagio.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cabecalho {

    @Id
    @Column(name = "numero_sequencial", length = 36)
    private String numeroSequencial;

    @Column(name = "tipo_registro", length = 1, nullable = false)
    private String tipoRegistro; // Sempre “0”

    @Column(length = 10, nullable = false)
    private String estabelecimento;

    @Column(name = "data_geracao_arquivo")
    private LocalDate dataGeracaoArquivo;

    @Column(name = "periodo_inicial")
    private LocalDate periodoInicial;

    @Column(name = "periodo_final")
    private LocalDate periodoFinal;

    @Column(name = "empresa_adquirente", length = 5, nullable = false)
    private String empresaAdquirente; // Constante "FICTI"

    @Column(name = "tipo_extrato", length = 2, nullable = false)
    private String tipoExtrato; // “01” para vendas

    @Column(length = 21)
    private String filler;

    @Column(name = "versao_layout", length = 3)
    private String versaoLayout;

    @Column(name = "versao_release", length = 5)
    private String versaoRelease;

    @Column(name = "reservado_futuro", length = 453)
    private String reservadoFuturo;

    // Getters e Setters

    public String getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(String numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public LocalDate getDataGeracaoArquivo() {
        return dataGeracaoArquivo;
    }

    public void setDataGeracaoArquivo(LocalDate dataGeracaoArquivo) {
        this.dataGeracaoArquivo = dataGeracaoArquivo;
    }

    public LocalDate getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(LocalDate periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public LocalDate getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(LocalDate periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public String getEmpresaAdquirente() {
        return empresaAdquirente;
    }

    public void setEmpresaAdquirente(String empresaAdquirente) {
        this.empresaAdquirente = empresaAdquirente;
    }

    public String getTipoExtrato() {
        return tipoExtrato;
    }

    public void setTipoExtrato(String tipoExtrato) {
        this.tipoExtrato = tipoExtrato;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getVersaoLayout() {
        return versaoLayout;
    }

    public void setVersaoLayout(String versaoLayout) {
        this.versaoLayout = versaoLayout;
    }

    public String getVersaoRelease() {
        return versaoRelease;
    }

    public void setVersaoRelease(String versaoRelease) {
        this.versaoRelease = versaoRelease;
    }

    public String getReservadoFuturo() {
        return reservadoFuturo;
    }

    public void setReservadoFuturo(String reservadoFuturo) {
        this.reservadoFuturo = reservadoFuturo;
    }
}
