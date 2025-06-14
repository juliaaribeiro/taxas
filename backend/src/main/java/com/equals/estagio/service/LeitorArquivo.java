package com.equals.estagio.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.equals.estagio.model.Cabecalho;
import com.equals.estagio.model.Venda;

@Service
public class LeitorArquivo {

    public class ResultadoLeitura {
        private Cabecalho cabecalho;
        private List<Venda> vendas;

        public ResultadoLeitura(Cabecalho cabecalho, List<Venda> vendas) {
            this.cabecalho = cabecalho;
            this.vendas = vendas;
        }

        public Cabecalho getCabecalho() {
            return cabecalho;
        }

        public List<Venda> getVendas() {
            return vendas;
        }
    }

    public ResultadoLeitura lerArquivo(String caminho) throws IOException {
        List<Venda> vendas = new ArrayList<>();
        Cabecalho cabecalho = null;

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            boolean cabecalhoLido = false;

            while ((linha = br.readLine()) != null) {
                if (!cabecalhoLido) {
                    if (linha.startsWith("0")) {
                        cabecalho = new Cabecalho();

                        cabecalho.setTipoRegistro(linha.substring(0, 1));
                        cabecalho.setEstabelecimento(linha.substring(1, 11).trim());
                        cabecalho.setDataGeracaoArquivo(LocalDate.parse(linha.substring(11, 19), DateTimeFormatter.BASIC_ISO_DATE));
                        cabecalho.setPeriodoInicial(LocalDate.parse(linha.substring(19, 27), DateTimeFormatter.BASIC_ISO_DATE));
                        cabecalho.setPeriodoFinal(LocalDate.parse(linha.substring(27, 35), DateTimeFormatter.BASIC_ISO_DATE));
                        cabecalho.setNumeroSequencial(linha.substring(35, 42).trim());
                        cabecalho.setEmpresaAdquirente(linha.substring(42, 47).trim());
                        cabecalho.setTipoExtrato(linha.substring(47, 49));
                        cabecalho.setFiller(linha.substring(49, 70));
                        cabecalho.setVersaoLayout(linha.substring(70, 73).trim());
                        cabecalho.setVersaoRelease(linha.substring(73, 78).trim());
                        cabecalho.setReservadoFuturo(linha.substring(78));

                        cabecalhoLido = true;
                    }
                    continue;
                }

                if (linha.startsWith("1")) {
                    Venda venda = new Venda();

                    venda.setEstabelecimento(linha.substring(1, 11));
                    venda.setDataTransacao(LocalDate.parse(linha.substring(11, 19), DateTimeFormatter.BASIC_ISO_DATE));
                    venda.setDataEvento(LocalDate.parse(linha.substring(19, 27), DateTimeFormatter.BASIC_ISO_DATE));
                    venda.setHoraEvento(LocalTime.parse(linha.substring(27, 33), DateTimeFormatter.ofPattern("HHmmss")));
                    venda.setTipoEvento(linha.substring(33, 35));
                    venda.setTipoTransacao(linha.substring(35, 37));
                    venda.setNumeroSerieLeitor(linha.substring(37, 45).trim());
                    venda.setCodigoTransacao(linha.substring(45, 77).trim());
                    venda.setCodigoPedido(linha.substring(77, 97).trim());
                    venda.setValorTotal(new BigDecimal(linha.substring(97, 110)).movePointLeft(2));
                    venda.setValorLiquido(new BigDecimal(linha.substring(110, 123)).movePointLeft(2));
                    venda.setPagamento(linha.substring(123, 124));
                    venda.setPlano(linha.substring(124, 126));
                    venda.setParcela(linha.substring(126, 128));
                    venda.setQtdParcelas(linha.substring(128, 130));
                    venda.setDataPrevistaPagamento(LocalDate.parse(linha.substring(130, 138), DateTimeFormatter.BASIC_ISO_DATE));
                    venda.setTaxaParcelamentoComprador(new BigDecimal(linha.substring(138, 151)).movePointLeft(2));
                    venda.setTarifaBoletoComprador(new BigDecimal(linha.substring(151, 164)).movePointLeft(2));
                    venda.setValorOriginal(new BigDecimal(linha.substring(164, 177)).movePointLeft(2));
                    venda.setTaxaParcelamentoVendedor(new BigDecimal(linha.substring(177, 190)).movePointLeft(2));
                    venda.setTaxaIntermediacao(new BigDecimal(linha.substring(190, 203)).movePointLeft(2));
                    venda.setTarifaIntermediacao(new BigDecimal(linha.substring(203, 216)).movePointLeft(2));
                    venda.setTarifaBoletoVendedor(new BigDecimal(linha.substring(216, 229)).movePointLeft(2));
                    venda.setRepasseAplicacao(new BigDecimal(linha.substring(229, 242)).movePointLeft(2));
                    venda.setValorLiquidoTransacao(new BigDecimal(linha.substring(242, 255)).movePointLeft(2));
                    venda.setStatusPagamento(linha.substring(255, 257));
                    venda.setMeioPagamento(linha.substring(259, 261));
                    venda.setBandeira(linha.substring(261, 291).trim());
                    venda.setCanalEntrada(linha.substring(291, 293));
                    venda.setNumLeitor(linha.substring(293, 295).trim());
                    venda.setNumMeioCaptura(linha.substring(295, 297));
                    venda.setNumeroLogico(linha.substring(297, 329).trim());
                    venda.setNsu(linha.substring(329, 340).trim());
                    venda.setFiller(linha.substring(340, 343));
                    venda.setCartaoBin(linha.substring(343, 349));
                    venda.setCartaoHolder(linha.substring(349, 353));
                    venda.setCodigoAutorizacao(linha.substring(353, 359));
                    venda.setCodigoCv(linha.substring(359, 391).trim());
                    venda.setReservadoFuturos(linha.substring(391, 530));

                    vendas.add(venda);
                }
            }
        }

        return new ResultadoLeitura(cabecalho, vendas);
    }
}

