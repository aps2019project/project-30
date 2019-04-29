package com.company.Models.Battle.Map;

public class Map {
    private Cell[] cells = new Cell[5 * 9];

    public Cell[] getCells() {
        return cells;
    }

    public Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell();
                cell.setxCoordinate(i);
                cell.setyCoordinate(j);
                cells[i] = cell;
            }
        }
    }

    public Cell getCellByCoordinates(int xCoordinate, int yCoordinate) {
        for (Cell cell : cells) {
            if (cell.getxCoordinate() == xCoordinate && cell.getyCoordinate() == yCoordinate) {
                return cell;
            }
        }
        return null;
    }
}
