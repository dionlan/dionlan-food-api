package com.dionlan.food.api;

import com.dionlan.food.domain.exception.EntidadeEmUsoException;
import com.dionlan.food.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.food.domain.model.Cozinha;
import com.dionlan.food.domain.repository.CozinhaRepository;
import com.dionlan.food.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        if(cozinha.isPresent()){
            return ResponseEntity.ok(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void adicionar(@RequestBody Cozinha cozinha){
        cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
        if(cozinhaAtual.isPresent()){
            copyProperties(cozinha, cozinhaAtual.get(), "id");
            Cozinha conzinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(conzinhaSalva);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> remover(@PathVariable Long cozinhaId){
        try {
            cadastroCozinhaService.excluir(cozinhaId);
            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.noContent().build();

        } catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
