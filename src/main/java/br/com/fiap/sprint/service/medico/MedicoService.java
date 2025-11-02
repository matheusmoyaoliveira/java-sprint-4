package br.com.fiap.sprint.service.medico;

import br.com.fiap.sprint.domain.Medico;
import br.com.fiap.sprint.repository.MedicoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.List;

@ApplicationScoped
public class MedicoService {

    @Inject
    MedicoRepository repository;

    public List<Medico> listarTodos() {
        return repository.listarTodos();
    }

    public Medico buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Medico criar(Medico medico) {
        Medico existente = repository.buscarPorCrm(medico.getCrm());
        if (existente != null) {
            throw new RuntimeException("Já existe um médico com esse CRM!");
        }
        return repository.salvar(medico);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        Medico existente = repository.buscarPorId(id);
        if (existente == null) {
            throw new RuntimeException("Médico não encontrado para atualização!");
        }

        existente.setNome(medicoAtualizado.getNome());
        existente.setEspecialidade(medicoAtualizado.getEspecialidade());
        existente.setTelefone(medicoAtualizado.getTelefone());
        existente.setEmail(medicoAtualizado.getEmail());

        return repository.atualizar(existente);
    }

    public void remover(Long id) {
        repository.remover(id);
    }
}
