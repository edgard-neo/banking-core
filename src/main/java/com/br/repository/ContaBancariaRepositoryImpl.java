package com.br.repository;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import com.br.domain.ContaBancaria;

public class ContaBancariaRepositoryImpl implements ContaBancariaRepository {

    private final Set<ContaBancaria> bancoDeDadosInMemoria = new LinkedHashSet<>();

    private final AtomicLong contador = new AtomicLong(0);


    @Override
    public ContaBancaria salvar(ContaBancaria conta) {

        if (conta.getId() == null) {

            // adiciona o ID no o objeto antes de salvar
            conta.setIDapenasPeloRepository(contador.incrementAndGet());

            bancoDeDadosInMemoria.add(conta);

            return conta;
        }

        bancoDeDadosInMemoria.remove(conta);
        bancoDeDadosInMemoria.add(conta);
        return conta;
    }

    @Override
    public ContaBancaria buscarUsuarioPelaID(Long id) {

        return bancoDeDadosInMemoria.stream().filter(b -> id.equals(b.getId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }

    @Override
    public Set<ContaBancaria> buscarTodosUsuarios() {
        return bancoDeDadosInMemoria;
    }

}
