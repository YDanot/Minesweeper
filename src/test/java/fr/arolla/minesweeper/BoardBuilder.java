package fr.arolla.minesweeper;

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

    public BoardBuilder but() {
        return aBoard().withWidth(width).withHeight(height);
    }

    public Board build() {
        Board board = new Board(width, height);
        return board;
    }
}
