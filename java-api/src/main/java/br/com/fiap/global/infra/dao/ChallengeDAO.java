
package br.com.fiap.global.dao;

import br.com.fiap.global.model.Challenge;
import br.com.fiap.global.infra.Connectionfactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ChallengeDAO {

    public List<Challenge> findAll() {
        String sql = "SELECT id, nome_challenge, descricao_challenge, tempo, curso_id FROM TB_CHALLENGE";
        List<Challenge> list = new ArrayList<>();
        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Challenge c = mapRow(rs);
                list.add(c);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public Optional<Challenge> findById(Long id) {
        String sql = "SELECT id, nome_challenge, descricao_challenge, tempo, curso_id FROM TB_CHALLENGE WHERE id = ?";
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
    public Challenge create(Challenge challenge) {
        String sql = "INSERT INTO TB_CHALLENGE (nome_challenge, descricao_challenge, tempo, curso_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, challenge.getNomeChallenge());
            ps.setString(2, challenge.getDescricaoChallenge());
            if (challenge.getTempo() != null) {
                ps.setDate(3, Date.valueOf(challenge.getTempo()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            if (challenge.getCurso() != null && challenge.getCurso().getId() != null) {
                ps.setLong(4, challenge.getCurso().getId());
            } else {
                ps.setNull(4, Types.BIGINT);
            }
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    challenge.setId(keys.getLong(1));
                }
            }
            return challenge;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Transactional
    public Optional<Challenge> update(Challenge challenge) {
        String sql = "UPDATE TB_CHALLENGE SET nome_challenge = ?, descricao_challenge = ?, tempo = ?, curso_id = ? WHERE id = ?";
        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, challenge.getNomeChallenge());
            ps.setString(2, challenge.getDescricaoChallenge());
            if (challenge.getTempo() != null) {
                ps.setDate(3, Date.valueOf(challenge.getTempo()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            if (challenge.getCurso() != null && challenge.getCurso().getId() != null) {
                ps.setLong(4, challenge.getCurso().getId());
            } else {
                ps.setNull(4, Types.BIGINT);
            }
            ps.setLong(5, challenge.getId());
            int updated = ps.executeUpdate();
            if (updated > 0) {
                return Optional.of(challenge);
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Transactional
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM TB_CHALLENGE WHERE id = ?";
        try (Connection conn = Connectionfactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int deleted = ps.executeUpdate();
            return deleted > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Challenge mapRow(ResultSet rs) throws SQLException {
        Challenge c = new Challenge();
        c.setId(rs.getLong("id"));
        c.setNomeChallenge(rs.getString("nome_challenge"));
        c.setDescricaoChallenge(rs.getString("descricao_challenge"));
        Date d = rs.getDate("tempo");
        if (d != null) c.setTempo(d.toLocalDate());
        long cursoId = rs.getLong("curso_id");
        if (!rs.wasNull()) {
            br.com.fiap.global.model.Curso curso = new br.com.fiap.global.model.Curso();
            curso.setId(cursoId);
            c.setCurso(curso);
        }
        return c;
    }
}
