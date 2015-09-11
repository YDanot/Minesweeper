package fr.arolla.minesweeper.board;


import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Board {

    private static final Random random = new Random();

    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<Position> getAdjacentPositions(Position position) {
        Set<Position> adjacentPosition = new LinkedHashSet<Position>();
        final int positionLine = position.getLine();
        final int positionColumn = position.getColumn();
        final int left = positionColumn > 0 ? positionColumn - 1 : positionColumn;
        final int right = positionColumn < (width-1) ? positionColumn + 1 : positionColumn;
        final int up = positionLine > 0 ? positionLine - 1 : positionLine;
        final int down = positionLine < (height-1) ? positionLine + 1 : positionLine;

        for (int column = left; column <= right; column++) {
            for (int line = up; line <= down; line++) {
                if (line != positionLine || column != positionColumn) {
                    adjacentPosition.add(new Position(line, column));
                }
            }
        }
        return adjacentPosition;
    }

    public int getNumberOfCells(){
        return width * height;
    }

    public Position getRandomPosition() {
        return new Position(random.nextInt(height), random.nextInt(width));
    }
}
