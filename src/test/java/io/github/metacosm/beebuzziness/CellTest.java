/**
 * ==========================================================================================
 * =                   JAHIA'S DUAL LICENSING - IMPORTANT INFORMATION                       =
 * ==========================================================================================
 *
 * http://www.jahia.com
 *
 * Copyright (C) 2002-2017 Jahia Solutions Group SA. All rights reserved.
 *
 * THIS FILE IS AVAILABLE UNDER TWO DIFFERENT LICENSES:
 * 1/GPL OR 2/JSEL
 *
 * 1/ GPL
 * ==================================================================================
 *
 * IF YOU DECIDE TO CHOOSE THE GPL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * 2/ JSEL - Commercial and Supported Versions of the program
 * ===================================================================================
 *
 * IF YOU DECIDE TO CHOOSE THE JSEL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 * Alternatively, commercial and supported versions of the program - also known as
 * Enterprise Distributions - must be used in accordance with the terms and conditions
 * contained in a separate written agreement between you and Jahia Solutions Group SA.
 *
 * If you are unsure which license is appropriate for your use,
 * please contact the sales department at sales@jahia.com.
 */
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
