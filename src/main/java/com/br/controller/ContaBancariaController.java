package com.br.controller;

import java.math.BigDecimal;
import java.util.Set;
import com.br.domain.ContaBancaria;
import com.br.dto.RequestDTO;
import com.br.dto.ResponseDTO;
import com.br.service.ContaBancariaService;

public class ContaBancariaController {

    private ContaBancariaService service;

    public ContaBancariaController(ContaBancariaService service) {
        this.service = service;
    }

    public ResponseDTO criarConta(RequestDTO request) {

        return service.criarConta(request);
    }

    public ResponseDTO buscarPorId(Long id) {

        return service.buscarPorId(id);
    }

    public Set<ContaBancaria> buscarTodos() {

        return service.buscarTodos();
    }

    public ResponseDTO depositar(Long numeroContaID, BigDecimal valor) {

        return service.depositar(numeroContaID, valor);
    }

    public ResponseDTO sacar(Long numeroContaID, BigDecimal valor) {

        return service.sacar(numeroContaID, valor);
    }

    public ResponseDTO bloquearConta(Long numeroConta) {

        return service.bloquearConta(numeroConta);
    }

    public ResponseDTO desbloquearConta(Long numeroConta) {

        return service.desbloquearConta(numeroConta);
    }

}
