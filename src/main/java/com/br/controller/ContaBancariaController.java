package com.br.controller;

import com.br.dto.RequestDTO;
import com.br.service.ContaBancariaService;

public class ContaBancariaController {

    private ContaBancariaService service;

    public ContaBancariaController(ContaBancariaService service) {
        this.service = service;
    }

    public void adicionarUsuario(RequestDTO request) {

        service.adicionarUsuario(request);
    }
}
