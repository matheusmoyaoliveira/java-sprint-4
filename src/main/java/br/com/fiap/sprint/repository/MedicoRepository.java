package br.com.fiap.sprint.repository;

import br.com.fiap.sprint.domain.Medico;
import java.util.List;

public interface MedicoRepository {
    Medico salvar(Medico medico);
    List<Medico> listarTodos();
    Medico buscarPorId(Long id);
    Medico buscarPorCrm(String crm);
    Medico atualizar(Medico medico);
    void remover(Long id);
}
