package br.com.fiap.sprint.resource.medico;

import br.com.fiap.sprint.dto.medico.MedicoRequest;
import br.com.fiap.sprint.dto.medico.MedicoResponse;
import br.com.fiap.sprint.service.medico.MedicoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/medicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicoResource {

    @Inject
    MedicoService service;

    @GET
    public Response listarTodos() {
        List<MedicoResponse> medicos = service.listarTodos();
        return Response.ok(medicos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        MedicoResponse medico = service.buscarPorId(id);
        if (medico == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Médico não encontrado.")
                    .build();
        }
        return Response.ok(medico).build();
    }

    @POST
    public Response criar(@Valid MedicoRequest request) {
        MedicoResponse novo = service.salvar(request);
        return Response.status(Response.Status.CREATED)
                .entity(novo)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid MedicoRequest request) {
        MedicoResponse atualizado = service.atualizar(id, request);
        if (atualizado == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Médico não encontrado para atualização.")
                    .build();
        }
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        boolean removido = service.remover(id);
        if (!removido) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Médico não encontrado para remoção.")
                    .build();
        }
        return Response.noContent().build();
    }
}
