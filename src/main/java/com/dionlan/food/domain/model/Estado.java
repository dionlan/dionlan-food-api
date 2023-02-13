package com.dionlan.food.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}
