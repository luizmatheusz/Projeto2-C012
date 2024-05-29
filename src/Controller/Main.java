package Controller;

import Models.PokemonIncubator;
import Models.Egg;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PokemonIncubator incubator = new PokemonIncubator();
        long totalWaitingTime = 0;

        // Vetor de ovos Pokémon (Threads)
        Egg[] eggs = new Egg[6];
        System.out.println("***************************");
        System.out.println("FCFS (First Come First Served) - Ordem de Chegada:");
        System.out.println("***************************");

        // Instancia o vetor
        for (int i = 1; i <= 6; i++) {
            eggs[i - 1] = new Egg(incubator);
            System.out.println(eggs[i-1].getPokemonName() + " - " + eggs[i-1].getStepsRequired() + " passos necessários");
        }

        // Inicia todas as threads
        for (int i = 1; i <= 6; i++) {
            eggs[i - 1].start();
        }

        // Aguarda a conclusão de cada thread
        for (int i = 1; i <= 6; i++) {
            try {
                // Aguarda a conclusão da thread atual antes de iniciar a próxima
                eggs[i - 1].join();
            } catch (InterruptedException e) {
                System.err.println("Thread " + i + " foi interrompida.");
            }
        }

        // Calcular e imprimir os tempos após todas as threads terminarem
        for (int i = 1; i <= 6; i++) {
            if(i == 1){
                System.out.println("Thread 1 - tempo de espera: 0 ms");
            }

            if(i != 1){
                long waitingTime = eggs[i - 2].getWaitingTime();
                totalWaitingTime += waitingTime;
                System.out.println("Thread " + i + " - tempo de espera: " + totalWaitingTime + " ms");
            }

            long spentTime = eggs[i - 1].getWaitingTime();
            System.out.println("Thread " + i + " - tempo gasto: " + spentTime + " ms");
            System.out.println("***************************\n");
        }

        // Mostra o tempo médio de espera do FCFS
        double averageWaitingTimeFCFS = totalWaitingTime / (double) eggs.length;
        System.out.println("Tempo médio de espera: " + Math.round(averageWaitingTimeFCFS) + " ms");

        // Reinicia a execução para o SJF
        totalWaitingTime = 0;
        Egg.restartSeed();
        eggs = new Egg[6];
        System.out.println("\n\n***************************");
        System.out.println("SJF (Shortest Job First) - Menor número de passos primeiro:");
        System.out.println("***************************");

        // Instancia o vetor novamente
        for (int i = 1; i <= 6; i++) {
            eggs[i - 1] = new Egg(incubator);
            System.out.println(eggs[i-1].getPokemonName() + " - " + eggs[i-1].getStepsRequired() + " passos necessários");
        }

        // Ordena o vetor pelos passos necessários
        Arrays.sort(eggs, (o1, o2) -> Integer.compare(o1.getStepsRequired(), o2.getStepsRequired()));

        // Mostra as informações das threads
        for (int i = 1; i <= 6; i++) {
            System.out.println(eggs[i-1].getPokemonName() + " - " + eggs[i-1].getStepsRequired() + " passos necessários");
        }

        // Inicia todas as threads
        for (int i = 1; i <= 6; i++) {
            eggs[i - 1].start();
        }

        // Aguarda a conclusão de cada thread
        for (int i = 1; i <= 6; i++) {
            try {
                // Aguarda a conclusão da thread atual antes de iniciar a próxima
                eggs[i - 1].join();
            } catch (InterruptedException e) {
                System.err.println("Thread " + i + " foi interrompida.");
            }
        }

        // Calcular e imprimir os tempos após todas as threads terminarem
        for (int i = 1; i <= 6; i++) {
            if(i == 1){
                System.out.println("Thread 1 - tempo de espera: 0 ms");
            }

            if(i != 1){
                long waitingTime = eggs[i - 2].getWaitingTime();
                totalWaitingTime += waitingTime;
                System.out.println("Thread " + i + " - tempo de espera: " + totalWaitingTime + " ms");
            }

            long spentTime = eggs[i - 1].getWaitingTime();
            System.out.println("Thread " + i + " - tempo gasto: " + spentTime + " ms");
            System.out.println("***************************\n");
        }

        // Mostra o tempo médio de espera do SJF
        double averageWaitingTimeSJF = totalWaitingTime / (double) eggs.length;
        System.out.println("Tempo médio de espera: " + Math.round(averageWaitingTimeSJF) + " ms");

        // Mostra o comparativo de tempos de espera entre FCFS e SJF
        System.out.println("\n\n***************************");
        System.out.println("Tempo médio de espera FCFS: " + Math.round(averageWaitingTimeFCFS) + " ms");
        System.out.println("Tempo médio de espera SJF:  " + Math.round(averageWaitingTimeSJF) + " ms");
        System.out.println("***************************");
    }
}
