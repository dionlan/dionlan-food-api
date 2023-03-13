package com.dionlan.food.domain.service;

import com.dionlan.food.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.food.domain.model.Estado;
import com.dionlan.food.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.Objects.isNull;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado adicionar(Estado estado){
        return estadoRepository.salvar(estado);
    }

    public List<Estado> listar(){
        return estadoRepository.listar();
    }

    public Estado buscarPorId(Long estadoId){
        Estado estadoAtual = estadoRepository.buscarPorId(estadoId);
        if(isNull(estadoAtual)){
            throw new EntidadeNaoEncontradaException();
        }
        return estadoAtual;
    }

    public void excluir(Long estadoId){
        estadoRepository.excluir(estadoId);
    }
}
