package com.dionlan.food.domain.service;

import com.dionlan.food.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.food.domain.model.Estado;
import com.dionlan.food.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;
    public Estado adicionar(Estado estado){
        return estadoRepository.save(estado);
    }
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }
    public Estado buscarPorId(Long estadoId){
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException());
    }
    public void excluir(Long estadoId){
        estadoRepository.deleteById(estadoId);
    }
}
