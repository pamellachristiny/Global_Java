package br.com.fiap.global.service;

import br.com.fiap.global.dao.CursoDAO;
import br.com.fiap.global.model.Curso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CursoService {

    @Inject
    CursoDAO cursoDAO;

    public List<Curso> findAll() {
        return cursoDAO.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoDAO.findById(id);
    }

    public Curso create(Curso curso) {
        return cursoDAO.create(curso);
    }

    public Optional<Curso> update(Long id, Curso updatedDetails) {
        updatedDetails.setId(id);
        return cursoDAO.update(updatedDetails);
    }

    public boolean delete(Long id) {
        return cursoDAO.deleteById(id);
    }
}