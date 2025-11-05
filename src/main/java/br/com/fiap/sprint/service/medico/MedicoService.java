package br.com.fiap.sprint.service.medico;

import br.com.fiap.sprint.domain.Medico;
import br.com.fiap.sprint.dto.medico.MedicoRequest;
import br.com.fiap.sprint.dto.medico.MedicoResponse;
import br.com.fiap.sprint.mapper.MedicoMapper;
import br.com.fiap.sprint.repository.MedicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MedicoService {

    @Inject
    MedicoRepository repository;

    public List<MedicoResponse> listarTodos() {
        return repository.listarTodos()
                .stream()
                .map(MedicoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MedicoResponse buscarPorId(Long id) {
        Medico medico = repository.buscarPorId(id);
        return medico != null ? MedicoMapper.toResponse(medico) : null;
    }

    public MedicoResponse buscarPorCrm(String crm) {
        Medico medico = repository.buscarPorCrm(crm);
        return medico != null ? MedicoMapper.toResponse(medico) : null;
    }

    public MedicoResponse salvar(MedicoRequest request) {
        Medico medico = MedicoMapper.toDomain(request);
        Medico salvo = repository.salvar(medico);
        return MedicoMapper.toResponse(salvo);
    }

    public MedicoResponse atualizar(Long id, MedicoRequest request) {
        Medico existente = repository.buscarPorId(id);
        if (existente == null) return null;

        Medico atualizado = MedicoMapper.toDomain(request);
        atualizado.setId(id);

        Medico salvo = repository.atualizar(atualizado);
        return MedicoMapper.toResponse(salvo);
    }

    public boolean remover(Long id) {
        Medico existente = repository.buscarPorId(id);
        if (existente == null) return false;

        repository.remover(id);
        return true;
    }
}
