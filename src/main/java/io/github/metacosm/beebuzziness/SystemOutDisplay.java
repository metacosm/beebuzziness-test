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

import java.util.List;

/**
 * Outputs {@link Cell}s to the {@link System#out} console.
 *
 * @author Christophe Laprun
 */
public class SystemOutDisplay implements Display {

    private static final char OFF_SEGMENT = ' ';
    private static final char MIDDLE_SEGMENT = '_';
    private static final char EXTERNAL_SEGMENTS = '|';

    // Initialization on demand holder idiom: thread-safe singleton initialization
    private static class Holder {
        static final SystemOutDisplay INSTANCE = new SystemOutDisplay();

        private Holder() {
        }
    }

    public static SystemOutDisplay getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void display(List<Cell> cells) {
        System.out.println(computeStringFor(cells));
    }

    /**
     * Computes a {@link String} representation for the given {@link Cell}s.
     *
     * @param cells the Cells to convert to String
     * @return a {@link String} representation for the given {@link Cell}s.
     */
    String computeStringFor(List<Cell> cells) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Cell.ROW_NUMBER; i++) {
            for (Cell cell : cells) {
                appendRowToStringBuilder(cell.getRow(i), builder);
                builder.append(' ');
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    private static void appendRowToStringBuilder(Cell.Row row, StringBuilder stringBuilder) {
        for (int i = 0; i < Cell.COLUMN_NUMBER; i++) {
            final boolean isSegmentOn = row.isOn(i);
            if (!isSegmentOn) {
                // space if segment is off
                stringBuilder.append(OFF_SEGMENT);
            } else {
                // middle segment is "_", external segments are "|"
                stringBuilder.append(i == 1 ? MIDDLE_SEGMENT : EXTERNAL_SEGMENTS);
            }
        }
    }
}
