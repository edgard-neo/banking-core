package com.br.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.br.domain.enums.Status;
import com.br.exception.ContaBloqueadaException;
import com.br.exception.SaldoInsuficienteException;
import com.br.exception.ValorInvalidoException;

public class ContaBancaria {

    private Long id;
    private String nome;
    private BigDecimal saldo;
    private Status status;
    private LocalDateTime criadoAs;



    public ContaBancaria(String nome) {

        if (nome.isBlank() || nome == null) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou em branco");
        }

        if (!nome.matches("[a-z A-Z À-ÿ ]+")) {
            throw new IllegalArgumentException("Nome contém caracteres inválidos");
        }

        this.nome = nome;
        this.saldo = BigDecimal.ZERO;
        this.status = Status.ATIVA;
        this.criadoAs = LocalDateTime.now();
    }

    public void depositar(BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("valor invalido");
        }

        if (status == Status.BLOQUEADA) {
            throw new ContaBloqueadaException("conta esta bloqueada");
        }

        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {

        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException("Saldo Insuficiente");
        }

        if (status == Status.BLOQUEADA) {
            throw new ContaBloqueadaException("Conta esta Bloqueada");
        }

        this.saldo = this.saldo.subtract(valor);

        // TODO ADICIONAR O HISTORICO SAQUE
    }

    public void bloquearConta() {

        if (status == Status.BLOQUEADA) {
            throw new ContaBloqueadaException("A conta atual já esta bloqueada");
        }

        this.status = Status.BLOQUEADA;
        // TODO ADICIONAR HISTORICO ALTERAÇAO DE STATUS

    }

    public void desbloquearConta() {

        if (status == Status.ATIVA) {
            throw new IllegalArgumentException("A conta já esta ativa");
        }

        this.status = Status.ATIVA;
        // TODO ADICIONAR HISTORICO ALTERAÇAO DE STATUS
    }

    public Long getId() {
        return this.id;
    }

    public void setIDapenasPeloRepository(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public Status getStatus() {
        return this.status;
    }

    public LocalDateTime getCriadoAs() {
        return this.criadoAs;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaBancaria other = (ContaBancaria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
