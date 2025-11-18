package br.com.fiap.global.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    // Construtor que recebe a mensagem de erro
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}