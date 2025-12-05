package com.example.service;

import com.azure.ai.textanalytics.*;
// import com.azure.ai.textanalytics.implementation.models.DetectedLanguage;
// import com.azure.ai.textanalytics.implementation.models.DocumentSentiment;
import com.azure.ai.textanalytics.models.DetectedLanguage;
import com.azure.ai.textanalytics.models.DocumentSentiment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguajeService {
    
    @Autowired
    private TextAnalyticsClient client;

    public String detectLanguage(String text) {
        DetectedLanguage lang = client.detectLanguage(text);
        return lang.getName();
    }

    public String analyzeSentiment(String text) {
        DocumentSentiment sentiment = client.analyzeSentiment(text);
        return sentiment.getSentiment().toString();
    }

}
