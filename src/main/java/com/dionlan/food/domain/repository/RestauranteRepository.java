package com.dionlan.food.domain.repository;

import com.dionlan.food.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
    List<Restaurante> findTop2ByNomeContaining(String nome);

}
