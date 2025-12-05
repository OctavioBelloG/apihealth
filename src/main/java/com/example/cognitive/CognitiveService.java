package com.example.cognitive;

import com.example.cognitive.SentimientResult;

public interface CognitiveService {

    String detectLanguage(String text);
    SentimientResult analyzeSentiment(String text);
    
}
