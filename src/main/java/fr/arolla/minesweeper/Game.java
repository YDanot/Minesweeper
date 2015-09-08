package fr.arolla.minesweeper;


public class Game {

    public static void main(String[] args) {
        final Minesweeper minesweeper = createMinesweeper();

        while (isNotOver(minesweeper)) {
            render(minesweeper);
            play(minesweeper);
        }

        endGame(minesweeper);
    }

    private static Minesweeper createMinesweeper() {
        int width = readInt("la largeur", 1, 100);
        int height = readInt("la hauteur", 1, 100);
        int nbMines = readInt("le nombre de mine", 1, width * height - 1);
        return new Minesweeper(width, height, nbMines);
    }

    private static int readInt(String name, int minValue, int maxValue) {
        return new ConsoleReader().readInt(name, minValue, maxValue);
    }

    private static boolean isNotOver(Minesweeper minesweeper) {
        return !minesweeper.gameIsLost() && !minesweeper.gameIsWon();
    }

    private static void render(Minesweeper minesweeper) {
        new MinesweeperRenderer(new MinesweeperView(minesweeper)).render();
    }

    private static void play(Minesweeper minesweeper) {
        final int linePlayed = readCellLine(minesweeper);
        final int columnPlayed = readCellColumn(minesweeper);

        minesweeper.play(linePlayed - 1, columnPlayed - 1);
    }

    private static int readCellLine(Minesweeper minesweeper) {
        return readInt("la ligne", 1, minesweeper.getGameBoardHeight());
    }

    private static int readCellColumn(Minesweeper minesweeper) {
        return readInt("la colonne", 1, minesweeper.getGameBoardWidth());
    }

    private static void endGame(Minesweeper minesweeper) {
        printEndMessage(minesweeper);
        printUncoveredGameBoard(minesweeper);
    }

    private static void printEndMessage(Minesweeper minesweeper) {
        final String gameState = minesweeper.gameIsWon() ? "GAGNE" : "PERDU";
        System.out.println("\n");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" + gameState + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("\n");
    }

    private static void printUncoveredGameBoard(Minesweeper minesweeper) {
        minesweeper.uncoverAllCells();
        render(minesweeper);
    }

}
