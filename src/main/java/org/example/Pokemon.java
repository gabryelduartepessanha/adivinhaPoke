package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemon {
    private String name;
    private List types;
    private double height;
    private double weight;
    private List abilities;

    // Dicionário estático para traduzir os tipos de inglês para português
    private static final Map TRADUCAO_TIPOS = new HashMap<>();

    static {
        TRADUCAO_TIPOS.put("normal", "Normal");
        TRADUCAO_TIPOS.put("fire", "Fogo");
        TRADUCAO_TIPOS.put("water", "Água");
        TRADUCAO_TIPOS.put("grass", "Planta");
        TRADUCAO_TIPOS.put("electric", "Elétrico");
        TRADUCAO_TIPOS.put("ice", "Gelo");
        TRADUCAO_TIPOS.put("fighting", "Lutador");
        TRADUCAO_TIPOS.put("poison", "Venenoso");
        TRADUCAO_TIPOS.put("ground", "Terrestre");
        TRADUCAO_TIPOS.put("flying", "Voador");
        TRADUCAO_TIPOS.put("psychic", "Psíquico");
        TRADUCAO_TIPOS.put("bug", "Inseto");
        TRADUCAO_TIPOS.put("rock", "Pedra");
        TRADUCAO_TIPOS.put("ghost", "Fantasma");
        TRADUCAO_TIPOS.put("dragon", "Dragão");
        TRADUCAO_TIPOS.put("steel", "Aço");
        TRADUCAO_TIPOS.put("fairy", "Fada");
        TRADUCAO_TIPOS.put("dark", "Sombrio");
    }

    public Pokemon(String name, List types, double height, double weight, List abilities) {
        this.name = name.toLowerCase();
        this.types = traduzirTipos(types);
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
    }

    private List traduzirTipos(List tiposIngles) {
        List tiposPt = new ArrayList<>();
        for (Object tipo : tiposIngles) {
            tiposPt.add(TRADUCAO_TIPOS.getOrDefault(tipo.toLowerCase(), tipo));
        }
        return tiposPt;
    }

    public String getName() {
        return name;
    }

    public void exibirDicas() {
        System.out.println("\n========== DICAS DO POKÉMON ==========");
        System.out.println("Tipo(s): " + String.join(", ", types));
        System.out.println("Altura: " + height + " m");
        System.out.println("Peso: " + weight + " kg");
        System.out.println("Habilidade(s): " + String.join(", ", abilities));
        System.out.println("======================================");
    }
}