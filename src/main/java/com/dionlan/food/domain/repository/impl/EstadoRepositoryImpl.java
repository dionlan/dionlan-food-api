package com.dionlan.food.domain.repository.impl;

import com.dionlan.food.domain.model.Estado;
import com.dionlan.food.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Estado> listar(){
        return em.createQuery("from Estado").getResultList();
    }
}
