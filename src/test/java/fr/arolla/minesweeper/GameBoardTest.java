package fr.arolla.minesweeper;

import fr.arolla.minesweeper.builder.GameBoardBuilder;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//TODO Déplacer les tests de fr.arolla.minesweeper.MinesweeperTest ici
public class GameBoardTest {

    private GameBoardBuilder gameBoardBuilder;
    private GameBoard gameBoard;

    @Test
    public void when_I_play_outside_grid_my_play_is_not_registered() {
        given_a_gameboard().withWidth(2).withHeight(2).withNbMines(0);
        when_I_uncover(position(5, 5));
    }

    @Test
    public void when_I_uncover_an_uncovered_cell_nothing_happens() {
        given_a_gameboard().withWidth(2).withHeight(2).withNbMines(0);
        when_I_uncover(position(1, 1));
        when_I_uncover(position(1, 1));
    }

    private GameBoardBuilder given_a_gameboard() {
        gameBoardBuilder = GameBoardBuilder.aGameBoard();
        return gameBoardBuilder;
    }

    private void when_I_uncover(Position position) {
        buildGameBoard();
        gameBoard.uncoverCell(position.getLine(), position.getColumn());
    }

    private void buildGameBoard() {
        gameBoard = gameBoardBuilder.build();
    }

    private Position position(int line, int column) {
        return new Position(line, column);
    }


}