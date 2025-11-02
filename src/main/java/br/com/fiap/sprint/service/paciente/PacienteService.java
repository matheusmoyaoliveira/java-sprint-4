package br.com.fiap.sprint.service.paciente;

import br.com.fiap.sprint.domain.Paciente;
import br.com.fiap.sprint.exception.BusinessException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class PacienteService {

    @Inject
    br.com.fiap.sprint.repository.PacienteRepository repository;

    public List<Paciente> listarTodos() {
        return repository.listarTodos();
    }

    public Paciente buscarPorId(Long id) {
        Paciente paciente = repository.buscarPorId(id);
        if (paciente == null) {
            throw new NotFoundException("Paciente com id " + id + " não encontrado.");
        }
        return paciente;
    }

    public Paciente criar(Paciente novo) {
        boolean existe = repository.buscarPorCpf(novo.getCpf()) != null;
        if (existe) {
            throw new BusinessException("CPF já cadastrado: " + novo.getCpf());
        }
        return repository.salvar(novo);
    }

    public Paciente atualizar(Long id, Paciente atualizado) {
        Paciente existente = buscarPorId(id);
        atualizado.setId(existente.getId());
        return repository.atualizar(atualizado);
    }

    public void remover(Long id) {
        Paciente existente = buscarPorId(id);
        repository.remover(existente.getId());
    }
}
