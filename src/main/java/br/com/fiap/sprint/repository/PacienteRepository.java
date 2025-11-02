package br.com.fiap.sprint.repository;

import br.com.fiap.sprint.domain.Paciente;
import java.util.List;


public interface PacienteRepository {

    Paciente salvar(Paciente paciente);

    List<Paciente> listarTodos();

    Paciente buscarPorId(Long id);

    Paciente buscarPorCpf(String cpf);

    Paciente atualizar(Paciente paciente);

    void remover(Long id);
}
