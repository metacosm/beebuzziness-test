package io.github.metacosm.beebuzziness;

import java.util.List;

/**
 * A representation of a possible {@link Cell} display implementation.
 * @author Christophe Laprun
 */
public interface Display {
    /**
     * Displays the specified cells.
     *
     * @param cells the {@link Cell}s to be displayed
     */
    void display(List<Cell> cells);
}
