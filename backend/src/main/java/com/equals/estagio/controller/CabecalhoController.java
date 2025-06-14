package com.equals.estagio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equals.estagio.model.Cabecalho;
import com.equals.estagio.repository.CabecalhoRepository;


@RestController
@RequestMapping("/cabecalhos")
public class CabecalhoController {

    @Autowired
    private CabecalhoRepository cabecalhoRepository;

    @GetMapping
    public List<Cabecalho> listarTodos() {
        return cabecalhoRepository.findAll();
    }
}
