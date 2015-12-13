package com.github.meshuga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GenerationBuilder {
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private final ConwaysWorldRules worldRules;

    public GenerationBuilder(ConwaysWorldRules worldRules) {
        this.worldRules = worldRules;
    }

    public Generation buildInitial(String mapFileName, int populationSize) throws Exception {
        Path path = Paths.get(mapFileName);
        List<String> lines = Files.readAllLines(path, ENCODING);
        LifeIndicator[][] map = new LifeIndicator[populationSize][populationSize];

        for (int i = 0; i < map.length; i++) {
            String line = lines.get(i);
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = LifeIndicator.getLifeIndicator(line.charAt(j));
        }

        return new Generation(map, populationSize);
    }

    public Generation buildNewGeneration(Generation currentGeneration) {
        int size = currentGeneration.getSize();
        LifeIndicator[][] newWorldMap = new LifeIndicator[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = worldRules.countNeighbours(currentGeneration, i, j);
                boolean currentlyExist = currentGeneration.isCellAlive(i, j);

                if (worldRules.shouldBeAlive(count, currentlyExist)) {
                    newWorldMap[i][j] = LifeIndicator.ALIVE;
                } else {
                    newWorldMap[i][j] = LifeIndicator.DEAD;
                }
            }
        }

        return new Generation(newWorldMap, size);
    }
}
