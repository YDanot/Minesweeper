package fr.arolla.minesweeper;


public class CellView {

    public final CellViewState state;
    public final int nbAdjacentMines;

    public CellView(CellViewState state, int nbAdjacentMines) {
        this.state = state;
        this.nbAdjacentMines = nbAdjacentMines;
    }

    public CellViewState getState() {
        return state;
    }

    public int getNbAdjacentMines() {
        return nbAdjacentMines;
    }

}

