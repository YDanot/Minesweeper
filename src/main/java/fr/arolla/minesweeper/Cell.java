package fr.arolla.minesweeper;


public enum Cell {
    MINED_COVER,
    MINED_UNCOVER,
    EMPTY_COVER,
    EMPTY_UNCOVER;

    public boolean isCoverered() {
        return MINED_COVER.equals(this) || EMPTY_COVER.equals(this);
     }

    public boolean isMined(){
        return MINED_UNCOVER.equals(this) || MINED_COVER.equals(this);
    }

    public boolean isEmpty() {
        return EMPTY_UNCOVER.equals(this) || EMPTY_COVER.equals(this);
    }

    public Cell uncover(){
        switch (this){
            case EMPTY_COVER :
            case EMPTY_UNCOVER : return EMPTY_UNCOVER;
            case MINED_COVER :
            case MINED_UNCOVER : return MINED_UNCOVER;
        }

        return null;
    }

}