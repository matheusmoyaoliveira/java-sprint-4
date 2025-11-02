package br.com.fiap.sprint.mapper;

import br.com.fiap.sprint.domain.Paciente;
import br.com.fiap.sprint.dto.paciente.PacienteRequest;
import br.com.fiap.sprint.dto.paciente.PacienteResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class PacienteMapper {

    public PacienteMapper() {
    }

    public static Paciente toDomain(PacienteRequest req) {
        if (req == null) {
            return null;
        }

        Paciente p = new Paciente();
        p.setNome(req.getNome());
        p.setEmail(req.getEmail());
        p.setCpf(req.getCpf());
        p.setTelefone(req.getTelefone());
        p.setDataNascimento(req.getDataNascimento());
        return p;
    }

    public static PacienteResponse toResponse(Paciente domain) {
        if (domain == null) {
            return null;
        }

        return new PacienteResponse(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getCpf(),
                domain.getTelefone(),
                domain.getDataNascimento()
        );
    }

    public static List<PacienteResponse> toResponseList(List<Paciente> lista) {
        if (lista == null) {
            return List.of();
        }

        return lista.stream().filter(Objects::nonNull).map(PacienteMapper::toResponse).collect(Collectors.toList());
    }

    public static void apply(Paciente existente, PacienteRequest req) {
        if (existente == null || req == null) {
            return;
        }

        if (req.getNome() != null) existente.setNome(req.getNome());
        if (req.getEmail() != null) existente.setEmail(req.getEmail());
        if (req.getCpf() != null) existente.setCpf(req.getCpf());
        if (req.getTelefone() != null) existente.setTelefone(req.getTelefone());
        if (req.getDataNascimento() != null) existente.setDataNascimento(req.getDataNascimento());
    }
}
