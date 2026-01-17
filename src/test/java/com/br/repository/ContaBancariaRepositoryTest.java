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

        repository.salvar(usuario);

        assertEquals(1, repository.buscarTodosUsuarios().size());

    }

    @Test
    public void buscarUsuarioPelaIDComSucesso() {

        repository.salvar(usuario);

        Long buscarID = (long) 1;

        assertEquals(usuario, repository.buscarUsuarioPelaID(buscarID));
    }

    @Test
    public void buscarTodosOsUsuariosComSucesso() {

        repository.salvar(new ContaBancaria("Anny"));
        repository.salvar(new ContaBancaria("Leo"));
        repository.salvar(usuario);

        assertEquals(3, repository.buscarTodosUsuarios().size());
    }
}
