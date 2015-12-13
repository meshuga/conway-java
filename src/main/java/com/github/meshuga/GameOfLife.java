package com.github.meshuga;

import java.io.IOException;

public class GameOfLife {
    private static final int SLEEP_TIME = 200;

    private final GenerationBuilder generationBuilder;

    private Generation currentGeneration;

    public GameOfLife(GenerationBuilder generationBuilder, Generation currentGeneration) throws IOException {
        this.generationBuilder = generationBuilder;
        this.currentGeneration = currentGeneration;
    }

    public void startGame() throws InterruptedException, IOException {
        currentGeneration.printWorld();

        while (true) {
            currentGeneration = generationBuilder.buildNewGeneration(currentGeneration);
            currentGeneration.printWorld();
            Thread.sleep(SLEEP_TIME);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new RuntimeException("The first argument must be initial file name and the second world size.");
        }
        String mapFileName = args[0];
        int populationSize = Integer.parseInt(args[1]);

        GenerationBuilder generationBuilder = new GenerationBuilder(new ConwaysWorldRules());

        Generation initialGeneration = generationBuilder.buildInitial(mapFileName, populationSize);
        GameOfLife now = new GameOfLife(generationBuilder, initialGeneration);
        now.startGame();
    }
}
