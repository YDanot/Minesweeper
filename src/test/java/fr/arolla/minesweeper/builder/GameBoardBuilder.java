package fr.arolla.minesweeper.builder;

import fr.arolla.minesweeper.GameBoard;


public class GameBoardBuilder {
    private int width;
    private int height;
    private int nbMines;

    private GameBoardBuilder() {
    }

    public static GameBoardBuilder aGameBoard() {
        return new GameBoardBuilder();
    }

    public GameBoardBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public GameBoardBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public GameBoardBuilder withNbMines(int nbMines) {
        this.nbMines = nbMines;
        return this;
    }

    public GameBoardBuilder but() {
        return aGameBoard().withWidth(width).withHeight(height).withNbMines(nbMines);
    }

    public GameBoard build() {
        GameBoard gameBoard = new GameBoard(width, height, nbMines);
        return gameBoard;
    }
}
