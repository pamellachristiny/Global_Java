package br.com.fiap.global.service;

import br.com.fiap.global.dao.UserDAO;
import br.com.fiap.global.model.User;
// IMPORTS DE EXCEÇÕES CORRIGIDOS
import br.com.fiap.global.exception.RecursoNaoEncontradoException;
import br.com.fiap.global.exception.RequisicaoInvalidaException;
import jakarta.enterprise.context.ApplicationScoped;
// ANOTAÇÃO @Inject Adicionada
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject // <--- VOCÊ PRECISA DESTA ANOTAÇÃO AQUI
    UserDAO userDAO;

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User buscarPorIdOuFalhar(Long id) {
        return userDAO.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado para o ID: " + id));
    }

    public User create(User user) {
        // Lógica de validação simples antes de criar
        if (user.getNome() == null || user.getNome().trim().isEmpty()) {
            throw new RequisicaoInvalidaException("O nome do usuário é obrigatório.");
        }
        return userDAO.create(user);
    }

    public User update(Long id, User updatedDetails) {
        // 1. Garante que o usuário existe antes de tentar atualizar (ou lança 404)
        buscarPorIdOuFalhar(id);

        // 2. Lógica de validação (lança 400 se for inválido)
        if (updatedDetails.getNome() == null || updatedDetails.getNome().trim().isEmpty()) {
            throw new RequisicaoInvalidaException("O nome do usuário não pode ser vazio.");
        }

        // 3. Atualiza. Já que userDAO.update usa findById(id) e depois atualiza o objeto rastreado.
        updatedDetails.setId(id);

        return userDAO.update(updatedDetails)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Falha na atualização para o ID: " + id));
    }

    public boolean delete(Long id) {
        // Verifica se o usuário existe antes de deletar, para retornar um 404 se não existir.
        // Se preferir, pode apenas chamar userDAO.deleteById(id) e deixar o caller lidar com o resultado.
        // Vamos manter a lógica simples, pois o DAO já tem o deleteById.
        return userDAO.deleteById(id);
    }
}