package com.dionlan.food.api;

import com.dionlan.food.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.food.domain.model.Restaurante;
import com.dionlan.food.domain.repository.CozinhaRepository;
import com.dionlan.food.domain.repository.RestauranteRepository;
import com.dionlan.food.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Restaurante restaurante = restauranteRepository.buscarPorId(restauranteId);

        if(nonNull(restaurante)){
           return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try{
            restaurante = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurate){
        try{
            Restaurante restauranteAtual = cadastroRestauranteService.buscar(restauranteId);
            if(nonNull(restauranteId)){
                copyProperties(restaurate, restauranteAtual, "id");
                restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
