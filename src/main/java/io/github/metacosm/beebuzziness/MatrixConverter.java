package io.github.metacosm.beebuzziness;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * A simple application that converts an integer into an LCD-like representation.
 *
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
    private static final Map<Character, Cell> digits = new HashMap<>(10);

    static {
        digits.put('0', ZERO);
        digits.put('1', ONE);
        digits.put('2', TWO);
        digits.put('3', THREE);
        digits.put('4', FOUR);
        digits.put('5', FIVE);
        digits.put('6', SIX);
        digits.put('7', SEVEN);
        digits.put('8', EIGHT);
        digits.put('9', NINE);
    }

    public static void main(String[] args) {
        for (String arg : args) {

            final List<Cell> cells = new LinkedList<>();
            final List<Character> ignored = new LinkedList<>();
            char[] inputChars = arg.toCharArray();
            for (char charDigit : inputChars) {
                Cell cell = digits.get(charDigit);
                if (cell != null) {
                    cells.add(cell);
                } else {
                    ignored.add(charDigit);
                }
            }

            System.out.println(arg + ":");
            SystemOutDisplay.getInstance().display(cells);
            if (!ignored.isEmpty()) {
                System.out.println("Ignored :" + ignored);
            }
        }
    }
}
