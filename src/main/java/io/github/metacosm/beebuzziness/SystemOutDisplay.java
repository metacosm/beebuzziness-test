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
