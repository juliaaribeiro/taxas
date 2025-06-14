package com.equals.estagio.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equals.estagio.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, String> {
    List<Venda> findByDataTransacaoBetween(LocalDate inicio, LocalDate fim);
}
