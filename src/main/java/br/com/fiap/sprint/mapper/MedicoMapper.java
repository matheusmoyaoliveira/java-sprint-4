package br.com.fiap.sprint.mapper;

import br.com.fiap.sprint.domain.Medico;
import br.com.fiap.sprint.dto.medico.MedicoRequest;
import br.com.fiap.sprint.dto.medico.MedicoResponse;

public final class MedicoMapper {

    private MedicoMapper() {}

    public static Medico toDomain(MedicoRequest req) {
        if (req == null) return null;
        Medico m = new Medico();
        m.setNome(req.getNome());
        m.setCrm(req.getCrm());
        return m;
    }

    public static MedicoResponse toResponse(Medico m) {
        if (m == null) return null;
        MedicoResponse resp = new MedicoResponse();
        resp.setId(m.getId());
        resp.setNome(m.getNome());
        resp.setCrm(m.getCrm());
        return resp;
    }
}
