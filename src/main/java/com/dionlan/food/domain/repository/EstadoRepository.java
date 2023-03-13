package com.dionlan.food.domain.repository;

import com.dionlan.food.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();

    Estado buscarPorId(Long estadoId);

    Estado salvar(Estado estado);

    void excluir(Long estadoId);
}
