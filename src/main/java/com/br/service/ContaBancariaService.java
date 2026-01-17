package com.br.service;

import java.math.BigDecimal;
import java.util.Set;
import com.br.domain.ContaBancaria;
import com.br.dto.RequestDTO;
import com.br.dto.ResponseDTO;
import com.br.mapper.ContaBancariaMapper;
import com.br.repository.ContaBancariaRepository;


public class ContaBancariaService {

    private final ContaBancariaRepository repository;

    public ContaBancariaService(ContaBancariaRepository repository) {
        this.repository = repository;
    }


    public ResponseDTO criarConta(RequestDTO request) {

        ContaBancaria usuario = ContaBancariaMapper.toEntity(request);

        return ContaBancariaMapper.toResponse(repository.salvar(usuario));
    }


    public ResponseDTO buscarPorId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id não pode esta vazio");
        }

        ContaBancaria usuarioEncontrado = repository.buscarUsuarioPelaID(id);

        return ContaBancariaMapper.toResponse(usuarioEncontrado);
    }


    public Set<ContaBancaria> buscarTodos() {

        return repository.buscarTodosUsuarios();
    }

    public ResponseDTO depositar(Long numeroContaID, BigDecimal valor) {

        ContaBancaria contaUsuario = repository.buscarUsuarioPelaID(numeroContaID);

        contaUsuario.depositar(valor);

        ContaBancaria contaAtualizada = repository.salvar(contaUsuario);

        return ContaBancariaMapper.toResponse(contaAtualizada);
    }

    public ResponseDTO sacar(Long numeroContaID, BigDecimal valor) {

        ContaBancaria contaUsuario = repository.buscarUsuarioPelaID(numeroContaID);

        contaUsuario.sacar(valor);

        ContaBancaria contaAtualizada = repository.salvar(contaUsuario);

        return ContaBancariaMapper.toResponse(contaAtualizada);
    }

    public ResponseDTO bloquearConta(Long contaUsuarioID) {

        ContaBancaria contaUsuario = repository.buscarUsuarioPelaID(contaUsuarioID);

        contaUsuario.bloquearConta();

        ContaBancaria contaAtualizada = repository.salvar(contaUsuario);

        return ContaBancariaMapper.toResponse(contaAtualizada);
    }

    public ResponseDTO desbloquearConta(Long contaUsuarioID) {

        ContaBancaria contaUsuario = repository.buscarUsuarioPelaID(contaUsuarioID);

        contaUsuario.desbloquearConta();

        ContaBancaria contaAtualizada = repository.salvar(contaUsuario);

        return ContaBancariaMapper.toResponse(contaAtualizada);
    }

}
