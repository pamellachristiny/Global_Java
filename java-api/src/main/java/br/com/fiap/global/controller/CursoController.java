package br.com.fiap.global.resource;

import br.com.fiap.global.model.Curso;
import br.com.fiap.global.service.CursoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoController {

    @Inject
    CursoService cursoService;

    @GET
    public List<Curso> listarTodos() {
        return cursoService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Curso> curso = cursoService.findById(id);
        return curso.map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response criarCurso(Curso novoCurso) {
        Curso salvoCurso = cursoService.create(novoCurso);
        return Response.status(Response.Status.CREATED).entity(salvoCurso).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCurso(@PathParam("id") Long id, Curso cursoAtualizado) {
        Optional<Curso> curso = cursoService.update(id, cursoAtualizado);

        return curso.map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deletarCurso(@PathParam("id") Long id) {
        if (cursoService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}