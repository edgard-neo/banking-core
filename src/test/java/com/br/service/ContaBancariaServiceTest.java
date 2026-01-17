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

        service.adicionarUsuario(usuario);

        assertEquals(1, service.buscarTodosUsuarios().size());
    }

    @Test
    @DisplayName("Verifica se foi encontado o usuario pelo ID")
    void buscarUsuarioPelaIDComSucesso() {

        ResponseDTO usuarioSalvo = service.adicionarUsuario(usuario);

        assertEquals(usuarioSalvo.getId(),
                service.buscarUsuarioPelaID(usuarioSalvo.getId()).getId());

    }

    @Test
    @DisplayName("Verifica se retornou todos os usuarios")
    void buscarTodosUsuarios() {

        service.adicionarUsuario(new RequestDTO("Anny"));
        service.adicionarUsuario(new RequestDTO("Leo"));

        assertEquals(repository.buscarTodosUsuarios().size(), service.buscarTodosUsuarios().size());
    }

}
