package com.company.Models.Battle.Map;

import com.company.Models.Card.Soldier;

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
    public StringBuilder getMap(){
        StringBuilder stringBuilder=new StringBuilder();
        int counter=0;
        for(Cell cell:getCells()){
            counter++;
            if(cell.getCardInCell()==null){
                stringBuilder.append('#');
            }
            if(cell.getCardInCell() instanceof Soldier){
                stringBuilder.append(cell.getCardInCell().getId());
            }

            if (counter==9){
                stringBuilder.append('\n');
                counter=0;
            }
        }
        return stringBuilder;
    }
}
