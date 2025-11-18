package br.com.fiap.global.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider // Anotação JAX-RS para registrar o mapeador de exceções
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        // Objeto DTO simples para padronizar o erro no JSON de resposta
        ErrorResponse error = new ErrorResponse(exception.getMessage());

        if (exception instanceof RecursoNaoEncontradoException) {
            // Mapeia RecursoNaoEncontradoException -> HTTP 404 Not Found
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();

        } else if (exception instanceof RequisicaoInvalidaException) {
            // Mapeia RequisicaoInvalidaException -> HTTP 400 Bad Request
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();

        } else {
            // Qualquer outra exceção não tratada -> HTTP 500 Internal Server Error
            // Idealmente, você logaria essa exceção
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro interno do servidor: " + exception.getMessage()))
                    .build();
        }
    }

    // Classe auxiliar para formatar a resposta JSON do erro
    private static class ErrorResponse {
        public String mensagem;

        public ErrorResponse(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}