package com.br.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.br.dto.RequestDTO;
import com.br.dto.ResponseDTO;
import com.br.repository.ContaBancariaRepository;
import com.br.repository.ContaBancariaRepositoryImpl;

public class ContaBancariaServiceTest {

    RequestDTO usuario;
    ContaBancariaService service;
    ContaBancariaRepository repository;

    @BeforeEach
    void setUp() {
        usuario = new RequestDTO("Edgar");
        repository = new ContaBancariaRepositoryImpl();
        service = new ContaBancariaService(repository);
    }

    @Test
    @DisplayName("Verifica se foi salvo usuario com sucesso")
    void adicionarUsuarioComSucesso() {

        service.criarConta(usuario);

        assertEquals(1, service.buscarTodos().size());
    }

    @Test
    @DisplayName("Verifica se foi encontado o usuario pelo ID")
    void buscarUsuarioPelaIDComSucesso() {

        ResponseDTO usuarioSalvo = service.criarConta(usuario);

        assertEquals(usuarioSalvo.getId(), service.buscarPorId(usuarioSalvo.getId()).getId());

    }

    @Test
    @DisplayName("Verifica se retornou todos os usuarios")
    void buscarTodosUsuarios() {

        service.criarConta(new RequestDTO("Anny"));
        service.criarConta(new RequestDTO("Leo"));

        assertEquals(repository.buscarTodosUsuarios().size(), service.buscarTodos().size());
    }

}
