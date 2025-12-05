package com.example.cognitive;

import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CognitiveServiceImpl implements CognitiveService {

    private final TextAnalyticsClient client;

    public CognitiveServiceImpl(
            @Value("${azure.ai.key}") String key,
            @Value("${azure.ai.endpoint}") String endpoint) {

        this.client = new TextAnalyticsClientBuilder()
                .credential(new com.azure.core.credential.AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }

    @Override
    public String detectLanguage(String text) {
        // PISTA DE DEBUG: Verificamos en consola qué está llegando
        System.out.println("--- AZURE DETECTANDO IDIOMA ---");
        System.out.println("Texto recibido: '" + text + "'");

        DetectedLanguage lang = client.detectLanguage(text);
        
        // Devolvemos: "Nombre (Código)" -> Ej: "Spanish (es)"
        return lang.getName() + " (" + lang.getIso6391Name() + ")"; 
    }

    @Override
    public SentimientResult analyzeSentiment(String text) {
        DocumentSentiment result = client.analyzeSentiment(text);

        SentimientResult dto = new SentimientResult();
        dto.setSentiment(result.getSentiment().toString());

        SentimentConfidenceScores scores = result.getConfidenceScores();
        dto.setPositive(scores.getPositive());
        dto.setNeutral(scores.getNeutral());
        dto.setNegative(scores.getNegative());

        return dto;
    }
}