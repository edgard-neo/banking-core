package com.br.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.br.domain.enums.Status;

public class ResponseDTO {

    private Long id;
    private String nome;
    private BigDecimal saldo;
    private Status status;
    private LocalDateTime criadoAs;

    public ResponseDTO(Long id, String nome, BigDecimal saldo, Status status,
            LocalDateTime criadoAs) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.status = status;
        this.criadoAs = criadoAs;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCriadoAs() {
        return criadoAs;
    }



}
