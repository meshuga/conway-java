package com.github.meshuga;

public enum LifeIndicator {
    ALIVE('*'), DEAD('_');

    private static final char ALIVE_CELL_MARK = '*';
    private static final char DEAD_CELL_MARK = '_';

    private char cellMark;

    LifeIndicator(char cellMark) {
        this.cellMark = cellMark;
    }

    public char getCellMark() {
        return cellMark;
    }

    public static LifeIndicator getLifeIndicator(char cellValue) {
        switch (cellValue) {
            case LifeIndicator.ALIVE_CELL_MARK:
                return LifeIndicator.ALIVE;
            case LifeIndicator.DEAD_CELL_MARK:
                return LifeIndicator.DEAD;
            default:
                throw new RuntimeException("Unknown cell value: " + cellValue);
        }
    }
}
