package br.com.fiap.global.model;

import java.io.Serializable;

public class Professor implements Serializable {
    private Long id;
    private String nome;
    private String formacao;

    public Professor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }
}
