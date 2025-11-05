package br.com.fiap.sprint.resource.consulta;

import br.com.fiap.sprint.dto.consulta.ConsultaRequest;
import br.com.fiap.sprint.dto.consulta.ConsultaResponse;
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
    public Response listarTodas() {
        List<ConsultaResponse> consultas = service.listarTodos();
        return Response.ok(consultas).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        ConsultaResponse consulta = service.buscarPorId(id);
        if (consulta == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Consulta não encontrada.")
                    .build();
        }
        return Response.ok(consulta).build();
    }

    @POST
    public Response criar(@Valid ConsultaRequest request) {
        ConsultaResponse nova = service.salvar(request);
        return Response.status(Response.Status.CREATED)
                .entity(nova)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid ConsultaRequest request) {
        ConsultaResponse atualizada = service.atualizar(id, request);
        if (atualizada == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Consulta não encontrada para atualização.")
                    .build();
        }
        return Response.ok(atualizada).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") Long id) {
        boolean removido = service.remover(id);
        if (!removido) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Consulta não encontrada para remoção.")
                    .build();
        }
        return Response.noContent().build();
    }
}
