package br.com.fiap.sprint.mapper;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.dto.consulta.ConsultaRequest;
import br.com.fiap.sprint.dto.consulta.ConsultaResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaMapper {

    public static Consulta toDomain(ConsultaRequest request) {
        Consulta c = new Consulta();
        c.setDataConsulta(request.getDataConsulta());
        c.setStatus(request.getStatus());
        c.setIdPaciente(request.getIdPaciente());
        c.setIdMedico(request.getIdMedico());
        return c;
    }

    public static ConsultaResponse toResponse(Consulta consulta) {
        ConsultaResponse r = new ConsultaResponse();
        r.setId(consulta.getId());
        r.setDataConsulta(consulta.getDataConsulta());
        r.setStatus(consulta.getStatus());
        r.setIdPaciente(consulta.getIdPaciente());
        r.setIdMedico(consulta.getIdMedico());
        return r;
    }

    public static List<ConsultaResponse> toResponseList(List<Consulta> consultas) {
        return consultas.stream()
                .map(ConsultaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
