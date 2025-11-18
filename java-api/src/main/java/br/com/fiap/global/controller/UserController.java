package br.com.fiap.global.controller; // Mudança para o pacote 'controller'

import br.com.fiap.global.model.User;
import br.com.fiap.global.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users") // Define a URL base para este recurso
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController { // Nome da classe alterado para UserController

    @Inject
    UserService userService; // Injeção de dependência do Serviço

    // --- GET ALL ---
    // GET /users
    @GET
    public List<User> listarTodos() {
        // Delega para o Service
        return userService.findAll();
    }

    // --- GET BY ID ---
    // GET /users/{id}
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        // Usa o método do Service que lança RecursoNaoEncontradoException (mapeado para 404)
        User user = userService.buscarPorIdOuFalhar(id);
        return Response.ok(user).build();
    }

    // --- CREATE ---
    // POST /users
    @POST
    public Response criarUser(User novoUser) {
        // O Service trata a validação e lança RequisicaoInvalidaException (mapeado para 400)
        User salvoUser = userService.create(novoUser);

        // Retorna HTTP 201 Created
        return Response.status(Response.Status.CREATED).entity(salvoUser).build();
    }

    // --- UPDATE ---
    // PUT /users/{id}
    @PUT
    @Path("/{id}")
    public Response atualizarUser(@PathParam("id") Long id, User userAtualizado) {
        // O Service trata 404 e 400
        User user = userService.update(id, userAtualizado);
        return Response.ok(user).build(); // HTTP 200 OK
    }

    // --- DELETE ---
    // DELETE /users/{id}
    @DELETE
    @Path("/{id}")
    public Response deletarUser(@PathParam("id") Long id) {
        if (userService.delete(id)) {
            return Response.noContent().build(); // HTTP 204 No Content
        } else {
            // Se o deleteById retornar false, significa que o ID não foi encontrado/deletado.
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}