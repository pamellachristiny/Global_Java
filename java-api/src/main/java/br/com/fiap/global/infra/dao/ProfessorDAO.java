package br.com.fiap.global.dao;

import br.com.fiap.global.model.Professor;
import br.com.fiap.global.infra.Connectionfactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfessorDAO {

    public List<Professor> findAll() {
        String sql = "SELECT * FROM TB_PROFESSOR";
        List<Professor> list = new ArrayList<>();

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Professor e = mapRow(rs);
                list.add(e);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return list;
    }

    public Optional<Professor> findById(Long id) {
        String sql = "SELECT * FROM TB_PROFESSOR WHERE id = ?";

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.empty();
    }

    @Transactional
    public Professor create(Professor obj) {
        throw new UnsupportedOperationException("Implement create for Professor");
    }

    @Transactional
    public Optional<Professor> update(Professor obj) {
        throw new UnsupportedOperationException("Implement update for Professor");
    }

    @Transactional
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM TB_PROFESSOR WHERE id = ?";

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int deleted = ps.executeUpdate();
            return deleted > 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Professor mapRow(ResultSet rs) throws SQLException {
        Professor e = new Professor();
        e.setId(rs.getLong("id"));
        return e;
    }
}