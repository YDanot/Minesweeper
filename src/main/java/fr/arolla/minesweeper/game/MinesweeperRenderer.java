package fr.arolla.minesweeper.game;

import fr.arolla.minesweeper.Minesweeper;
import fr.arolla.minesweeper.board.Position;
import fr.arolla.minesweeper.board.cell.Cell;

public class MinesweeperRenderer {

    public void render(Minesweeper minesweeper) {
        System.out.println(computeView(minesweeper));
    }

    private String computeView(Minesweeper minesweeper) {
        StringBuilder viewBuilder = new StringBuilder();
        viewBuilder.append(getColumnHeaders(minesweeper.getBoardWidth()));

        for (int line = 0; line < minesweeper.getBoardHeight(); line++) {
            for (int column = 0; column < minesweeper.getBoardWidth(); column++) {
                if (column == 0) {
                    viewBuilder.append(getLineHeader(line));
                }
                viewBuilder.append(view(minesweeper.getCell(new Position(line, column))));
            }
            viewBuilder.append("\n");
        }

        return viewBuilder.toString();
    }


    private String getLineHeader(int line) {
        StringBuilder stringBuilder = new StringBuilder();
        if (line < 9) {
            stringBuilder.append(" " + (line + 1) + "  |");
        } else {
            stringBuilder.append(" " + (line + 1) + " |");
        }
        return stringBuilder.toString();
    }

    private String getColumnHeaders(int gameBoardWidth) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int column = 0; column < gameBoardWidth; column++) {
            if (column == 0)
                stringBuilder.append("     ");
            stringBuilder.append(" " + (column + 1) + "  ");
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private String view(Cell cell) {
        String view;

        if (cell.isCoverered()){
            view = "#";
        }
        else if(cell.isMined()){
            view = "@";
        }
        else {
            view = String.valueOf(cell.getAdjacentMinesNumber());
        }
        

        return " " + view + " |";
    }
}
