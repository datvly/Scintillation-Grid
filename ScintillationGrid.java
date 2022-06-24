import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

//Draw Scintillation grid illusion 
public class ScintillationGrid {

    // Main method that creates the DrawingPanel with scintillation grids.
    // Restricted to chapters 1 - 3 of Building Java Programs
    public static void main(String[] args) {
        //finding the length of the black squares

        final int WIDTH = 950;
        final int HEIGHT = 650;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        dp.setBackground(Color.CYAN);
        Graphics g = dp.getGraphics();

        scintillationGrid(g, 0, 0, 75,3, 16 );
        scintillationGrid(g, 400, 50, 50,6, 12 );
        scintillationGrid(g, 50, 400, 100,1, 22 );
        scintillationGrid(g, 500, 500, 15,7, 4 );

        // Saves the grid
        saveDrawingPanel(dp, "grid.png");
    }

    //Draw the entire of the Scintillation Grid
    //With x and y being the upper left corner
    public static void scintillationGrid(Graphics g, int x, int y, int sizeSmallSquares,
                                         int amountLines, int thickLine) {
        //Find the length and width of the black squares
        int allSmallSquares = sizeSmallSquares * (amountLines +1);
        int allLines = amountLines * thickLine;
        int sizeLargeSquare = allSmallSquares + allLines;

        drawBlackBackground(g, x, y, sizeLargeSquare);
        drawGrid(g, x, y, sizeLargeSquare, sizeSmallSquares, amountLines, thickLine);
        drawWhiteDots(g, x, y, sizeSmallSquares, amountLines, thickLine);
    }

    //Print the black squares
    public static void drawBlackBackground(Graphics g, int x, int y, int sizeLargeSquare) {
        //Find the length and width of the square
        g.setColor(Color.BLACK);
        g.fillRect(x, y, sizeLargeSquare, sizeLargeSquare);
    }

    //Print the grid
    public static void drawGrid (Graphics g, int x, int y, int sizeLargeSquare, int sizeSmallSquares,
                                 int amountLines, int thickLine) {
        g.setColor(Color.GRAY);
        int yInitialHorizontalLine = y + sizeSmallSquares;
        int xInitialVerticalLine = x + sizeSmallSquares;

        for (int i = 0; i < amountLines; i++) { //amount of line
            g.fillRect(x, yInitialHorizontalLine, sizeLargeSquare, thickLine); //horizontal line
            yInitialHorizontalLine += sizeSmallSquares + thickLine;
            g.fillRect(xInitialVerticalLine, y, thickLine, sizeLargeSquare); //vertical line
            xInitialVerticalLine += sizeSmallSquares + thickLine;
        }

    }

    //Printing the dots
    public static void drawWhiteDots (Graphics g, int x, int y, int sizeSmallSquares, int amountLines, int thickLine) {
        g.setColor(Color.WHITE);
        final int PIXEL = 4;
        final double PERCENT_BIGGER = .4;

        //Centering the Circle over the line cross
        int movement = (int) Math.max (PIXEL, thickLine * PERCENT_BIGGER);
        int sizeCircle = movement + thickLine;
        int extraSize = movement / 2;
        int xInitialDot = (x + sizeSmallSquares) - extraSize;
        int yInitialDot = (y + sizeSmallSquares) - extraSize;

        //printing the dot itself
        for(int i = 0; i < amountLines; i++) {
            for(int j = 0; j < amountLines; j++) {
                g.fillOval(xInitialDot, yInitialDot, sizeCircle, sizeCircle);
                xInitialDot += sizeSmallSquares + thickLine;
            }
            xInitialDot = (x + sizeSmallSquares) - extraSize;
            yInitialDot += sizeSmallSquares + thickLine;
        }

    }

    // Save the current drawing panel to the given file. 
    public static void saveDrawingPanel(DrawingPanel dp, String fileName) {
        try {
            dp.save(fileName);
        } catch (IOException e) {
            System.out.println("Unable to save DrawingPanel");
        }
    }
}

