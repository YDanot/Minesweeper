package fr.arolla.minesweeper;

public class Minesweeper {

    private GameBoard gameBoard;

    public Minesweeper(int width, int height, int nbMines) {
        this.gameBoard = new GameBoard(width, height, nbMines);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getGameBoardWidth(){
        return getGameBoard().getBoard().getWidth();
    }

    public int getGameBoardHeight(){
        return getGameBoard().getBoard().getHeight();
    }

    public void uncoverAllCells(){
        getGameBoard().uncoverAllCells();
    }

    public boolean gameIsWon() {
        return gameBoard.getEmptyCoveredCellNumber() == 0;
    }

    public boolean gameIsLost() {
        return gameBoard.hasMineUncovered();
    }

    public void play(int line, int column) {
        gameBoard.uncoverCell(line, column);
    }

}
