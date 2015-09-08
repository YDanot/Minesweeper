package fr.arolla.minesweeper;

import java.util.LinkedHashMap;
import java.util.Map;

public class MinesweeperView {

    private final Minesweeper minesweeper;
    private final Map<Position, CellView> boardView = new LinkedHashMap<Position, CellView>();

    public MinesweeperView(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
        initGridView();
    }

    public CellView getCellView(Position position) {
        return boardView.get(position);
    }

    public int getGameBoardHeight() {
        return getHeight();
    }

    public int getGameBoardWidth() {
        return getWidth();
    }

    private Map<Position, CellView> initGridView() {
        for (int line = 0; line < getHeight(); line++) {
            for (int column = 0; column < getWidth(); column++) {
                final Position position = new Position(line, column);
                final CellView cellView = computeCellView(position);
                boardView.put(position, cellView);
            }
        }

        return boardView;
    }

    private int getWidth() {
        return getGameBoard().getBoard().getWidth();
    }

    private int getHeight() {
        return getGameBoard().getBoard().getHeight();
    }

    private CellView computeCellView(Position position) {
        final Cell cell = getGameBoard().getCell(position);
        final int nbAdjacentMines = getGameBoard().getAdjacentMinesNumber(position);
        final CellViewState cellViewState = getCellViewState(cell);

        return new CellView(cellViewState, nbAdjacentMines);
    }

    private CellViewState getCellViewState(Cell cell) {
        CellViewState cellViewState;

        if (cell.isCoverered()){
            cellViewState = CellViewState.COVERED;
        }
        else {
            cellViewState = CellViewState.EMPTY;

            if (cell.isMined()) {
                cellViewState = CellViewState.MINED;
            }
        }
        return cellViewState;
    }


    private GameBoard getGameBoard() {
        return minesweeper.getGameBoard();
    }
}
