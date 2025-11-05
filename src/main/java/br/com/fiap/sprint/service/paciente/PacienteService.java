package br.com.fiap.sprint.service.paciente;

import br.com.fiap.sprint.domain.Paciente;
import br.com.fiap.sprint.dto.paciente.PacienteRequest;
import br.com.fiap.sprint.dto.paciente.PacienteResponse;
import br.com.fiap.sprint.mapper.PacienteMapper;
import br.com.fiap.sprint.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository repository;

    public List<PacienteResponse> listarTodos() {
        return repository.listarTodos()
                .stream()
                .map(PacienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponse buscarPorId(Long id) {
        Paciente paciente = repository.buscarPorId(id);
        return paciente != null ? PacienteMapper.toResponse(paciente) : null;
    }

    public PacienteResponse buscarPorCpf(String cpf) {
        Paciente paciente = repository.buscarPorCpf(cpf);
        return paciente != null ? PacienteMapper.toResponse(paciente) : null;
    }

    public PacienteResponse salvar(PacienteRequest request) {
        Paciente paciente = PacienteMapper.toDomain(request);
        Paciente salvo = repository.salvar(paciente);
        return PacienteMapper.toResponse(salvo);
    }

    public PacienteResponse atualizar(Long id, PacienteRequest request) {
        Paciente existente = repository.buscarPorId(id);
        if (existente == null) return null;

        Paciente atualizado = PacienteMapper.toDomain(request);
        atualizado.setId(id);

        Paciente salvo = repository.atualizar(atualizado);
        return PacienteMapper.toResponse(salvo);
    }

    public boolean remover(Long id) {
        Paciente existente = repository.buscarPorId(id);
        if (existente == null) return false;

        repository.remover(id);
        return true;
    }
}
