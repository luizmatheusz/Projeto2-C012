package Models;

import java.util.Random;

public class PokemonIncubator {
    // Códigos de cor ANSI
    private static final Random rand = new Random(28);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Função incubate
    // Funciona como um monitor, apenas uma thread executando
    public synchronized long[] incubate(Egg pokemon) {
        long[] times = new long[2];
        int stepsRequired = pokemon.getStepsRequired();
        int stepsTaken = 0;
        String color = getColor();

        // Inicia a incubação
        System.out.println(color + "Um ovo está sendo incubado. Passos necessários: " + stepsRequired);
        times[0] = System.currentTimeMillis();

        // Simula o processo de incubação, contando o número de passos dados
        while (stepsTaken < stepsRequired) {
            try {
                Thread.sleep(300); // Cada passo leva 0.3 segundo
            } catch (InterruptedException e) {
                System.err.println("Incubação interrompida para " + pokemon.getPokemonName());
            }
            stepsTaken++;
            System.out.println("Passos totais: " + stepsTaken);
        }

        // Fim da incubação
        times[1] = System.currentTimeMillis();
        System.out.println(color + pokemon.getPokemonName() + " foi chocado!");
        System.out.println("");

        // Retorna tempo inicial e final
        return times;
    }

    // Função auxiliar de Print
    private String getColor() {
        String[] colors = { ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE };
        return colors[rand.nextInt(colors.length)];
    }
}