package br.com.fiap.global.service;

import br.com.fiap.global.dao.ProfessorDAO;
import br.com.fiap.global.model.Professor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfessorService {

    @Inject
    ProfessorDAO professorDAO;

    public List<Professor> findAll() {
        return professorDAO.findAll();
    }

    public Optional<Professor> findById(Long id) {
        return professorDAO.findById(id);
    }

    public Professor create(Professor professor) {
        // Lógica de negócio, ex: validação de email
        return professorDAO.create(professor);
    }

    public Optional<Professor> update(Long id, Professor updatedDetails) {
        updatedDetails.setId(id);
        return professorDAO.update(updatedDetails);
    }

    public boolean delete(Long id) {
        return professorDAO.deleteById(id);
    }
}