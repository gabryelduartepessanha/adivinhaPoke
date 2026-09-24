package org.example;

import org.example.Pokemon;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokeApiClient {

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";
    private final HttpClient client = HttpClient.newHttpClient();

    public Pokemon buscarPokemonAleatorio(int maxId) throws Exception {
        int randomId = (int) (Math.random() * maxId) + 1;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(POKEAPI_URL + randomId))
                .GET()
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao conectar com a PokéAPI. Código: " + response.statusCode());
        }

        return parseJsonToPokemon((String) response.body());
    }

    // Parser simples para extrair nome, tipos, altura, peso e habilidades sem precisar de Jackson/Gson
    private Pokemon parseJsonToPokemon(String json) {
        String name = extractString(json, "\"name\":\"([^\"]+)\"");
        double height = Double.parseDouble(extractString(json, "\"height\":(\\d+)")) / 10.0; // Converte decímetros para metros
        double weight = Double.parseDouble(extractString(json, "\"weight\":(\\d+)")) / 10.0; // Converte hectogramas para kg

        List types = extractList(json, "\"type\":\\{\"name\":\"([^\"]+)\"");
        List abilities = extractList(json, "\"ability\":\\{\"name\":\"([^\"]+)\"");

        return new Pokemon(name, types, height, weight, abilities);
    }

    private String extractString(String json, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(json);
        return matcher.find() ? matcher.group(1) : "Desconhecido";
    }

    private List extractList(String json, String regex) {
        List list = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(json);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }
}