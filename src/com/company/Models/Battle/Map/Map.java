package com.company.Models.Battle.Map;

import com.company.Models.Card.Soldier;

public class Map {
    private Cell[] cells = new Cell[5 * 9];

    public Cell[] getCells() {
        return cells;
    }

    public Map() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                Cell cell = new Cell();
                cell.setxCoordinate(i + 1);
                cell.setyCoordinate(j + 1);
                cells[j * 9 + i] = cell;
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

    @Override
    public String toString(){
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
        return stringBuilder.toString();
    }
}
