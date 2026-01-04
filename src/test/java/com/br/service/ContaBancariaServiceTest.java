package com.br.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.br.domain.ContaBancaria;
import com.br.repository.ContaBancariaRepository;
import com.br.repository.ContaBancariaRepositoryImpl;

public class ContaBancariaServiceTest {

    ContaBancaria usuario;
    ContaBancariaService service;
    ContaBancariaRepository repository;

    @BeforeEach
    void setUp() {
        usuario = new ContaBancaria("Edgar");
        repository = new ContaBancariaRepositoryImpl();
        service = new ContaBancariaService(repository);
    }

    @Test
    @DisplayName("Verifica se foi salvo usuario com sucesso")
    void adicionarUsuarioComSucesso() {

        service.adicionarUsuario(usuario);

        assertEquals(1, service.buscarTodosUsuarios().size());
    }

    @Test
    @DisplayName("Verifica se foi encontado o usuario pelo ID")
    void buscarUsuarioPelaIDComSucesso() {

        service.adicionarUsuario(usuario);

        assertEquals(usuario, service.buscarUsuarioPelaID(usuario.getId()));

    }

    @Test
    @DisplayName("Verifica se retornou todos os usuarios")
    void buscarTodosUsuarios() {

        service.adicionarUsuario(new ContaBancaria("Anny"));
        service.adicionarUsuario(new ContaBancaria("Leo"));

        assertEquals(repository.buscarTodosUsuarios().size(), service.buscarTodosUsuarios().size());
    }

}
