package com.dionlan.food.api;

import com.dionlan.food.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.food.domain.model.Estado;
import com.dionlan.food.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscarPorId(@PathVariable Long estadoId){
        return estadoService.buscarPorId(estadoId);
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
        Estado estadoAtual = estadoService.adicionar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoAtual);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        try{
            Estado estadoAtual = estadoService.buscarPorId(estadoId);
            if(nonNull(estadoAtual)){
                copyProperties(estado, estadoAtual, "id");
                estadoAtual = estadoService.adicionar(estadoAtual);
                return ResponseEntity.ok(estadoAtual);
            }
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public void excluir(@PathVariable Long estadoId){
        estadoService.excluir(estadoId);
    }
}
