package com.equals.estagio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Venda {

    @Id
    @Column(name = "codigoTransacao")
    private String codigoTransacao;
    private Long id;

    private String estabelecimento;
    private LocalDate dataTransacao;
    private LocalDate dataEvento;
    private LocalTime horaEvento;
    private String tipoEvento;
    private String tipoTransacao;
    private String numeroSerieLeitor;
    private String codigoPedido;
    private BigDecimal valorTotal;
    private BigDecimal valorLiquido;
    private String pagamento;
    private String plano;
    private String parcela;
    private String qtdParcelas;
    private LocalDate dataPrevistaPagamento;
    private BigDecimal taxaParcelamentoComprador;
    private BigDecimal tarifaBoletoComprador;
    private BigDecimal valorOriginal;
    private BigDecimal taxaParcelamentoVendedor;
    private BigDecimal taxaIntermediacao;
    private BigDecimal tarifaIntermediacao;
    private BigDecimal tarifaBoletoVendedor;
    private BigDecimal repasseAplicacao;
    private BigDecimal valorLiquidoTransacao;
    private String statusPagamento;
    private String meioPagamento;
    private String bandeira;

    // Novos campos adicionados:
    private String canalEntrada;
    private String numLeitor;
    private String numMeioCaptura;
    private String numeroLogico;
    private String nsu;
    private String filler;
    private String cartaoBin;
    private String cartaoHolder;
    private String codigoAutorizacao;
    private String codigoCv;
    private String reservadoFuturos;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(String estabelecimento) { this.estabelecimento = estabelecimento; }

    public LocalDate getDataTransacao() { return dataTransacao; }
    public void setDataTransacao(LocalDate dataTransacao) { this.dataTransacao = dataTransacao; }

    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }

    public LocalTime getHoraEvento() { return horaEvento; }
    public void setHoraEvento(LocalTime horaEvento) { this.horaEvento = horaEvento; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getTipoTransacao() { return tipoTransacao; }
    public void setTipoTransacao(String tipoTransacao) { this.tipoTransacao = tipoTransacao; }

    public String getNumeroSerieLeitor() { return numeroSerieLeitor; }
    public void setNumeroSerieLeitor(String numeroSerieLeitor) { this.numeroSerieLeitor = numeroSerieLeitor; }

    public String getCodigoTransacao() { return codigoTransacao; }
    public void setCodigoTransacao(String codigoTransacao) { this.codigoTransacao = codigoTransacao; }

    public String getCodigoPedido() { return codigoPedido; }
    public void setCodigoPedido(String codigoPedido) { this.codigoPedido = codigoPedido; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public BigDecimal getValorLiquido() { return valorLiquido; }
    public void setValorLiquido(BigDecimal valorLiquido) { this.valorLiquido = valorLiquido; }

    public String getPagamento() { return pagamento; }
    public void setPagamento(String pagamento) { this.pagamento = pagamento; }

    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }

    public String getParcela() { return parcela; }
    public void setParcela(String parcela) { this.parcela = parcela; }

    public String getQtdParcelas() { return qtdParcelas; }
    public void setQtdParcelas(String qtdParcelas) { this.qtdParcelas = qtdParcelas; }

    public LocalDate getDataPrevistaPagamento() { return dataPrevistaPagamento; }
    public void setDataPrevistaPagamento(LocalDate dataPrevistaPagamento) { this.dataPrevistaPagamento = dataPrevistaPagamento; }

    public BigDecimal getTaxaParcelamentoComprador() { return taxaParcelamentoComprador; }
    public void setTaxaParcelamentoComprador(BigDecimal taxaParcelamentoComprador) { this.taxaParcelamentoComprador = taxaParcelamentoComprador; }

    public BigDecimal getTarifaBoletoComprador() { return tarifaBoletoComprador; }
    public void setTarifaBoletoComprador(BigDecimal tarifaBoletoComprador) { this.tarifaBoletoComprador = tarifaBoletoComprador; }

    public BigDecimal getValorOriginal() { return valorOriginal; }
    public void setValorOriginal(BigDecimal valorOriginal) { this.valorOriginal = valorOriginal; }

    public BigDecimal getTaxaParcelamentoVendedor() { return taxaParcelamentoVendedor; }
    public void setTaxaParcelamentoVendedor(BigDecimal taxaParcelamentoVendedor) { this.taxaParcelamentoVendedor = taxaParcelamentoVendedor; }

    public BigDecimal getTaxaIntermediacao() { return taxaIntermediacao; }
    public void setTaxaIntermediacao(BigDecimal taxaIntermediacao) { this.taxaIntermediacao = taxaIntermediacao; }

    public BigDecimal getTarifaIntermediacao() { return tarifaIntermediacao; }
    public void setTarifaIntermediacao(BigDecimal tarifaIntermediacao) { this.tarifaIntermediacao = tarifaIntermediacao; }

    public BigDecimal getTarifaBoletoVendedor() { return tarifaBoletoVendedor; }
    public void setTarifaBoletoVendedor(BigDecimal tarifaBoletoVendedor) { this.tarifaBoletoVendedor = tarifaBoletoVendedor; }

    public BigDecimal getRepasseAplicacao() { return repasseAplicacao; }
    public void setRepasseAplicacao(BigDecimal repasseAplicacao) { this.repasseAplicacao = repasseAplicacao; }

    public BigDecimal getValorLiquidoTransacao() { return valorLiquidoTransacao; }
    public void setValorLiquidoTransacao(BigDecimal valorLiquidoTransacao) { this.valorLiquidoTransacao = valorLiquidoTransacao; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    public String getMeioPagamento() { return meioPagamento; }
    public void setMeioPagamento(String meioPagamento) { this.meioPagamento = meioPagamento; }

    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }

    public String getCanalEntrada() { return canalEntrada; }
    public void setCanalEntrada(String canalEntrada) { this.canalEntrada = canalEntrada; }

    public String getNumLeitor() { return numLeitor; }
    public void setNumLeitor(String numLeitor) { this.numLeitor = numLeitor; }

    public String getNumMeioCaptura() { return numMeioCaptura; }
    public void setNumMeioCaptura(String numMeioCaptura) { this.numMeioCaptura = numMeioCaptura; }

    public String getNumeroLogico() { return numeroLogico; }
    public void setNumeroLogico(String numeroLogico) { this.numeroLogico = numeroLogico; }

    public String getNsu() { return nsu; }
    public void setNsu(String nsu) { this.nsu = nsu; }

    public String getFiller() { return filler; }
    public void setFiller(String filler) { this.filler = filler; }

    public String getCartaoBin() { return cartaoBin; }
    public void setCartaoBin(String cartaoBin) { this.cartaoBin = cartaoBin; }

    public String getCartaoHolder() { return cartaoHolder; }
    public void setCartaoHolder(String cartaoHolder) { this.cartaoHolder = cartaoHolder; }

    public String getCodigoAutorizacao() { return codigoAutorizacao; }
    public void setCodigoAutorizacao(String codigoAutorizacao) { this.codigoAutorizacao = codigoAutorizacao; }

    public String getCodigoCv() { return codigoCv; }
    public void setCodigoCv(String codigoCv) { this.codigoCv = codigoCv; }

    public String getReservadoFuturos() { return reservadoFuturos; }
    public void setReservadoFuturos(String reservadoFuturos) { this.reservadoFuturos = reservadoFuturos; }
}
