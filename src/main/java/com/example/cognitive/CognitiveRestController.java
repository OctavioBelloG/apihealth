package com.example.cognitive;

import com.example.service.LanguajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // <--- Esto es lo que busca Swagger
@RequestMapping("/api/cognitive") // Prefijo para tu API REST
@Tag(name = "Servicios Cognitivos", description = "Detección de idioma y análisis de sentimientos")
public class CognitiveRestController {

    @Autowired
    private LanguajeService languageService;

    @Operation(summary = "Detectar Idioma", description = "Devuelve el código del idioma detectado")
    @PostMapping("/detect-language")
    public ResponseEntity<String> detectLanguage(@RequestBody String text) {
        return ResponseEntity.ok(languageService.detectLanguage(text));
    }

    @Operation(summary = "Analizar Sentimiento", description = "Devuelve el análisis de sentimiento del texto")
    @PostMapping("/analyze-sentiment")
    public ResponseEntity<String> analyzeSentiment(@RequestBody String text) {
        return ResponseEntity.ok(languageService.analyzeSentiment(text));
    }
}