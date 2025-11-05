package br.com.fiap.sprint.repository.impl;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConsultaRepositoryImpl implements ConsultaRepository {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm562822";
    private static final String PASSWORD = "130997";

    @Override
    public Consulta salvar(Consulta c) {
        String sql = "INSERT INTO T_HC_CONSULTA (ID_CONSULTA, DT_HR_CONSULTA, DS_MODALIDADE) " +
                "VALUES (SQ_T_HC_CONSULTA.NEXTVAL, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(c.getDataHora()));
            ps.setString(2, c.getModalidade());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar consulta: " + e.getMessage());
        }

        return c;
    }

    @Override
    public List<Consulta> listarTodos() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT ID_CONSULTA, DT_HR_CONSULTA, DS_MODALIDADE FROM T_HC_CONSULTA ORDER BY ID_CONSULTA";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getLong("ID_CONSULTA"));
                Timestamp dataHora = rs.getTimestamp("DT_HR_CONSULTA");
                c.setDataHora(dataHora != null ? dataHora.toLocalDateTime() : null);
                c.setModalidade(rs.getString("DS_MODALIDADE").trim());
                consultas.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }

        return consultas;
    }

    @Override
    public Consulta buscarPorId(Long id) {
        String sql = "SELECT ID_CONSULTA, DT_HR_CONSULTA, DS_MODALIDADE FROM T_HC_CONSULTA WHERE ID_CONSULTA = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getLong("ID_CONSULTA"));
                Timestamp dataHora = rs.getTimestamp("DT_HR_CONSULTA");
                c.setDataHora(dataHora != null ? dataHora.toLocalDateTime() : null);
                c.setModalidade(rs.getString("DS_MODALIDADE").trim());
                return c;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Consulta atualizar(Consulta c) {
        String sql = "UPDATE T_HC_CONSULTA SET DT_HR_CONSULTA = ?, DS_MODALIDADE = ? WHERE ID_CONSULTA = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(c.getDataHora()));
            ps.setString(2, c.getModalidade());
            ps.setLong(3, c.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        }

        return c;
    }

    @Override
    public void remover(Long id) {
        String sql = "DELETE FROM T_HC_CONSULTA WHERE ID_CONSULTA = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao remover consulta: " + e.getMessage());
        }
    }
}
