package br.com.fiap.sprint.mapper;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.dto.consulta.ConsultaRequest;
import br.com.fiap.sprint.dto.consulta.ConsultaResponse;

import java.time.LocalDateTime;

public final class ConsultaMapper {

    private ConsultaMapper() {}

    public static Consulta toDomain(ConsultaRequest req) {
        if (req == null) return null;
        Consulta c = new Consulta();

        LocalDateTime dt = req.getDataHora();
        c.setDataHora(dt);
        c.setModalidade(req.getModalidade());
        return c;
    }

    public static ConsultaResponse toResponse(Consulta c) {
        if (c == null) return null;
        ConsultaResponse resp = new ConsultaResponse();
        resp.setId(c.getId());
        resp.setDataHora(c.getDataHora());
        resp.setModalidade(c.getModalidade());
        return resp;
    }
}
