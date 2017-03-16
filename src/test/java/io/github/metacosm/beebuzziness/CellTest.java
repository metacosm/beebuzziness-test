package io.github.metacosm.beebuzziness;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author Christophe Laprun
 */
public class CellTest {

    @Test
    public void constructorShouldFailIfNotGivenEnoughSegments() {
        try {
            new Cell(0, 1, 2);
            fail("Cell constructor should fail if not given 9 segments");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    @Test
    public void constructorShouldWorkProperly() {
        Cell cell = new Cell(0, 1, 0, 1, 0, 1, 1, 1, 1);
        assertThat(cell.isOn(0, 0)).isFalse();
        assertThat(cell.isOn(0, 1)).isTrue();
        assertThat(cell.isOn(0, 2)).isFalse();
        assertThat(cell.isOn(1, 0)).isTrue();
        assertThat(cell.isOn(1, 1)).isFalse();
        assertThat(cell.isOn(1, 2)).isTrue();
        assertThat(cell.isOn(2, 0)).isTrue();
        assertThat(cell.isOn(2, 1)).isTrue();
        assertThat(cell.isOn(2, 2)).isTrue();
    }

    @Test
    public void isOnShouldThrowExceptionIfCoordinatesAreOutOfBounds() {
        final Cell cell = new Cell(0, 1, 0, 1, 0, 1, 1, 1, 1);

        try {
            cell.isOn(-1, 0);
            fail("isOn shouldn't accept negative coordinates");
        } catch (IndexOutOfBoundsException expected) {
            // expected
        }

        try {
            cell.isOn(0, -100);
            fail("isOn shouldn't accept negative coordinates");
        } catch (IndexOutOfBoundsException expected) {
            // expected
        }

        try {
            cell.isOn(Cell.ROW_NUMBER, 0);
            fail("isOn shouldn't accept coordinates greater than grid size");
        } catch (IndexOutOfBoundsException expected) {
            // expected
        }

        try {
            cell.isOn(0, 100);
            fail("isOn shouldn't accept coordinates greater than grid size");
        } catch (IndexOutOfBoundsException expected) {
            // expected
        }
    }

    @Test
    public void getRowShouldThrowAnExceptionIfIndexIsOutOfBounds() {
        final Cell cell = new Cell(0, 1, 0, 1, 0, 1, 1, 1, 1);

        try {
            cell.getRow(-1);
            fail("getRow should only accept indices between 0 and " + Cell.ROW_NUMBER + " - 1");
        } catch (IndexOutOfBoundsException expected) {
            // expected
        }

        try {
            cell.getRow(Cell.ROW_NUMBER);
            fail("getRow should only accept indices between 0 and " + Cell.ROW_NUMBER + " - 1");
        } catch (IndexOutOfBoundsException expected) {
            // expected
        }
    }
}
