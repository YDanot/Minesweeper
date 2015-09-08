package fr.arolla.minesweeper;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static fr.arolla.minesweeper.Cell.MINED_COVER;

public class GameBoard {

    private final Board board;
    private final int nbMines;
    private final Map<Position, Cell> cells;
    private boolean mineUncovered;
    private int emptyCoveredCellNumber;

    public GameBoard(int width, int height, int nbMines) {
        this.board = new Board(width, height);
        this.cells = new HashMap<Position, Cell>();
        this.nbMines = nbMines;
        this.emptyCoveredCellNumber = board.getNumberOfCells();
        this.mineUncovered = false;

        initGameBoard();
    }

    public int getEmptyCoveredCellNumber() {
        return emptyCoveredCellNumber;
    }

    public boolean hasMineUncovered() {
        return mineUncovered;
    }

    public int getMinesNumber() {
        return nbMines;
    }

    public void uncoverCell(int line, int column) {
        final Position position = new Position(line, column);
        final Cell cell = getCell(position);

        if (cell != null && cell.isCoverered()) {
            uncover(position);
            if (!mineUncovered && noMineAdjacent(position)) {
                uncoverAdjacentEmptyCells(position);
            }
        }
    }

    public int getAdjacentMinesNumber(Position position) {
        int counterMines = 0;
        for(Position adjacentposition : board.getAdjacentPositions(position)){
            final Cell adjacentCell = getCell(adjacentposition);
            if (adjacentCell.isMined())
                counterMines++;
        }
        return counterMines;
    }

    public void uncoverAllCells() {
        for (Position position : cells.keySet()) {
            uncover(position);
        }
    }

    private void uncoverAdjacentEmptyCells(Position position) {
        final Set<Position> adjacentPositions = board.getAdjacentPositions(position);
        for (Position adjacentPosition : adjacentPositions) {
            Cell adjacentCell = getCell(adjacentPosition);
            if (adjacentCell.isEmpty() && adjacentCell.isCoverered()){
                uncover(adjacentPosition);
                if (noMineAdjacent(adjacentPosition)) {
                    uncoverAdjacentEmptyCells(adjacentPosition);
                }
            }
        }
    }

    private void uncover(Position position) {
        final Cell uncoveredCell = getCell(position).uncover();
        cells.put(position, uncoveredCell);

        if(uncoveredCell.isEmpty()) {
            emptyCoveredCellNumber--;
        }

        if (uncoveredCell.isMined()){
            mineUncovered = true;
        }
    }

    private boolean noMineAdjacent(Position position) {
        return getAdjacentMinesNumber(position) == 0;
    }

    private void initGameBoard() {
        createEmptyCells();
        putMines();
    }

    private void createEmptyCells() {
        for (int column = 0; column < board.getWidth(); column++) {
            for (int line = 0; line < board.getHeight(); line++) {
                cells.put(new Position(line, column), Cell.EMPTY_COVER);
            }
        }
    }

    private void putMines() {
        int minesCounter = nbMines;
        while(minesCounter > 0) {
            cells.put(getRandomEmptyPosition(), MINED_COVER);
            emptyCoveredCellNumber--;
            minesCounter--;
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

    public Board getBoard() {
        return board;
    }
}
