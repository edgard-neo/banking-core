package com.br.mapper;

import com.br.domain.ContaBancaria;
import com.br.dto.RequestDTO;
import com.br.dto.ResponseDTO;

public class ContaBancariaMapper {

    public static ContaBancaria toEntity(RequestDTO request) {

        return new ContaBancaria(request.getNome());
    }

    public static ResponseDTO toResponse(ContaBancaria contaBancaria) {

        return new ResponseDTO(contaBancaria.getId(), contaBancaria.getNome(),
                contaBancaria.getSaldo(), contaBancaria.getStatus(), contaBancaria.getCriadoAs());
    }
}
