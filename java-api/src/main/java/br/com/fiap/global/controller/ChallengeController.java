package br.com.fiap.global.resource;

import br.com.fiap.global.model.Challenge;
import br.com.fiap.global.service.ChallengeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/challenges")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChallengeController {

    @Inject
    ChallengeService challengeService;

    @GET
    public List<Challenge> listarTodos() {
        return challengeService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Optional<Challenge> challenge = challengeService.findById(id);
        return challenge.map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response criarChallenge(Challenge novoChallenge) {
        Challenge salvoChallenge = challengeService.create(novoChallenge);
        return Response.status(Response.Status.CREATED).entity(salvoChallenge).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarChallenge(@PathParam("id") Long id, Challenge challengeAtualizado) {
        Optional<Challenge> challenge = challengeService.update(id, challengeAtualizado);

        return challenge.map(c -> Response.ok(c).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deletarChallenge(@PathParam("id") Long id) {
        if (challengeService.delete(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}