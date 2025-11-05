package br.com.fiap.sprint.mapper;

import br.com.fiap.sprint.domain.Paciente;
import br.com.fiap.sprint.dto.paciente.PacienteRequest;
import br.com.fiap.sprint.dto.paciente.PacienteResponse;

public final class PacienteMapper {

    private PacienteMapper() {}

    public static Paciente toDomain(PacienteRequest req) {
        if (req == null) return null;
        Paciente p = new Paciente();
        p.setNome(req.getNome());
        p.setCpf(req.getCpf());
        p.setRg(req.getRg());
        p.setAltura(req.getAltura());
        p.setPeso(req.getPeso());
        p.setDataNascimento(req.getDataNascimento()); // LocalDate no request
        p.setEscolaridade(req.getEscolaridade());
        p.setEstadoCivil(req.getEstadoCivil());
        p.setDescricao(req.getDescricao());
        return p;
    }

    public static PacienteResponse toResponse(Paciente p) {
        if (p == null) return null;
        PacienteResponse resp = new PacienteResponse();
        resp.setId(p.getId());
        resp.setNome(p.getNome());
        resp.setCpf(p.getCpf());
        resp.setRg(p.getRg());
        resp.setAltura(p.getAltura());
        resp.setPeso(p.getPeso());
        resp.setDataNascimento(p.getDataNascimento()); // LocalDate
        resp.setEscolaridade(p.getEscolaridade());
        resp.setEstadoCivil(p.getEstadoCivil());
        resp.setDescricao(p.getDescricao());
        return resp;
    }
}
