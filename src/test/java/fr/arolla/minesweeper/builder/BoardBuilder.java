package fr.arolla.minesweeper.builder;

import fr.arolla.minesweeper.board.Board;

public class BoardBuilder {
    private int width;
    private int height;

    private BoardBuilder() {
    }

    public static BoardBuilder aBoard() {
        return new BoardBuilder();
    }

    public BoardBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public BoardBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public Board build() {
        Board board = new Board(width, height);
        return board;
    }
}
