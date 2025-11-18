package br.com.fiap.global.model;

import java.time.LocalDate;
import java.io.Serializable;

public class Challenge implements Serializable {
    private Long id;
    private String nomeChallenge;
    private String descricaoChallenge;
    private LocalDate tempo;
    private Curso curso;

    public Challenge() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeChallenge() { return nomeChallenge; }
    public void setNomeChallenge(String nomeChallenge) { this.nomeChallenge = nomeChallenge; }

    public String getDescricaoChallenge() { return descricaoChallenge; }
    public void setDescricaoChallenge(String descricaoChallenge) { this.descricaoChallenge = descricaoChallenge; }

    public LocalDate getTempo() { return tempo; }
    public void setTempo(LocalDate tempo) { this.tempo = tempo; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}
