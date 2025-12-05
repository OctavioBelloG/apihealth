package com.example.cognitive;

import lombok.Data;

@Data
public class SentimientResult {
    
    private String sentiment;
    private double positive;
    private double neutral;
    private double negative;

}
