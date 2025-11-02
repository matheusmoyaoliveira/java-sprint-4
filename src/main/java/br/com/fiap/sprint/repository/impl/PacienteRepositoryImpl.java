package br.com.fiap.sprint.repository.impl;

import br.com.fiap.sprint.domain.Paciente;
import br.com.fiap.sprint.repository.PacienteRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PacienteRepositoryImpl implements PacienteRepository {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm562822";
    private static final String PASSWORD = "130997";

    private static final List<Paciente> banco = new ArrayList<>();

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getLong("ID_PACIENTE"));
                p.setNome(rs.getString("NOME"));
                p.setCpf(rs.getString("CPF"));
                p.setEmail(rs.getString("EMAIL"));
                p.setTelefone(rs.getString("TELEFONE"));
                pacientes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        String sql = "SELECT * FROM PACIENTE WHERE ID_PACIENTE = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getLong("ID_PACIENTE"));
                p.setNome(rs.getString("NOME"));
                p.setCpf(rs.getString("CPF"));
                p.setEmail(rs.getString("EMAIL"));
                p.setTelefone(rs.getString("TELEFONE"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM PACIENTE WHERE CPF = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getLong("ID_PACIENTE"));
                p.setNome(rs.getString("NOME"));
                p.setCpf(rs.getString("CPF"));
                p.setEmail(rs.getString("EMAIL"));
                p.setTelefone(rs.getString("TELEFONE"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paciente salvar(Paciente p) {
        String sql = "INSERT INTO PACIENTE (NOME, CPF, EMAIL, TELEFONE) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Paciente atualizar(Paciente p) {
        String sql = "UPDATE PACIENTE SET NOME = ?, EMAIL = ?, TELEFONE = ? WHERE ID_PACIENTE = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getTelefone());
            stmt.setLong(4, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void remover(Long id) {
        String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
