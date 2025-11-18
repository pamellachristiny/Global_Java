package br.com.fiap.global.exception;

public class RequisicaoInvalidaException extends RuntimeException {

    public RequisicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}