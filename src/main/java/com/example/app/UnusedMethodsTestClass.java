package com.example.app;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Classe créée spécifiquement pour tester la détection de méthodes inutilisées
 * Aucune de ces méthodes n'est appelée depuis d'autres parties du code
 */
public class UnusedMethodsTestClass {

    private String unusedField = "Cette variable n'est jamais utilisée";
    private static final int UNUSED_CONSTANT = 42;

    /**
     * Méthode privée qui n'est jamais appelée
     */
    private void privateUnusedMethod() {
        System.out.println("Cette méthode privée n'est jamais appelée");
    }

    /**
     * Méthode publique qui n'est jamais utilisée
     */
    public String publicUnusedMethod() {
        return "Cette méthode publique n'est jamais appelée";
    }

    /**
     * Méthode protected inutilisée
     */
    protected void protectedUnusedMethod(int param) {
        int result = param * 2;
        System.out.println("Résultat: " + result);
    }

    /**
     * Méthode statique inutilisée
     */
    public static void staticUnusedMethod() {
        System.out.println("Cette méthode statique n'est jamais appelée");
    }

    /**
     * Méthode avec paramètres complexes, inutilisée
     */
    public Map<String, List<Integer>> complexUnusedMethod(List<String> input, boolean flag) {
        Map<String, List<Integer>> result = new HashMap<>();
        
        if (flag) {
            for (String item : input) {
                List<Integer> numbers = new ArrayList<>();
                numbers.add(item.length());
                numbers.add(item.hashCode());
                result.put(item, numbers);
            }
        }
        
        return result;
    }

    /**
     * Méthode qui retourne toujours null, inutilisée
     */
    public Object alwaysReturnsNull() {
        return null;
    }

    /**
     * Méthode avec logique métier inutilisée
     */
    private double calculateSomethingUseless(double x, double y) {
        double result = Math.pow(x, 2) + Math.pow(y, 2);
        result = Math.sqrt(result);
        
        if (result > 100) {
            result = result / 2;
        }
        
        return result;
    }

    /**
     * Méthode qui lève une exception, inutilisée
     */
    public void methodThatThrowsException() throws Exception {
        throw new Exception("Cette exception ne sera jamais lancée car la méthode n'est pas appelée");
    }

    /**
     * Méthode de validation inutilisée
     */
    private boolean validateSomething(String input) {
        return input != null && !input.trim().isEmpty() && input.length() > 3;
    }

    /**
     * Constructeur avec paramètre, probablement inutilisé
     */
    public UnusedMethodsTestClass(String unusedParameter) {
        this.unusedField = unusedParameter;
    }

    /**
     * Constructeur par défaut
     */
    public UnusedMethodsTestClass() {
        // Constructeur vide
    }

    /**
     * Getter pour le champ inutilisé
     */
    public String getUnusedField() {
        return unusedField;
    }

    /**
     * Setter pour le champ inutilisé
     */
    public void setUnusedField(String unusedField) {
        this.unusedField = unusedField;
    }
}