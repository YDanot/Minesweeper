package fr.arolla.minesweeper.game;


import fr.arolla.minesweeper.Minesweeper;

public class Game {

    public static void main(String[] args) {
        final Minesweeper minesweeper = createMinesweeper();
        final MinesweeperRenderer minesweeperRenderer = new MinesweeperRenderer();
        while (isNotOver(minesweeper)) {
            render(minesweeperRenderer, minesweeper);
            play(minesweeper);
        }

        endGame(minesweeperRenderer, minesweeper);
    }

    private static Minesweeper createMinesweeper() {
        int width = readInt("la largeur", 1, 50);
        int height = readInt("la hauteur", 1, 50);
        int nbMines = readInt("le nombre de mine", 1, width * height - 1);
        return new Minesweeper(width, height, nbMines);
    }

    private static int readInt(String name, int minValue, int maxValue) {
        return new ConsoleReader().readInt(name, minValue, maxValue);
    }

    private static boolean isNotOver(Minesweeper minesweeper) {
        return !minesweeper.gameIsLost() && !minesweeper.gameIsWon();
    }

    private static void render(MinesweeperRenderer minesweeperRenderer, Minesweeper minesweeper) {
        minesweeperRenderer.render(minesweeper);
    }

    private static void play(Minesweeper minesweeper) {
        final int linePlayed = readCellLine(minesweeper);
        final int columnPlayed = readCellColumn(minesweeper);

        minesweeper.uncoverCell(linePlayed - 1, columnPlayed - 1);
    }

    private static int readCellLine(Minesweeper minesweeper) {
        return readInt("la ligne", 1, minesweeper.getBoardHeight());
    }

    private static int readCellColumn(Minesweeper minesweeper) {
        return readInt("la colonne", 1, minesweeper.getBoardWidth());
    }

    private static void endGame(MinesweeperRenderer minesweeperRenderer, Minesweeper minesweeper) {
        printEndMessage(minesweeper);
        printUncoveredGameBoard(minesweeperRenderer, minesweeper);
    }

    private static void printEndMessage(Minesweeper minesweeper) {
        final String gameState = minesweeper.gameIsWon() ? "GAGNE" : "PERDU";
        System.out.println("\n");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" + gameState + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("\n");
    }

    private static void printUncoveredGameBoard(MinesweeperRenderer minesweeperRenderer, Minesweeper minesweeper) {
        minesweeper.uncoverAllCells();
        render(minesweeperRenderer, minesweeper);
    }

}
