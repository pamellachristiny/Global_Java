package br.com.fiap.global.resource;

import br.com.fiap.global.model.Professor;
import br.com.fiap.global.service.ProfessorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorController {

    @Inject
    ProfessorService professorService;

    @GET
    public List<Professor> listarTodos() {
        return professorService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Professor> professor = professorService.findById(id);
        return professor.map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response criarProfessor(Professor novoProfessor) {
        Professor salvoProfessor = professorService.create(novoProfessor);
        return Response.status(Response.Status.CREATED).entity(salvoProfessor).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarProfessor(@PathParam("id") Long id, Professor professorAtualizado) {
        Optional<Professor> professor = professorService.update(id, professorAtualizado);

        return professor.map(p -> Response.ok(p).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProfessor(@PathParam("id") Long id) {
        if (professorService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}