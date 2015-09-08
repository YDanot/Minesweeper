package fr.arolla.minesweeper.builder;

import fr.arolla.minesweeper.Cell;
import fr.arolla.minesweeper.Minesweeper;
import fr.arolla.minesweeper.Position;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

public class MinesweeperBuilder {

    private int width;
    private int height;
    private int nbMines;
    private Map<Position, Cell> cells;

    public static MinesweeperBuilder aMinesweeper() {
        return new MinesweeperBuilder();
    }

    public MinesweeperBuilder withCell(Position position, Cell cell) {
        if (this.cells == null){
            cells = new HashMap<Position, Cell>();
        }
        cells.put(position, cell);
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

    public MinesweeperBuilder withNbMines(int nbMines) {
        this.nbMines = nbMines;
        return this;
    }

    public Minesweeper build() {
        Minesweeper minesweeper = new Minesweeper(width, height, nbMines);
        if (cells != null){
            ReflectionTestUtils.setField(minesweeper.getGameBoard(), "cells", cells);
        }
        return minesweeper;
    }
}
