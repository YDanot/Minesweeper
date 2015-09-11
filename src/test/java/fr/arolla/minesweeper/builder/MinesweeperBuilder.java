package fr.arolla.minesweeper.builder;

import fr.arolla.minesweeper.board.cell.Cell;
import fr.arolla.minesweeper.Minesweeper;
import fr.arolla.minesweeper.board.Position;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;


public class MinesweeperBuilder {
    private int width;
    private int height;
    private int nbMines;

    private Map<Position, Cell> cellMap;

    private int line = 0;
    private int column = 0;

    public static MinesweeperBuilder aMinesweeper() {
        return new MinesweeperBuilder();
    }

    private MinesweeperBuilder withCell(Cell cell) {
        if (this.cellMap == null) {
            cellMap = new HashMap<Position, Cell>();
        }

        cellMap.put(new Position(line, column), cell);

        return this;
    }

    public MinesweeperBuilder withCells(CellMineState... cells) {
        for (CellMineState cellMineState : cells) {
            Cell cell = new Cell();
            if (CellMineState.MINE.equals(cellMineState)){
                cell.mine();
            }
            withCell(cell);
            if (++column % width == 0){
                line++;
                column=0;
            }
        }
        return this;
    }

    public MinesweeperBuilder withNbMines(int nbMines) {
        this.nbMines = nbMines;
        return this;
    }

    public MinesweeperBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public MinesweeperBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public Minesweeper build() {
        Minesweeper minesweeper = new Minesweeper(width, height, nbMines);
        if (cellMap != null){
            ReflectionTestUtils.setField(minesweeper, "cells", cellMap);
        }
        minesweeper.computeMinesAdjacentNumber();
        return minesweeper;
    }
}
