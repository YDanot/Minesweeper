package fr.arolla.minesweeper;

import fr.arolla.minesweeper.builder.MinesweeperBuilder;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static fr.arolla.minesweeper.Cell.EMPTY_COVER;
import static fr.arolla.minesweeper.Cell.EMPTY_UNCOVER;
import static fr.arolla.minesweeper.Cell.MINED_COVER;
import static org.assertj.core.api.Assertions.assertThat;


public class MinesweeperViewTest {

    private MinesweeperBuilder minesweeperBuilder;
    private MinesweeperView minesweeperView;

    @Test
    public void when_I_ask_for_adjacent_nb_mines_it_counts_all_adjacent_mine(){
        given_a_minesweeper().withWidth(4).withHeight(3)
                .withCell(position(0, 0), MINED_COVER).withCell(position(0, 1), EMPTY_COVER).withCell(position(0, 2), EMPTY_COVER).withCell(position(0, 3), MINED_COVER)
                .withCell(position(1, 0), MINED_COVER).withCell(position(1, 1), EMPTY_COVER).withCell(position(1, 2), EMPTY_COVER).withCell(position(1, 3), MINED_COVER)
                .withCell(position(2, 0), MINED_COVER).withCell(position(2, 1), EMPTY_COVER).withCell(position(2, 2), MINED_COVER).withCell(position(2, 3), MINED_COVER);

        when_I_compute_minesweeper_view();
        then_adjacent_mines_number_is(position(1, 2), 4);
    }

    private MinesweeperBuilder given_a_minesweeper() {
        minesweeperBuilder = MinesweeperBuilder.aMinesweeper();
        return minesweeperBuilder;
    }

    private void when_I_compute_minesweeper_view() {
        minesweeperView = new MinesweeperView(minesweeperBuilder.build());
    }

    private void then_cell_view_state_is(Position position, CellViewState cellViewState) {
        assertThat(getGridView().get(position).getState()).as("le contenu de la case").isEqualTo(cellViewState);
    }

    private void then_adjacent_mines_number_is(Position position, int nbMines) {
        assertThat(getGridView().get(position).getNbAdjacentMines()).as("Le nombre de mines adjacente").isEqualTo(nbMines);
    }

    private Map<Position, CellView> getGridView() {
        return (Map<Position, CellView>) ReflectionTestUtils.getField(minesweeperView, "boardView");
    }

    private Position position(int line, int column) {
        return new Position(line, column);
    }

}