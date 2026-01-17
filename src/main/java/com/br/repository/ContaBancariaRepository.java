package com.br.repository;

import java.util.Set;
import com.br.domain.ContaBancaria;

public interface ContaBancariaRepository {

    ContaBancaria salvar(ContaBancaria conta);

    ContaBancaria buscarUsuarioPelaID(Long id);

    Set<ContaBancaria> buscarTodosUsuarios();

}
