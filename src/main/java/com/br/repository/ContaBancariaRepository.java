package com.br.repository;

import java.util.Set;
import com.br.domain.ContaBancaria;

public interface ContaBancariaRepository {

    ContaBancaria adicionarUsuario(ContaBancaria usuario);

    ContaBancaria buscarUsuarioPelaID(Long id);

    Set<ContaBancaria> buscarTodosUsuarios();

    // TODO: Obter histórico completo da conta (ordenado por data, decrescente,
    // somente leitura)
}
