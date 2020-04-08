package data_structure.ac_recursion;

public class AB_EnglishRuler {

    public static void main(String[] args) {
        drawRuler(2, 4);
        System.out.println("============================================================");
        drawRuler(1, 5);
        System.out.println("============================================================");
        drawRuler(3, 3);

    }


    /**
     * Manages the construction of the entire ruler.
     * @param nInches  Total number of inches in the ruler
     * @param majorLength The major tick length
     */
    private static void drawRuler(int nInches, int majorLength) {
        // Draw inch 0 line and label
        drawLine(majorLength, 0);

        for (int i = 1; i <= nInches; i++) {

            // Draw interior ticks for inch
            drawInterval(majorLength - 1);

            // Draw inch `i` line and label
            drawLine(majorLength, i);
        }
    }


    /**
     * Draws the sequence of minor ticks within some interval, based upon the length of the interval’s central tick.
     * In general, an interval with a central tick length L ≥ 1 is composed of:
        • An interval with a central tick length L − 1
        • A single tick of length L
        • An interval with a central tick length L − 1
     * With a base case when L = 0 that draws nothing.
     * For L ≥ 1, the first and last steps are performed by recursively calling drawInterval(L − 1).
     * The middle step is performed by calling method drawLine(L).
     * @param centralLength The length of the interval’s central tick
     */
    private static void drawInterval(int centralLength) {
        // Otherwise, do nothing
        if (centralLength >= 1) {

            // Recursively draw top interval
            drawInterval(centralLength - 1);

            // Draw center tick line (without label)
            drawLine(centralLength);

            // Recursively draw bottom interval
            drawInterval(centralLength - 1);
        }
    }


    /**
     * The utility method, drawLine, draws a single tick with a specified number of dashes (and an optional integer
       label that is printed to the right of the tick).
     * @param tickLength The specified number of dashes
     * @param tickLabel An optional integer label that is printed to the right of the tick
     */
    private static void drawLine(int tickLength, int tickLabel) {
        for (int i = 0; i < tickLength; i++) {
            System.out.print("-");
        }
        if (tickLabel >= 0) {
            System.out.print(" " + tickLabel);
        }
        System.out.print("\n");
    }


    /**
     * Draws a line with the given tick length (but no label).
     * @param tickLength The specified number of dashes
     */
    private static void drawLine(int tickLength) {
        drawLine(tickLength, -1);
    }
}
