package br.com.fiap.sprint.service.consulta;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.dto.consulta.ConsultaRequest;
import br.com.fiap.sprint.dto.consulta.ConsultaResponse;
import br.com.fiap.sprint.mapper.ConsultaMapper;
import br.com.fiap.sprint.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository repository;

    public List<ConsultaResponse> listarTodos() {
        return repository.listarTodos()
                .stream()
                .map(ConsultaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ConsultaResponse buscarPorId(Long id) {
        Consulta consulta = repository.buscarPorId(id);
        return consulta != null ? ConsultaMapper.toResponse(consulta) : null;
    }

    public ConsultaResponse salvar(ConsultaRequest request) {
        Consulta consulta = ConsultaMapper.toDomain(request);
        Consulta salva = repository.salvar(consulta);
        return ConsultaMapper.toResponse(salva);
    }

    public ConsultaResponse atualizar(Long id, ConsultaRequest request) {
        Consulta existente = repository.buscarPorId(id);
        if (existente == null) return null;

        Consulta atualizada = ConsultaMapper.toDomain(request);
        atualizada.setId(id);

        Consulta salva = repository.atualizar(atualizada);
        return ConsultaMapper.toResponse(salva);
    }

    public boolean remover(Long id) {
        Consulta existente = repository.buscarPorId(id);
        if (existente == null) return false;

        repository.remover(id);
        return true;
    }
}
