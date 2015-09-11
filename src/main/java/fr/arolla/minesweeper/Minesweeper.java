package fr.arolla.minesweeper;


import fr.arolla.minesweeper.board.Board;
import fr.arolla.minesweeper.board.Position;
import fr.arolla.minesweeper.board.cell.Cell;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Minesweeper {

    private final Board board;
    private final int nbMines;
    private final Map<Position, Cell> cells;
    private boolean mineUncovered;
    private int emptyCoveredCellNumber;

    public Minesweeper(int width, int height, int nbMines) {
        this.board = new Board(width, height);
        this.cells = new HashMap<Position, Cell>();
        this.nbMines = nbMines;
        this.emptyCoveredCellNumber = board.getNumberOfCells();
        this.mineUncovered = false;

        initGameBoard();
    }

    public void uncoverCell(int line, int column) {
        final Position position = new Position(line, column);
        final Cell cell = getCell(position);

        if (cell != null && cell.isCoverered()) {
            uncover(position);
            if (!mineUncovered && noMineAdjacent(cell)) {
                uncoverAdjacentEmptyCells(position);
            }
        }
    }

    public void uncoverAllCells() {
        for (Position position : cells.keySet()) {
            uncover(position);
        }
    }

    private int computeAdjacentMinesNumber(Position position) {
        int counterMines = 0;
        for(Position adjacentposition : board.getAdjacentPositions(position)){
            final Cell adjacentCell = getCell(adjacentposition);
            if (adjacentCell.isMined())
                counterMines++;
        }
        return counterMines;
    }

    private void uncoverAdjacentEmptyCells(Position position) {
        final Set<Position> adjacentPositions = board.getAdjacentPositions(position);
        for (Position adjacentPosition : adjacentPositions) {
            Cell adjacentCell = getCell(adjacentPosition);
            if (adjacentCell.isEmpty() && adjacentCell.isCoverered()){
                uncover(adjacentPosition);
                if (noMineAdjacent(adjacentCell)) {
                    uncoverAdjacentEmptyCells(adjacentPosition);
                }
            }
        }
    }

    private void uncover(Position position) {
        Cell coveredCell = getCell(position);

        if (coveredCell != null){
            coveredCell.uncover();
        }

        if(coveredCell.isEmpty()) {
            emptyCoveredCellNumber--;
        }

        if (coveredCell.isMined()){
            mineUncovered = true;
        }
    }

    private boolean noMineAdjacent(Cell cell) {
        return cell.getAdjacentMinesNumber() == 0;
    }

    private void initGameBoard() {
        createEmptyCells();
        putMines();
        computeMinesAdjacentNumber();
    }

    private void createEmptyCells() {
        for (int column = 0; column < board.getWidth(); column++) {
            for (int line = 0; line < board.getHeight(); line++) {
                cells.put(new Position(line, column), new Cell());
            }
        }
    }

    private void putMines() {
        int minesCounter = nbMines;
        while(minesCounter > 0) {
            Cell cell = cells.get(getRandomEmptyPosition());
            cell.mine();
            emptyCoveredCellNumber--;
            minesCounter--;
        }
    }

    private void computeMinesAdjacentNumber() {
        for (Position position : cells.keySet()) {
            final int adjacentMinesNumber = computeAdjacentMinesNumber(position);
            cells.get(position).setAdjacentMinesNumber(adjacentMinesNumber);
        }
    }

    private Position getRandomEmptyPosition() {
        Position randomPosition = board.getRandomPosition();
        Cell randomCell = getCell(randomPosition);
        while(randomCell.isMined()){
            randomPosition = board.getRandomPosition();
            randomCell = getCell(randomPosition);
        }

        return randomPosition;
    }


    public Cell getCell(Position position) {
        return cells.get(position);
    }

    public boolean gameIsLost() {
        return mineUncovered;
    }

    public boolean gameIsWon() {
        return emptyCoveredCellNumber == 0;
    }

    public int getBoardWidth() {
        return board.getWidth();
    }

    public int getBoardHeight() {
        return board.getHeight();
    }
}
