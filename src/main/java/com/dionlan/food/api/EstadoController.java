package com.dionlan.food.api;

import com.dionlan.food.domain.model.Estado;
import com.dionlan.food.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Estado> listar() {
        return estadoRepository.listar();
    }
}
