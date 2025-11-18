package br.com.fiap.global.service;

import br.com.fiap.global.dao.ChallengeDAO;
import br.com.fiap.global.model.Challenge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ChallengeService {

    @Inject
    ChallengeDAO challengeDAO;

    public List<Challenge> findAll() {
        return challengeDAO.findAll();
    }

    public Optional<Challenge> findById(Long id) {
        return challengeDAO.findById(id);
    }

    public Challenge create(Challenge challenge) {
        // Lógica de negócio (ex: verificar data, validar nome)
        return challengeDAO.create(challenge);
    }

    public Optional<Challenge> update(Long id, Challenge updatedDetails) {
        // Garante que o ID do objeto seja usado para a atualização
        updatedDetails.setId(id);
        return challengeDAO.update(updatedDetails);
    }

    public boolean delete(Long id) {
        return challengeDAO.deleteById(id);
    }
}