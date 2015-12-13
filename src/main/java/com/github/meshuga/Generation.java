package com.github.meshuga;

public class Generation {
    private final LifeIndicator[][] worldMap;
    private final int size;

    public Generation(LifeIndicator[][] worldMap, int size) {
        this.worldMap = worldMap;
        this.size = size;
    }

    public boolean isCellAlive(int x, int y) {
        return worldMap[x][y] == LifeIndicator.ALIVE;
    }

    public int getSize() {
        return size;
    }

    public void printWorld() {
        for (LifeIndicator[] aMap : this.worldMap) {
            printRow(aMap);
        }
        System.out.println();
    }

    private void printRow(LifeIndicator[] arr) {
        for (LifeIndicator anArr : arr) {
            System.out.print(anArr.getCellMark());
        }
        System.out.println();
    }
}
