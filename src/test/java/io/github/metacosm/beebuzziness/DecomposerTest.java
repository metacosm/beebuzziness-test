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
public class DecomposerTest {

    @Test
    public void decomposerShouldWorkCorrectly() {
        final int zero = 0;
        assertThat(Decomposer.decompose(zero)).containsExactly(0);

        final int seven = 7;
        assertThat(Decomposer.decompose(seven)).containsExactly(7);

        int test = 123456789;
        assertThat(Decomposer.decompose(test)).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    public void decomposerShouldFailIfGivenANegativeInt() {
        try {
            Decomposer.decompose(-1);
            fail("Can only decompose positive integers");
        } catch (IllegalArgumentException expected) {
            //expected
        }
    }

}
