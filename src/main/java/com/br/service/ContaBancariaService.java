package com.br.service;

import java.util.Set;
import com.br.domain.ContaBancaria;
import com.br.dto.RequestDTO;
import com.br.dto.ResponseDTO;
import com.br.mapper.ContaBancariaMapper;
import com.br.repository.ContaBancariaRepository;


public class ContaBancariaService {

    private ContaBancariaRepository repository;

    public ContaBancariaService(ContaBancariaRepository repository) {
        this.repository = repository;
    }


    public ResponseDTO adicionarUsuario(RequestDTO request) {

        if (request.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não Pode estar Vazio");
        }

        ContaBancaria usuario = ContaBancariaMapper.toEntity(request);

        return ContaBancariaMapper.toResponse(repository.adicionarUsuario(usuario));
    }


    public ResponseDTO buscarUsuarioPelaID(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode esta vazio");
        }

        ContaBancaria usuarioEncontrado = repository.buscarUsuarioPelaID(id);

        return ContaBancariaMapper.toResponse(usuarioEncontrado);
    }


    public Set<ContaBancaria> buscarTodosUsuarios() {

        return repository.buscarTodosUsuarios();
    }


}
