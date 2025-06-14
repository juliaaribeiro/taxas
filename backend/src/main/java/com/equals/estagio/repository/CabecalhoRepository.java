package com.equals.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equals.estagio.model.Cabecalho;

public interface CabecalhoRepository extends JpaRepository<Cabecalho, String> {
    // Você pode adicionar métodos customizados se precisar, por exemplo:
    // List<Cabecalho> findByEstabelecimento(String estabelecimento);
}

