package Models;

import java.util.Random;

public class Egg extends Thread {
    private PokemonIncubator incubator;
    private String name;
    private int stepsRequired;
    private long startTime;
    private long endTime;
    private long waitingTime;
    private static Random rand = new Random(28);

    // Construtor
    // Gera um Pokémon e um número de passos necessários aleatórios
    public Egg(PokemonIncubator incubator) {
        this.incubator = incubator;
        String[] pokemonNames = {"Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise", "Caterpie", "Metapod", "Butterfree", "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot", "Rattata", "Raticate", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu", "Sandshrew", "Sandslash", "Nidoran♀", "Nidorina", "Nidoqueen", "Nidoran♂", "Nidorino", "Nidoking", "Clefairy", "Clefable", "Vulpix", "Ninetales", "Jigglypuff", "Wigglytuff"
        };
        this.name = pokemonNames[rand.nextInt(pokemonNames.length)];
        this.stepsRequired = rand.nextInt(15) + 5;
    }

    @Override
    public void run() {
        long[] times = this.incubator.incubate(this); // Função de incubar o ovo
        startTime = times[0]; // recebe o tempo inicial da função incubate
        endTime = times[1]; // recebe o tempo final da função incubate
        waitingTime = endTime - startTime; // calcula o tempo gasto
    }

    // Getters
    public long getWaitingTime() {
        return waitingTime;
    }

    public String getPokemonName() {
        return name;
    }

    public int getStepsRequired() {
        return stepsRequired;
    }

    public static void restartSeed(){
        rand = new Random(28);
    }
}
