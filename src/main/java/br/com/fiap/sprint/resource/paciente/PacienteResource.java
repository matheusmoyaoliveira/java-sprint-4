package br.com.fiap.sprint.resource.paciente;

import br.com.fiap.sprint.domain.Paciente;
import br.com.fiap.sprint.dto.paciente.PacienteRequest;
import br.com.fiap.sprint.dto.paciente.PacienteResponse;
import br.com.fiap.sprint.mapper.PacienteMapper;
import br.com.fiap.sprint.service.paciente.PacienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    PacienteService service;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String home() {
        return """
            <html>
              <body style='font-family:Arial; background:#f7f7f7; color:#333; padding:20px'>
                <h2>🚀 API Java Sprint 4 - FIAP</h2>
                <p>Bem-vindo à API Hospitalar!</p>
                <hr>
                <h3>🔗 Endpoints disponíveis:</h3>
                <ul>
                  <li><b>Pacientes:</b> /pacientes</li>
                  <li><b>Médicos:</b> /medicos</li>
                  <li><b>Consultas:</b> /consultas</li>
                </ul>
                <p><b>Matheus Moya de Oliveira</b> - RM562822</p>
                <p><b>Daniel Nicolas Leoterio</b> - RM562186</p>
                <p><b>Ana Carolina Pereira Fontes</b> - RM562145</p>
              </body>
            </html>
        """;
    }

    @Path("/pacientes")
    public static class PacientesEndpoint {

        @Inject
        PacienteService service;

        @GET
        public List<PacienteResponse> listarTodos() {
            return PacienteMapper.toResponseList(service.listarTodos());
        }

        @GET
        @Path("/{id}")
        public Response buscarPorId(@PathParam("id") Long id) {
            Paciente paciente = service.buscarPorId(id);
            return Response.ok(PacienteMapper.toResponse(paciente)).build();
        }

        @POST
        public Response criar(@Valid PacienteRequest request) {
            Paciente novo = PacienteMapper.toDomain(request);
            Paciente salvo = service.criar(novo);
            return Response.status(Response.Status.CREATED)
                    .entity(PacienteMapper.toResponse(salvo))
                    .build();
        }

        @PUT
        @Path("/{id}")
        public Response atualizar(@PathParam("id") Long id, @Valid PacienteRequest request) {
            Paciente atualizado = PacienteMapper.toDomain(request);
            Paciente salvo = service.atualizar(id, atualizado);
            return Response.ok(PacienteMapper.toResponse(salvo)).build();
        }

        @DELETE
        @Path("/{id}")
        public Response remover(@PathParam("id") Long id) {
            service.remover(id);
            return Response.noContent().build();
        }
    }
}
