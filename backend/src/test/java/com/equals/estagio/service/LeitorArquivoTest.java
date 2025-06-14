package com.equals.estagio.service;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.equals.estagio.model.Cabecalho;
import com.equals.estagio.model.Venda;
import com.equals.estagio.service.LeitorArquivo.ResultadoLeitura;

public class LeitorArquivoTest {

    @Test
    void testLerArquivoComCabecalhoEVenda(@TempDir Path tempDir) throws Exception {
        // Arrange
        File tempFile = tempDir.resolve("teste.txt").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            // Cabeçalho
            writer.write("012345678912018112920180925201809250017799FICTI01A                    002.002c                                                                                                                                                                                                                                                                                                                                                                                                                                                                    \n");
            // Venda
            writer.write("11234567891201809252018092513173601016831815412345678910111213141516171819202000001              00000000001000000000000098M1 1 012018102500000000000000000000000000000000000010000000000000000000000000002000000000000000000000000000000000000000000000000009801  03MASTERCARD                    ME1201                                1003413168    1234567890589082115720                                                                                                                                                                     \n");
        }

        LeitorArquivo leitor = new LeitorArquivo();

        
        ResultadoLeitura resultado = leitor.lerArquivo(tempFile.getAbsolutePath());

        
        Cabecalho cabecalho = resultado.getCabecalho();
        List<Venda> vendas = resultado.getVendas();

        // Assert
        assertNotNull(cabecalho);
        assertNotNull(vendas);
 
        // Assertions para Cabecalho
        assertEquals("0", cabecalho.getTipoRegistro());
        assertEquals("1234567891", cabecalho.getEstabelecimento());
        assertEquals(LocalDate.of(2018, 11, 29), cabecalho.getDataGeracaoArquivo());
        assertEquals(LocalDate.of(2018, 9, 25), cabecalho.getPeriodoInicial());
        assertEquals(LocalDate.of(2018, 9, 25), cabecalho.getPeriodoFinal());
        assertEquals("0017799", cabecalho.getNumeroSequencial());
        assertEquals("FICTI", cabecalho.getEmpresaAdquirente());
        assertEquals("01", cabecalho.getTipoExtrato());
        assertEquals("A", cabecalho.getFiller().trim());
        assertEquals("002", cabecalho.getVersaoLayout());
        assertEquals(".002c", cabecalho.getVersaoRelease());
        assertEquals("", cabecalho.getReservadoFuturo().trim());
        // Assertions para Venda
        assertEquals(1, vendas.size());
        Venda vendaTeste = vendas.get(0);

        assertEquals("1234567891", vendaTeste.getEstabelecimento());
        assertEquals(LocalDate.of(2018, 9, 25), vendaTeste.getDataTransacao());
        assertEquals(LocalDate.of(2018, 9, 25), vendaTeste.getDataEvento());
        assertEquals(LocalTime.of(13, 17, 36), vendaTeste.getHoraEvento());
        assertEquals("01", vendaTeste.getTipoEvento());
        assertEquals("01", vendaTeste.getTipoTransacao());
        assertEquals("68318154", vendaTeste.getNumeroSerieLeitor());
        assertEquals("12345678910111213141516171819202", vendaTeste.getCodigoTransacao());
        assertEquals("000001", vendaTeste.getCodigoPedido().trim());
        assertEquals(new BigDecimal("1.00"), vendaTeste.getValorTotal());
        assertEquals(new BigDecimal("0.98"), vendaTeste.getValorLiquido());
        assertEquals("M", vendaTeste.getPagamento());
        assertEquals("1 ", vendaTeste.getPlano());
        assertEquals("1", vendaTeste.getParcela().trim());
        assertEquals("01", vendaTeste.getQtdParcelas().trim());
        assertEquals(LocalDate.of(2018, 10, 25), vendaTeste.getDataPrevistaPagamento());
        assertEquals(new BigDecimal("0.00"), vendaTeste.getTaxaParcelamentoComprador());
        assertEquals(new BigDecimal("0.00"), vendaTeste.getTarifaBoletoComprador());
        assertEquals(new BigDecimal("1.00"), vendaTeste.getValorOriginal());
        assertEquals(new BigDecimal("0.00"), vendaTeste.getTaxaParcelamentoVendedor());
        assertEquals(new BigDecimal("0.02"), vendaTeste.getTaxaIntermediacao());
        assertEquals(new BigDecimal("0.00"), vendaTeste.getTarifaIntermediacao());
        assertEquals(new BigDecimal("0.00"), vendaTeste.getTarifaBoletoVendedor());
        assertEquals(new BigDecimal("0.00"), vendaTeste.getRepasseAplicacao());
        assertEquals(new BigDecimal("0.98"), vendaTeste.getValorLiquidoTransacao());
        assertEquals("01", vendaTeste.getStatusPagamento());
        assertEquals("03", vendaTeste.getMeioPagamento());
        assertEquals("MASTERCARD", vendaTeste.getBandeira().trim());
        assertEquals("ME", vendaTeste.getCanalEntrada());
        assertEquals("12", vendaTeste.getNumLeitor());
        assertEquals("01", vendaTeste.getNumMeioCaptura());
        assertEquals("", vendaTeste.getNumeroLogico().trim());
        assertEquals("1003413168", vendaTeste.getNsu().trim());
        assertEquals("", vendaTeste.getFiller().trim());
        assertEquals("123456", vendaTeste.getCartaoBin());
        assertEquals("7890", vendaTeste.getCartaoHolder());
        assertEquals("589082", vendaTeste.getCodigoAutorizacao());
        assertEquals("115720", vendaTeste.getCodigoCv().trim());
        assertEquals("", vendaTeste.getReservadoFuturos().trim());
    }
}
