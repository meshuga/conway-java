package com.github.meshuga;

public class ConwaysWorldRules {

    public boolean shouldBeAlive(int neighboursStateCount, boolean currentlyExist) {
        if (currentlyExist) {
            return !(neighboursStateCount < 2 || neighboursStateCount > 3);
        } else {
            return neighboursStateCount == 3;
        }
    }

    public int countNeighbours(Generation generation, int i, int j) {
        int existingNeighboursCount = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x == i && y == j) {
                    continue;
                }
                if (isCellAliveWithShifting(generation, x, y)) {
                    existingNeighboursCount++;
                }
            }
        }
        return existingNeighboursCount;
    }

    private boolean isCellAliveWithShifting(Generation generation, int x, int y) {
        if (x < 0) {
            x = generation.getSize() - 1;
        } else if (x == generation.getSize()) {
            x = 0;
        }

        if (y < 0) {
            y = generation.getSize() - 1;
        } else if (y == generation.getSize()) {
            y = 0;
        }
        return generation.isCellAlive(x, y);
    }
}
