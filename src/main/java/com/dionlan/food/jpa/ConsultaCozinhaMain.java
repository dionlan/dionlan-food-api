package com.dionlan.food.jpa;

import com.dionlan.food.DionlanFoodApiApplication;
import com.dionlan.food.domain.model.Cozinha;
import com.dionlan.food.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DionlanFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");

        //cadastroCozinha.salvar(cozinha);
        /*List<Cozinha> cozinhas = cadastroCozinha.lista();
        for(Cozinha cozinha : cozinhas){
            System.out.println(cozinha.getNome());
        }*/
    }
}
