
package com.equals.estagio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.equals.estagio.model.Cabecalho;
import com.equals.estagio.model.Venda;
import com.equals.estagio.repository.CabecalhoRepository;
import com.equals.estagio.repository.VendaRepository;
import com.equals.estagio.service.LeitorArquivo;
import com.equals.estagio.service.LeitorArquivo.ResultadoLeitura;

@SpringBootApplication
public class EqualsEstagioApplication implements CommandLineRunner {

    @Autowired
    private LeitorArquivo leitor;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private CabecalhoRepository cabecalhoRepository;

    public static void main(String[] args) {
        SpringApplication.run(EqualsEstagioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ResultadoLeitura resultado = leitor.lerArquivo("Arquivo_Estagio_Desenvolvimento.txt");

        Cabecalho cabecalho = resultado.getCabecalho();
        List<Venda> vendas = resultado.getVendas();

        // Salva o cabeçalho
        cabecalhoRepository.save(cabecalho);

        // Salva as vendas
        vendaRepository.saveAll(vendas);
    }
}
