package com.br.dto;

public class RequestDTO {

    private String nome;

    public RequestDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
