package br.com.fiap.sprint.repository.impl;

import br.com.fiap.sprint.domain.Medico;
import br.com.fiap.sprint.repository.MedicoRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MedicoRepositoryImpl implements MedicoRepository {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm562822";
    private static final String PASSWORD = "130997";

    @Override
    public Medico salvar(Medico m) {
        String sql = "INSERT INTO MEDICO (NOME, CRM, ESPECIALIDADE, TELEFONE, EMAIL) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setString(3, m.getEspecialidade());
            stmt.setString(4, m.getTelefone());
            stmt.setString(5, m.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<Medico> listarTodos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM MEDICO";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getLong("ID_MEDICO"));
                m.setNome(rs.getString("NOME"));
                m.setCrm(rs.getString("CRM"));
                m.setEspecialidade(rs.getString("ESPECIALIDADE"));
                m.setTelefone(rs.getString("TELEFONE"));
                m.setEmail(rs.getString("EMAIL"));
                medicos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    @Override
    public Medico buscarPorId(Long id) {
        String sql = "SELECT * FROM MEDICO WHERE ID_MEDICO = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getLong("ID_MEDICO"));
                m.setNome(rs.getString("NOME"));
                m.setCrm(rs.getString("CRM"));
                m.setEspecialidade(rs.getString("ESPECIALIDADE"));
                m.setTelefone(rs.getString("TELEFONE"));
                m.setEmail(rs.getString("EMAIL"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medico buscarPorCrm(String crm) {
        String sql = "SELECT * FROM MEDICO WHERE CRM = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crm);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getLong("ID_MEDICO"));
                m.setNome(rs.getString("NOME"));
                m.setCrm(rs.getString("CRM"));
                m.setEspecialidade(rs.getString("ESPECIALIDADE"));
                m.setTelefone(rs.getString("TELEFONE"));
                m.setEmail(rs.getString("EMAIL"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medico atualizar(Medico m) {
        String sql = "UPDATE MEDICO SET NOME = ?, ESPECIALIDADE = ?, TELEFONE = ?, EMAIL = ? WHERE ID_MEDICO = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEspecialidade());
            stmt.setString(3, m.getTelefone());
            stmt.setString(4, m.getEmail());
            stmt.setLong(5, m.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public void remover(Long id) {
        String sql = "DELETE FROM MEDICO WHERE ID_MEDICO = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
