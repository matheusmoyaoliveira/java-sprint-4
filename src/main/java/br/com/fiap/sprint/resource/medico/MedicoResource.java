package br.com.fiap.sprint.resource.medico;

import br.com.fiap.sprint.domain.Medico;
import br.com.fiap.sprint.dto.medico.MedicoRequest;
import br.com.fiap.sprint.dto.medico.MedicoResponse;
import br.com.fiap.sprint.mapper.MedicoMapper;
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
    public List<MedicoResponse> listarTodos() {
        return MedicoMapper.toResponseList(service.listarTodos());
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Medico medico = service.buscarPorId(id);
        return Response.ok(MedicoMapper.toResponse(medico)).build();
    }

    @POST
    public Response criar(@Valid MedicoRequest request) {
        Medico novo = MedicoMapper.toDomain(request);
        Medico salvo = service.criar(novo);
        return Response.status(Response.Status.CREATED)
                .entity(MedicoMapper.toResponse(salvo))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid MedicoRequest request) {
        Medico atualizado = MedicoMapper.toDomain(request);
        Medico salvo = service.atualizar(id, atualizado);
        return Response.ok(MedicoMapper.toResponse(salvo)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(id);
        return Response.noContent().build();
    }
}
