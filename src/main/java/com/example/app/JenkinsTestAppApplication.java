package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.ArrayList;

@SpringBootApplication
public class JenkinsTestAppApplication {

    // Variables non utilisées
    private static final String UNUSED_CONSTANT = "Cette variable n'est jamais utilisée";
    private static int unusedCounter = 0;
    private String unusedField;

    public static void main(String[] args) {
        SpringApplication.run(JenkinsTestAppApplication.class, args);
        
        // Code dupliqué intentionnel
        int a = 5;
        int b = 5; // Duplication
        int c = 5; // Encore une duplication
        
        // Variables non utilisées
        String unusedString = "Je ne sers à rien";
        List<String> unusedList = new ArrayList<>();
        boolean unusedFlag = true;
        
        // Code inutile
        doNothingUseful();
        calculateUselessValue();
        
        // Boucle inutile
        for(int i = 0; i < 10; i++) {
            // Ne fait rien d'utile
            String temp = "iteration " + i;
        }
    }
    
    // Méthode inutile #1
    private static void doNothingUseful() {
        System.out.println("Cette méthode ne fait rien d'utile");
        int x = 10;
        int y = 20;
        int z = x + y; // Résultat non utilisé
    }
    
    // Méthode inutile #2 - Duplication de logique
    private static int calculateUselessValue() {
        int result = 0;
        for(int i = 1; i <= 5; i++) {
            result += i;
        }
        return result; // Valeur de retour jamais utilisée
    }
    
    // Méthode inutile #3 - Encore de la duplication
    private static int anotherUselessCalculation() {
        int total = 0;
        for(int j = 1; j <= 5; j++) {
            total += j; // Même logique que calculateUselessValue
        }
        return total;
    }
    
    // Méthode jamais appelée
    public void neverCalled() {
        String message = "Cette méthode n'est jamais appelée";
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        // La liste n'est jamais utilisée après ça
    }
    
    // Getters/Setters inutiles
    public String getUnusedField() {
        return unusedField;
    }
    
    public void setUnusedField(String unusedField) {
        this.unusedField = unusedField;
    }
}