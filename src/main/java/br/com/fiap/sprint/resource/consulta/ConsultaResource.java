package br.com.fiap.sprint.resource.consulta;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.dto.consulta.ConsultaRequest;
import br.com.fiap.sprint.dto.consulta.ConsultaResponse;
import br.com.fiap.sprint.mapper.ConsultaMapper;
import br.com.fiap.sprint.service.consulta.ConsultaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaService service;

    @GET
    public List<ConsultaResponse> listarTodas() {
        return ConsultaMapper.toResponseList(service.listarTodos());
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Consulta consulta = service.buscarPorId(id);
        return Response.ok(ConsultaMapper.toResponse(consulta)).build();
    }

    @POST
    public Response criar(@Valid ConsultaRequest request) {
        Consulta nova = ConsultaMapper.toDomain(request);
        Consulta salva = service.criar(nova);
        return Response.status(Response.Status.CREATED)
                .entity(ConsultaMapper.toResponse(salva))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid ConsultaRequest request) {
        Consulta atualizada = ConsultaMapper.toDomain(request);
        Consulta salva = service.atualizar(id, atualizada);
        return Response.ok(ConsultaMapper.toResponse(salva)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        service.remover(id);
        return Response.noContent().build();
    }
}
