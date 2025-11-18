package br.com.fiap.global.dao;

import br.com.fiap.global.model.User;
import br.com.fiap.global.infra.Connectionfactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDAO {

    public List<User> findAll() {
        String sql = "SELECT * FROM TB_USER";
        List<User> list = new ArrayList<>();

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User e = mapRow(rs);
                list.add(e);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return list;
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM TB_USER WHERE id = ?";

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
    public User create(User obj) {
        throw new UnsupportedOperationException("Implement create for User");
    }

    @Transactional
    public Optional<User> update(User obj) {
        throw new UnsupportedOperationException("Implement update for User");
    }

    @Transactional
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM TB_USER WHERE id = ?";

        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int deleted = ps.executeUpdate();
            return deleted > 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User e = new User();
        e.setId(rs.getLong("id"));
        return e;
    }
}