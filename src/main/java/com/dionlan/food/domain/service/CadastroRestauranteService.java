package com.dionlan.food.domain.service;

import com.dionlan.food.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.food.domain.model.Cozinha;
import com.dionlan.food.domain.model.Restaurante;
import com.dionlan.food.domain.repository.CozinhaRepository;
import com.dionlan.food.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante buscar(Long restauranteId){
        Restaurante restaurante = restauranteRepository.buscarPorId(restauranteId);
        if(isNull(restaurante)){
            throw new IllegalArgumentException(String.format("Não existe restaurante com código %d cadastrado", restauranteId));
        }
        return restaurante;
    }
}
