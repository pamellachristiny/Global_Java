package br.com.fiap.global.dao;

import br.com.fiap.global.model.Curso;
import br.com.fiap.global.infra.Connectionfactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CursoDAO {

    public List<Curso> findAll() {
        String sql = "SELECT * FROM TB_CURSO";
        List<Curso> list = new ArrayList<>();

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso e = mapRow(rs);
                list.add(e);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public Optional<Curso> findById(Long id) {

        String sql = "SELECT * FROM TB_CURSO WHERE id = ?";

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
    public Curso create(Curso obj) {
        throw new UnsupportedOperationException("Implement create for Curso");
    }

    @Transactional
    public Optional<Curso> update(Curso obj) {
        throw new UnsupportedOperationException("Implement update for Curso");
    }

    @Transactional
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM TB_CURSO WHERE id = ?";

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int deleted = ps.executeUpdate();
            return deleted > 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Curso mapRow(ResultSet rs) throws SQLException {
        Curso e = new Curso();
        e.setId(rs.getLong("id"));
        return e;
    }
}