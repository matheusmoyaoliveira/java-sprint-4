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
        String sql = "INSERT INTO T_HC_MEDICO (ID_MEDICO, NM_MEDICO, NR_CRM) " +
                "VALUES (SQ_T_HC_MEDICO.NEXTVAL, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<Medico> listarTodos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT ID_MEDICO, NM_MEDICO, NR_CRM FROM T_HC_MEDICO ORDER BY ID_MEDICO";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getLong("ID_MEDICO"));
                m.setNome(rs.getString("NM_MEDICO").trim());
                m.setCrm(rs.getString("NR_CRM").trim());
                medicos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    @Override
    public Medico buscarPorId(Long id) {
        String sql = "SELECT ID_MEDICO, NM_MEDICO, NR_CRM FROM T_HC_MEDICO WHERE ID_MEDICO = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getLong("ID_MEDICO"));
                    m.setNome(rs.getString("NM_MEDICO").trim());
                    m.setCrm(rs.getString("NR_CRM").trim());
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medico buscarPorCrm(String crm) {
        String sql = "SELECT ID_MEDICO, NM_MEDICO, NR_CRM FROM T_HC_MEDICO WHERE TRIM(NR_CRM) = TRIM(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getLong("ID_MEDICO"));
                    m.setNome(rs.getString("NM_MEDICO").trim());
                    m.setCrm(rs.getString("NR_CRM").trim());
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medico atualizar(Medico m) {
        String sql = "UPDATE T_HC_MEDICO SET NM_MEDICO = ?, NR_CRM = ? WHERE ID_MEDICO = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setLong(3, m.getId());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public void remover(Long id) {
        String sql = "DELETE FROM T_HC_MEDICO WHERE ID_MEDICO = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
