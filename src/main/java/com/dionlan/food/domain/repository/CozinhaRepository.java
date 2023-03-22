package com.dionlan.food.domain.repository;

import com.dionlan.food.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    List<Cozinha> findByNomeContaining(String nome);
    boolean existsByNome(String nome);
    int countById(Long cozinhaId);
}
