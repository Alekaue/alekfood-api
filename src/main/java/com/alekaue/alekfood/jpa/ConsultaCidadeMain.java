package com.alekaue.alekfood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.alekaue.alekfood.AlekfoodApiApplication;
import com.alekaue.alekfood.domain.model.Cidade;
import com.alekaue.alekfood.domain.repository.CidadeRepository;

public class ConsultaCidadeMain {

        public static void main(String[] args) {
            ApplicationContext applicationContext = new SpringApplicationBuilder(AlekfoodApiApplication.class)
                    .web(WebApplicationType.NONE)
                    .run(args);
            
            CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
            
            List<Cidade> todasCidades = cidadeRepository.todas();
            
            for (Cidade cidade : todasCidades) {
                System.out.printf("%s - %s\n", cidade.getNome(), cidade.getEstado().getNome());
            }
        }
        
    }