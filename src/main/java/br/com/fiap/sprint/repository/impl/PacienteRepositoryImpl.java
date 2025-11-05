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

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT ID_PACIENTE, NM_PACIENTE, NR_CPF, NR_RG, NR_ALTURA, NR_PESO, " +
                "DT_NASCIMENTO, DS_ESCOLARIDADE, DS_ESTADO_CIVIL, DS_DESCRICAO " +
                "FROM T_HC_PACIENTE ORDER BY ID_PACIENTE";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente p = mapRow(rs);
                pacientes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        String sql = "SELECT ID_PACIENTE, NM_PACIENTE, NR_CPF, NR_RG, NR_ALTURA, NR_PESO, " +
                "DT_NASCIMENTO, DS_ESCOLARIDADE, DS_ESTADO_CIVIL, DS_DESCRICAO " +
                "FROM T_HC_PACIENTE WHERE ID_PACIENTE = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT ID_PACIENTE, NM_PACIENTE, NR_CPF, NR_RG, NR_ALTURA, NR_PESO, " +
                "DT_NASCIMENTO, DS_ESCOLARIDADE, DS_ESTADO_CIVIL, DS_DESCRICAO " +
                "FROM T_HC_PACIENTE WHERE TRIM(NR_CPF) = TRIM(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paciente salvar(Paciente p) {
        String sql = "INSERT INTO T_HC_PACIENTE " +
                "(ID_PACIENTE, NM_PACIENTE, NR_CPF, NR_RG, NR_ALTURA, NR_PESO, " +
                "DT_NASCIMENTO, DS_ESCOLARIDADE, DS_ESTADO_CIVIL, DS_DESCRICAO) " +
                "VALUES (SQ_T_HC_PACIENTE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getRg());

            if (p.getAltura() != null) stmt.setDouble(4, p.getAltura()); else stmt.setNull(4, Types.NUMERIC);
            if (p.getPeso() != null)   stmt.setDouble(5, p.getPeso());   else stmt.setNull(5, Types.NUMERIC);

            if (p.getDataNascimento() != null) {
                stmt.setDate(6, Date.valueOf(p.getDataNascimento()));
            } else {
                stmt.setNull(6, Types.DATE);
            }

            stmt.setString(7, p.getEscolaridade());
            stmt.setString(8, p.getEstadoCivil());
            stmt.setString(9, p.getDescricao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Paciente atualizar(Paciente p) {
        String sql = "UPDATE T_HC_PACIENTE SET " +
                "NM_PACIENTE = ?, NR_CPF = ?, NR_RG = ?, NR_ALTURA = ?, NR_PESO = ?, " +
                "DT_NASCIMENTO = ?, DS_ESCOLARIDADE = ?, DS_ESTADO_CIVIL = ?, DS_DESCRICAO = ? " +
                "WHERE ID_PACIENTE = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getRg());

            if (p.getAltura() != null) stmt.setDouble(4, p.getAltura()); else stmt.setNull(4, Types.NUMERIC);
            if (p.getPeso() != null)   stmt.setDouble(5, p.getPeso());   else stmt.setNull(5, Types.NUMERIC);

            if (p.getDataNascimento() != null) {
                stmt.setDate(6, Date.valueOf(p.getDataNascimento()));
            } else {
                stmt.setNull(6, Types.DATE);
            }

            stmt.setString(7, p.getEscolaridade());
            stmt.setString(8, p.getEstadoCivil());
            stmt.setString(9, p.getDescricao());
            stmt.setLong(10, p.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void remover(Long id) {
        String sql = "DELETE FROM T_HC_PACIENTE WHERE ID_PACIENTE = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Paciente mapRow(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setId(rs.getLong("ID_PACIENTE"));

        p.setNome(safeTrim(rs.getString("NM_PACIENTE")));
        p.setCpf(safeTrim(rs.getString("NR_CPF")));
        p.setRg(safeTrim(rs.getString("NR_RG")));
        double alt = rs.getDouble("NR_ALTURA");
        p.setAltura(rs.wasNull() ? null : alt);
        double pes = rs.getDouble("NR_PESO");
        p.setPeso(rs.wasNull() ? null : pes);

        Date dn = rs.getDate("DT_NASCIMENTO");
        p.setDataNascimento(dn != null ? dn.toLocalDate() : null);

        p.setEscolaridade(safeTrim(rs.getString("DS_ESCOLARIDADE")));
        p.setEstadoCivil(safeTrim(rs.getString("DS_ESTADO_CIVIL")));
        p.setDescricao(safeTrim(rs.getString("DS_DESCRICAO")));
        return p;
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
