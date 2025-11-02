package br.com.fiap.sprint.service.consulta;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository repository;

    public List<Consulta> listarTodos() {
        return repository.listarTodos();
    }

    public Consulta buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Consulta criar(Consulta consulta) {
        if (consulta.getIdPaciente() == null || consulta.getIdMedico() == null) {
            throw new RuntimeException("IDs de paciente e médico são obrigatórios!");
        }

        return repository.salvar(consulta);
    }

    public Consulta atualizar(Long id, Consulta novaConsulta) {
        Consulta existente = repository.buscarPorId(id);
        if (existente == null) {
            throw new RuntimeException("Consulta não encontrada para atualização!");
        }

        existente.setDataConsulta(novaConsulta.getDataConsulta());
        existente.setStatus(novaConsulta.getStatus());
        existente.setIdPaciente(novaConsulta.getIdPaciente());
        existente.setIdMedico(novaConsulta.getIdMedico());

        return repository.atualizar(existente);
    }

    public void remover(Long id) {
        repository.remover(id);
    }
}
