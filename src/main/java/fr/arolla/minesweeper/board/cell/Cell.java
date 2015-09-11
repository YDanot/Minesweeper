package fr.arolla.minesweeper.board.cell;


import static fr.arolla.minesweeper.board.cell.CellState.*;

public class Cell {

    public CellState state;
    private int adjacentMinesNumber;

    public Cell() {
        this.state = EMPTY_COVER;
        this.adjacentMinesNumber = 0;
    }

    public CellState getState() {
        return state;
    }


    public boolean isCoverered() {
        return MINED_COVER.equals(state) || EMPTY_COVER.equals(state);
    }

    public boolean isMined(){
        return MINED_UNCOVER.equals(state) || MINED_COVER.equals(state);
    }

    public boolean isEmpty() {
        return EMPTY_UNCOVER.equals(state) || EMPTY_COVER.equals(state);
    }

    public void uncover(){
        switch (state){
            case EMPTY_COVER : state = EMPTY_UNCOVER; break;
            case MINED_COVER : state = MINED_UNCOVER; break;
            default : break;
        }
    }

    public void mine() {
        state = MINED_COVER;
    }

    public int getAdjacentMinesNumber() {
        return adjacentMinesNumber;
    }

    public void setAdjacentMinesNumber(int adjacentMinesNumber) {
        this.adjacentMinesNumber = adjacentMinesNumber;
    }

    @Override
    public String toString() {
        return "Cell{" + state +" " +adjacentMinesNumber +'}';
    }
}

