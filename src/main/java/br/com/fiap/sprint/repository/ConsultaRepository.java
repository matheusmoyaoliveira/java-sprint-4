package br.com.fiap.sprint.repository;

import br.com.fiap.sprint.domain.Consulta;
import java.util.List;

public interface ConsultaRepository {
    Consulta salvar(Consulta consulta);
    List<Consulta> listarTodos();
    Consulta buscarPorId(Long id);
    Consulta atualizar(Consulta consulta);
    void remover(Long id);
}
