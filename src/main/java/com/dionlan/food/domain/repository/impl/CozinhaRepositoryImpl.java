package com.dionlan.food.domain.repository.impl;

import com.dionlan.food.domain.model.Cozinha;
import com.dionlan.food.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cozinha> listar() {
        return em.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return em.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return em.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        em.remove(cozinha);
    }
}
