package fr.arolla.minesweeper;

import fr.arolla.minesweeper.builder.MinesweeperBuilder;
import org.junit.Test;

import static fr.arolla.minesweeper.builder.CellMineState.EMPTY;
import static fr.arolla.minesweeper.builder.CellMineState.MINE;
import static org.assertj.core.api.Assertions.assertThat;


public class MinesweeperTest {

    private Minesweeper minesweeper;
    private MinesweeperBuilder minesweeperBuilder;

    @Test
    public void when_I_create_a_game_the_game_is_not_over() {
        given_a_minesweeper()
                .withWidth(10)
                .withHeight(10)
                .withNbMines(10);
        then_the_game_is_not_over("La partie est finie avant d'avoir commencé");
    }

    @Test
    public void when_I_create_a_game_there_are_mines_on_grid() {
        given_a_minesweeper()
                .withWidth(10)
                .withHeight(10)
                .withNbMines(10);
        then_mines_number_is(10);
    }

    @Test
    public void when_I_uncover_a_cell_the_cell_is_uncovered() {
        given_a_minesweeper().withWidth(2).withHeight(2).withNbMines(2).withCells(
                EMPTY, MINE,
                MINE, EMPTY);
        
        when_I_uncover(1, 1);
        then_cells_are_uncovered(position(1, 1));
    }

    @Test
    public void when_I_uncover_an_empty_cell_game_continue() {
        given_a_minesweeper().withWidth(2).withHeight(2).withNbMines(2).withCells(
                EMPTY, MINE,
                MINE, EMPTY);

        when_I_uncover(1, 1);
        then_the_game_is_not_over("La partie est finie alors qu'aucune mine n'a été découverte et qu'il reste des cases couvertes vides");
    }

    @Test
    public void when_I_uncover_a_mine_game_is_over() {
        given_a_minesweeper()
                .withWidth(2)
                .withHeight(2)
                .withNbMines(4);
        when_I_uncover(1, 1);
        then_the_game_is_over("La partie n'est pas finie alors qu'une mine a été découverte");
    }


    @Test
    public void when_I_play_outside_grid_my_play_is_not_registered() {
        given_a_minesweeper().withWidth(2).withHeight(2).withNbMines(0);
        when_I_uncover(5, 5);
    }

    @Test
    public void when_I_uncover_an_uncovered_cell_nothing_happens() {
        given_a_minesweeper().withWidth(2).withHeight(2).withNbMines(0);
        when_I_uncover(1, 1);
        when_I_uncover(1, 1);
    }

    @Test
    public void when_I_uncover_all_empty_cells_game_is_over() {
        given_a_minesweeper()
                .withWidth(3)
                .withHeight(3)
                .withNbMines(0);
        when_I_uncover(0, 0);
        then_the_game_is_over("La partie n'est pas finie alors qu'il ne reste plus de case vide");
    }

    @Test
    public void when_I_create_a_game_all_nb_adjacent_mines_are_computed_into_cell(){
        given_a_minesweeper().withWidth(4).withHeight(3).withCells(
                MINE, EMPTY, EMPTY, MINE,
                MINE, EMPTY, EMPTY, MINE,
                MINE, EMPTY, MINE, MINE);
        buildMinesweeper();
        then_adjacent_mines_number_is(1, 2, 2, 1,
                                      2, 4, 4, 3,
                                      1, 3, 2, 2);
    }

    @Test
    public void when_I_uncover_an_empty_cell_with_adjacent_mine_game_do_not_recursively_uncover_adjacent_cells() {
        given_a_minesweeper().withWidth(4).withHeight(5).withCells(
                MINE, EMPTY, EMPTY, EMPTY,
                MINE, EMPTY, EMPTY, EMPTY,
                MINE, EMPTY, EMPTY, EMPTY,
                MINE, EMPTY, EMPTY, MINE,
                MINE, MINE,  EMPTY, EMPTY  );

        when_I_uncover(1, 1);

        then_cells_are_uncovered(position(1, 1));

        then_cells_are_covered(position(0, 3), position(1, 3), position(2, 3), position(3, 3), position(4, 3));
    }

    @Test
    public void when_I_uncover_an_empty_cell_without_adjacent_mine_game_recursively_uncover_adjacent_cells() {
        given_a_minesweeper().withWidth(4).withHeight(5).withCells(
                MINE, EMPTY, EMPTY, EMPTY,
                MINE, EMPTY, EMPTY, EMPTY,
                MINE, EMPTY, EMPTY, EMPTY,
                MINE, EMPTY, EMPTY, MINE,
                MINE, MINE , EMPTY, EMPTY);

        buildMinesweeper();
        when_I_uncover(0, 2);

        then_cells_are_uncovered(position(0, 3), position(1, 3), position(2, 3));

        then_cells_are_covered(
                position(0, 0),
                position(1, 0),
                position(2, 0),
                position(3, 3),
                position(4, 3));
    }

    private MinesweeperBuilder given_a_minesweeper() {
        minesweeperBuilder = MinesweeperBuilder.aMinesweeper();
        return minesweeperBuilder;
    }

    private void when_I_uncover(int line, int column) {
        buildMinesweeper();
        minesweeper.uncoverCell(line, column);
    }
    private void then_the_game_is_not_over(String description) {
        buildMinesweeper();
        assertThat(gameIsOver()).as(description).isFalse();
    }

    private boolean gameIsOver() {
        return minesweeper.gameIsLost() || minesweeper.gameIsWon();
    }

    private void then_the_game_is_over(String description) {
        buildMinesweeper();
        assertThat(gameIsOver()).as(description).isTrue();
    }

    private void then_mines_number_is(int nbMines) {
        buildMinesweeper();
        assertThat(minesweeper.getMinesNumber()).as("nombre de mines sur la grille").isEqualTo(nbMines);
        int nbMinesOnBoard = 0;
        for (int column = 0; column < minesweeper.getBoardWidth(); column++) {
            for (int line = 0; line < minesweeper.getBoardHeight(); line++) {
                if (minesweeper.getCell(position(line, column)).isMined()) {
                    nbMinesOnBoard++;
                }
            }
        }
        assertThat(nbMinesOnBoard).as("nombre de mines sur la grille").isEqualTo(nbMines);

    }

    private void then_adjacent_mines_number_is(int... nbMines) {
        int line=0;
        int column=0;
        for (int nbMine : nbMines) {
            assertThat(minesweeper.getCell(position(line, column)).getAdjacentMinesNumber()).as("Le nombre de mines adjacente de la case "+position(line, column)).isEqualTo(nbMine);
            if (++column % minesweeper.getBoardWidth() == 0){
                line++;
                column=0;
            }
        }
    }

    private void then_cells_are_uncovered(Position... positions) {
        for (Position position : positions) {
            assertThat(minesweeper.getCell(position).isCoverered()).as("la case "+position+" est vide, adjacente au coup joué et sans mines adjacentes, elle doit être découverte").isFalse();
        }
    }

    private void then_cells_are_covered(Position... positions) {
        for (Position position : positions) {
            assertThat(minesweeper.getCell(position).isCoverered()).as("la case " + position + " n'est pas adjacente au coup joué, elle ne doit pas être découverte").isTrue();
        }
    }

    private void buildMinesweeper() {
        if (minesweeper == null) {
            minesweeper = minesweeperBuilder.build();
        }
    }
    
    private Position position(int line, int column) {
        return new Position(line, column);
    }

}