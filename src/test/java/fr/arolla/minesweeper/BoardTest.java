package fr.arolla.minesweeper;

import fr.arolla.minesweeper.builder.BoardBuilder;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class BoardTest {

    private BoardBuilder boardBuilder;
    private Set<Position> adjacentPositions;

    @Test
    public void when_I_ask_for_adjacent_position_of_center_position_it_returns_only_all_adjacent_positions(){
        given_a_board().withWidth(5).withHeight(5);

        when_I_ask_for_adjacent_position_of(position(2, 2));
        then_adjacent_position_are("Les positions adjacentes de la case 2|2 ne correspondent pas",
                position(1, 1), position(1, 2), position(1, 3),
                position(2, 1),                 position(2, 3),
                position(3, 1), position(3, 2), position(3, 3));
    }

    @Test
    public void when_I_ask_for_adjacent_position_of_top_left_corner_it_returns_only_right_down_positions(){
        given_a_board()
                .withWidth(5)
                .withHeight(5);

        when_I_ask_for_adjacent_position_of(position(0, 0));
        then_adjacent_position_are("Les positions adjacentes de la case 0|0 ne correspondent pas",
                position(0, 1),
                position(1, 0), position(1, 1));
    }

    @Test
    public void when_I_ask_for_adjacent_position_of_top_right_corner_it_returns_only_left_down_positions(){
        given_a_board()
                .withWidth(5)
                .withHeight(5);

        when_I_ask_for_adjacent_position_of(position(0, 4));
        then_adjacent_position_are("Les positions adjacentes de la case 0|4 ne correspondent pas",
                position(0, 3),
                position(1, 3), position(1, 4));
    }

    @Test
    public void when_I_ask_for_adjacent_position_of_bottom_right_corner_it_returns_only_left_up_positions(){
        given_a_board()
                .withWidth(5)
                .withHeight(5);

        when_I_ask_for_adjacent_position_of(position(4, 4));
        then_adjacent_position_are("Les positions adjacentes de la case 4|4 ne correspondent pas",
                position(3, 3), position(3, 4),
                position(4, 3)                 );
    }

    @Test
    public void when_I_ask_for_adjacent_position_of_bottom_left_corner_it_returns_only_right_up_positions(){
        given_a_board()
                .withWidth(5)
                .withHeight(5);

        when_I_ask_for_adjacent_position_of(position(4, 0));
        then_adjacent_position_are("Les positions adjacentes de la case 4|0 ne correspondent pas",
                position(3, 0), position(3, 1),
                position(4, 1));
    }

    private BoardBuilder given_a_board() {
        boardBuilder = BoardBuilder.aBoard();
        return boardBuilder;
    }

    private void when_I_ask_for_adjacent_position_of(Position position) {
        adjacentPositions = boardBuilder.build().getAdjacentPositions(position);
    }

    private void then_adjacent_position_are(String description, Position... positions) {
        assertThat(adjacentPositions.size()).as(description).isEqualTo(positions.length);
        for (Position position : positions) {
            assertThat(adjacentPositions).as(description).contains(position);
        }
    }

    private Position position(int line, int column) {
        return new Position(line, column);
    }


}