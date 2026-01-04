package com.br.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.br.domain.ContaBancaria;

public class ContaBancariaRepositoryTest {

    private ContaBancaria usuario;
    private ContaBancariaRepository repository;

    @BeforeEach
    public void setUp() {
        usuario = new ContaBancaria("Edgar");
        repository = new ContaBancariaRepositoryImpl();

    }

    @Test
    public void adicionarUsuarioComSucesso() {

        repository.adicionarUsuario(usuario);

        assertEquals(1, repository.buscarTodosUsuarios().size());

    }

    @Test
    public void buscarUsuarioPelaIDComSucesso() {

        repository.adicionarUsuario(usuario);

        Long buscarID = (long) 1;

        assertEquals(usuario, repository.buscarUsuarioPelaID(buscarID));
    }

    @Test
    public void buscarTodosOsUsuariosComSucesso() {

        repository.adicionarUsuario(new ContaBancaria("Anny"));
        repository.adicionarUsuario(new ContaBancaria("Leo"));
        repository.adicionarUsuario(usuario);

        assertEquals(3, repository.buscarTodosUsuarios().size());
    }
}
