import org.example.PokeApiClient;
import org.example.Pokemon;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PokeApiClient client = new PokeApiClient();

        final int MAX_TENTATIVAS = 3;
        final int MAX_POKEMON_GEN1 = 151; // Limite até a 1ª geração para facilitar

        System.out.println("==========================================");
        System.out.println("  BEM-VINDO AO QUEM É ESSE POKÉMON? (CLI) ");
        System.out.println("==========================================");
        System.out.println("Carregando um Pokémon aleatório...");

        try {
            Pokemon pokemon = client.buscarPokemonAleatorio(MAX_POKEMON_GEN1);
            pokemon.exibirDicas();

            int tentativas = 0;
            boolean acertou = false;

            while (tentativas < MAX_TENTATIVAS && !acertou) {
                System.out.print("\nQual é o seu palpite? (Tentativa " + (tentativas + 1) + "/" + MAX_TENTATIVAS + "): ");
                String palpite = scanner.nextLine().trim().toLowerCase();

                if (palpite.equals(pokemon.getName())) {
                    acertou = true;
                    System.out.println("\n🎉 PARABÉNS! Você acertou! O Pokémon era: " + pokemon.getName().toUpperCase());
                } else {
                    tentativas++;
                    if (tentativas < MAX_TENTATIVAS) {
                        System.out.println("❌ Errado! Tente novamente.");
                    }
                }
            }

            if (!acertou) {
                System.out.println("\n☠️ Você perdeu! O Pokémon correto era: " + pokemon.getName().toUpperCase());
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao rodar o jogo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}