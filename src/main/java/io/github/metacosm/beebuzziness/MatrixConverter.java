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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple application that converts an integer into an LCD-like representation.
 * @author Christophe Laprun
 */
public class MatrixConverter {
    private static final Cell ZERO = new Cell(0, 1, 0, 1, 0, 1, 1, 1, 1);
    private static final Cell ONE = new Cell(0, 0, 0, 0, 0, 1, 0, 0, 1);
    private static final Cell TWO = new Cell(0, 1, 0, 0, 1, 1, 1, 1, 0);
    private static final Cell THREE = new Cell(0, 1, 0, 0, 1, 1, 0, 1, 1);
    private static final Cell FOUR = new Cell(0, 0, 0, 1, 1, 1, 0, 0, 1);
    private static final Cell FIVE = new Cell(0, 1, 0, 1, 1, 0, 0, 1, 1);
    private static final Cell SIX = new Cell(0, 1, 0, 1, 1, 0, 1, 1, 1);
    private static final Cell SEVEN = new Cell(0, 1, 0, 0, 0, 1, 0, 0, 1);
    private static final Cell EIGHT = new Cell(0, 1, 0, 1, 1, 1, 1, 1, 1);
    private static final Cell NINE = new Cell(0, 1, 0, 1, 1, 1, 0, 0, 1);

    /**
     * Holds the Cell representation for each digit.
     */
    private static final Map<Integer, Cell> digits = new HashMap<>(10);

    static {
        digits.put(0, ZERO);
        digits.put(1, ONE);
        digits.put(2, TWO);
        digits.put(3, THREE);
        digits.put(4, FOUR);
        digits.put(5, FIVE);
        digits.put(6, SIX);
        digits.put(7, SEVEN);
        digits.put(8, EIGHT);
        digits.put(9, NINE);
    }

    public static void main(String[] args) {
        for (String arg : args) {
            // convert each argument into an int
            int toDisplay = Integer.parseInt(arg);

            // decompose the int into its components
            List<Integer> components = Decomposer.decompose(toDisplay);
            List<Cell> cells = components.stream().map(digits::get).collect(Collectors.toList());

            System.out.println(arg + ":");
            SystemOutDisplay.getInstance().display(cells);
            System.out.println();
        }
    }
}
