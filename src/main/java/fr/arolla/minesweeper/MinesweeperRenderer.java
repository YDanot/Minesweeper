package fr.arolla.minesweeper;

public class MinesweeperRenderer {

    public final MinesweeperView minesweeperView;

    public MinesweeperRenderer(MinesweeperView minesweeperView) {
        this.minesweeperView = minesweeperView;
    }

    public void render() {
        System.out.println(computeView());
    }

    private String computeView() {
        StringBuilder viewBuilder = new StringBuilder();
        viewBuilder.append(getColumnHeaders());

        for (int line = 0; line < minesweeperView.getGameBoardHeight(); line++) {
            for (int column = 0; column < minesweeperView.getGameBoardWidth(); column++) {
                if (column == 0) {
                    viewBuilder.append(getLineHeader(line));
                }
                viewBuilder.append(view(line, column));
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

    private String getColumnHeaders() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int column = 0; column < minesweeperView.getGameBoardWidth(); column++) {
            if (column == 0)
                stringBuilder.append("     ");
            stringBuilder.append(" " + (column + 1) + "  ");
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private String view(int line, int column) {
        final Position position = new Position(line, column);
        final CellView cellView = minesweeperView.getCellView(position);
        return view(cellView);
    }

    private String view(CellView cellView) {
        String view;
        switch (cellView.getState()) {
            case COVERED:
                view = "#";
                break;
            case EMPTY:
                view = String.valueOf(cellView.getNbAdjacentMines());
                break;
            case MINED:
                view = "@";
                break;
            default:
                view = "";
        }

        return " " + view + " |";
    }
}
