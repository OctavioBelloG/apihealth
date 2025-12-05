package com.example.graphql;

import com.example.service.LanguajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping; // O @MutationMapping si modifican datos
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;

@Controller // <--- Esto es para GraphQL
public class LanguageGraphqlController {

    @Autowired
    private LanguajeService languageService;

    @QueryMapping // Asegúrate que en tu schema.graphqls se llamen igual
    public String detectLanguage(@Argument String text) {
        return languageService.detectLanguage(text);
    }

    @QueryMapping
    public String analyzeSentiment(@Argument String text) {
        return languageService.analyzeSentiment(text);
    }
}