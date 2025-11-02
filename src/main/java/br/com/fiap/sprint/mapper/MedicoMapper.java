package br.com.fiap.sprint.mapper;

import br.com.fiap.sprint.domain.Medico;
import br.com.fiap.sprint.dto.medico.MedicoRequest;
import br.com.fiap.sprint.dto.medico.MedicoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MedicoMapper {

    public static Medico toDomain(MedicoRequest request) {
        Medico m = new Medico();
        m.setNome(request.getNome());
        m.setCrm(request.getCrm());
        m.setEspecialidade(request.getEspecialidade());
        m.setTelefone(request.getTelefone());
        m.setEmail(request.getEmail());
        return m;
    }

    public static MedicoResponse toResponse(Medico medico) {
        MedicoResponse r = new MedicoResponse();
        r.setId(medico.getId());
        r.setNome(medico.getNome());
        r.setCrm(medico.getCrm());
        r.setEspecialidade(medico.getEspecialidade());
        r.setTelefone(medico.getTelefone());
        r.setEmail(medico.getEmail());
        return r;
    }

    public static List<MedicoResponse> toResponseList(List<Medico> medicos) {
        return medicos.stream()
                .map(MedicoMapper::toResponse)
                .collect(Collectors.toList());
    }

}
