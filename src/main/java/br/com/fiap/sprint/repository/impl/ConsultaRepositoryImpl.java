package br.com.fiap.sprint.repository.impl;

import br.com.fiap.sprint.domain.Consulta;
import br.com.fiap.sprint.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConsultaRepositoryImpl implements ConsultaRepository {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm562822";
    private static final String PASSWORD = "130997";

    @Override
    public Consulta salvar(Consulta consulta) {
        String sql = "INSERT INTO CONSULTA (DATA_CONSULTA, STATUS, ID_PACIENTE, ID_MEDICO) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(true); // ✅ ativa o commit automático

            PreparedStatement ps = conn.prepareStatement(sql, new String[] {"ID_CONSULTA"});
            ps.setDate(1, java.sql.Date.valueOf(consulta.getDataConsulta()));
            ps.setString(2, consulta.getStatus());
            ps.setLong(3, consulta.getIdPaciente());
            ps.setLong(4, consulta.getIdMedico());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) consulta.setId(rs.getLong(1));

        } catch (SQLException e) {
            System.out.println("Erro ao salvar consulta: " + e.getMessage());
        }

        return consulta;
    }

    @Override
    public List<Consulta> listarTodos() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getLong("ID_CONSULTA"));
                c.setDataConsulta(rs.getDate("DATA_CONSULTA").toLocalDate());
                c.setStatus(rs.getString("STATUS"));
                c.setIdPaciente(rs.getLong("ID_PACIENTE"));
                c.setIdMedico(rs.getLong("ID_MEDICO"));
                consultas.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }

        return consultas;
    }

    @Override
    public Consulta buscarPorId(Long id) {
        String sql = "SELECT * FROM CONSULTA WHERE ID_CONSULTA = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getLong("ID_CONSULTA"));
                c.setDataConsulta(rs.getDate("DATA_CONSULTA").toLocalDate());
                c.setStatus(rs.getString("STATUS"));
                c.setIdPaciente(rs.getLong("ID_PACIENTE"));
                c.setIdMedico(rs.getLong("ID_MEDICO"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Consulta atualizar(Consulta c) {
        String sql = "UPDATE CONSULTA SET DATA_CONSULTA = ?, STATUS = ?, ID_PACIENTE = ?, ID_MEDICO = ? WHERE ID_CONSULTA = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(c.getDataConsulta()));
            stmt.setString(2, c.getStatus());
            stmt.setLong(3, c.getIdPaciente());
            stmt.setLong(4, c.getIdMedico());
            stmt.setLong(5, c.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void remover(Long id) {
        String sql = "DELETE FROM CONSULTA WHERE ID_CONSULTA = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
