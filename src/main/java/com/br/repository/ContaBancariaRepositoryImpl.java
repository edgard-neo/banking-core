package com.br.repository;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import com.br.domain.ContaBancaria;

public class ContaBancariaRepositoryImpl implements ContaBancariaRepository {

    private Set<ContaBancaria> bancoDeDadosInMemoria = new LinkedHashSet<>();

    private AtomicLong contador = new AtomicLong(0);


    @Override
    public ContaBancaria adicionarUsuario(ContaBancaria usuario) {

        // adiciona o ID no o objeto antes de salvar
        usuario.setIDapenasPeloRepository(contador.incrementAndGet());

        bancoDeDadosInMemoria.add(usuario);

        return usuario;
    }

    @Override
    public ContaBancaria buscarUsuarioPelaID(Long id) {

        return bancoDeDadosInMemoria.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Set<ContaBancaria> buscarTodosUsuarios() {
        return bancoDeDadosInMemoria;
    }
}
