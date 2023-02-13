package com.dionlan.food.domain.repository.impl;

import com.dionlan.food.domain.model.Restaurante;
import com.dionlan.food.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Restaurante> listar() {
        return em.createQuery("from Restaurante").getResultList();
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        return em.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return em.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        em.remove(buscarPorId(restaurante.getId()));
    }
}
