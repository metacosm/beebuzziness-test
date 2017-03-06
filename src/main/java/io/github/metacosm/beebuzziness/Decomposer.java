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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Decomposes an integer into its powers of ten components.
 *
 * @author Christophe Laprun
 */
class Decomposer {

    /**
     * Decomposes an integer into its powers of ten components. For example, {@code 1234} will return a list
     * containing {@code 1, 2, 3, 4} since {@code 1234 = 1 * 1000 + 2 * 100 + 3 * 10 + 4 * 1}.
     * @param toDecompose the positive integer to decompose
     * @return the list of components for the specified integer
     * @throws IllegalArgumentException if the specified integer is negative
     */
    public static List<Integer> decompose(int toDecompose) {
        if(toDecompose < 0) {
            throw new IllegalArgumentException("Can currently only decompose positive integer. Sorry!");
        }
        
        final List<Integer> result = new LinkedList<>();

        // extract power of ten components of given integer
        while(toDecompose >= 10) {
            int remainder = toDecompose % 10;
            result.add(remainder);
            toDecompose /= 10;
        }

        // add the last component (or the only one if the given integer was less than 10)
        result.add(toDecompose);

        // we get components in reverse order so we need to reverse the list
        Collections.reverse(result);

        return result;
    }
}
