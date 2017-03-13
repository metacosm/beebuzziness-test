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

import java.util.BitSet;
import java.util.Collections;

/**
 * A {@link #ROW_NUMBER} x {@link #COLUMN_NUMBER}-segment cell mimicking LCD cells. Segments are organized in
 * {@link #ROW_NUMBER} {@link Row}s of each {@link #COLUMN_NUMBER} segments. The status of a given segment can
 * therefore be known based on a {@code (row, column)} coordinate.
 *
 * @author Christophe Laprun
 */
class Cell {
    static final int ROW_NUMBER = 3;
    static final int COLUMN_NUMBER = 3;
    private final Row[] rows = new Row[ROW_NUMBER];

    /**
     * Builds a Cell based on the specified values for the segments, passing each values for each segment in each
     * row, starting with the first row.
     *
     * @param segments the values for the segments, see {@link Row#switchSegment(int, int)}
     * @throws IllegalArgumentException if an improper number of values for segments are given
     */
    Cell(int... segments) {
        if (segments.length != ROW_NUMBER * COLUMN_NUMBER) {
            throw new IllegalArgumentException("Must specify status for all segments");
        }

        int column = 0;
        int row = 0;
        int index = 0;
        for (int segment : segments) {
            Row currentRow = rows[row];
            if (currentRow == null) {
                currentRow = new Row();
                rows[row] = currentRow;
            }

            currentRow.switchSegment(column, segment);

            // compute row and column
            index++;
            column = index % COLUMN_NUMBER;
            row = index / ROW_NUMBER;
        }
    }

    /**
     * Determines whether the segment located at the specified row and column in the cell is on or not.
     *
     * @param row    the row of the segment we're interested in, must be between {@code 0} and {@link #ROW_NUMBER} - 1
     * @param column the column of the segment we're interested in, must be between {@code 0} and
     *               {@link #COLUMN_NUMBER} - 1
     * @return {@code true} if the segment is on, {@code false} otherwise
     * @throws IndexOutOfBoundsException if any of the specified coordinates are not within existing bounds
     */
    boolean isOn(int row, int column) {
        return rows[row].isOn(column);
    }

    /**
     * Retrieves the {@link Row} at the specified coordinate.
     *
     * @param row the index of the row to retrieve, must be between {@code 0} and {@link #ROW_NUMBER} - 1
     * @return the row located at the specified index
     * @throws IndexOutOfBoundsException if the specified index is not within acceptable bounds
     */
    Row getRow(int row) {
        return rows[row];
    }


    @Override
    public String toString() {
        return SystemOutDisplay.getInstance().computeStringFor(Collections.singletonList(this));
    }


    /**
     * Encapsulates the internal representation of a row for a cell.
     */
    static class Row {
        private final BitSet segments = new BitSet(COLUMN_NUMBER);

        /**
         * Switches the specified segment on or off based on given value. This method is private so that we only
         * present an immutable view of the Row to client classes.
         *
         * @param segment      the segment to switch on or off
         * @param segmentValue even values (e.g. {@code 0}) turn the segment off, while odd values (e.g. {@code 1})
         *                     turn the segment on
         */
        private void switchSegment(int segment, int segmentValue) {
            validateColumn(segment);

            boolean segmentOn = segmentValue % 2 == 1;
            if (segmentOn) {
                segments.set(segment);
            }
        }

        /**
         * Determines whether the specified segment is on or not.
         *
         * @param segment the segment which status we're checking
         * @return {@code true} if the segment is on, {@code false} otherwise
         */
        boolean isOn(int segment) {
            validateColumn(segment);

            return segments.get(segment);
        }

        private void validateColumn(int segment) {
            if (segment >= COLUMN_NUMBER) {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
