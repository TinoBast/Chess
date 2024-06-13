package pieces;

import board.Field;
import javax.swing.JPanel;

public class Queen extends Piece {
    private String whiteQueenSvg;
    private String blackQueenSvg;

    public Queen(String color, int row, int col, String circleMoveSvg, String edgeCaptureSvg, String whiteQueenSvg, String blackQueenSvg) {
        super(color, row, col, circleMoveSvg, edgeCaptureSvg);

        this.whiteQueenSvg = whiteQueenSvg;
        this.blackQueenSvg = blackQueenSvg;
       // displayPieceIcon(); // Display initial piece icon
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = newRow - getRow();
        int colDiff = newCol - getCol();

        // Check for valid diagonal, horizontal, or vertical move
        if (Math.abs(rowDiff) == Math.abs(colDiff) || rowDiff == 0 || colDiff == 0) {
            return isValidPath(newRow, newCol, board);
        }

        return false;
    }

    @Override
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        // The queen captures similarly to how it moves
        return isValidMove(newRow, newCol, board);
    }

    private boolean isValidPath(int newRow, int newCol, Field[][] board) {
        int rowDiff = newRow - getRow();
        int colDiff = newCol - getCol();

        int rowDirection = Integer.signum(rowDiff);
        int colDirection = Integer.signum(colDiff);

        int currentRow = getRow() + rowDirection;
        int currentCol = getCol() + colDirection;

        // Check each square along the path for obstacles
        while (currentRow != newRow || currentCol != newCol) {
            if (board[currentRow][currentCol].isOccupied()) {
                return false; // There is a piece in the way
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // Check the final position
        if (board[newRow][newCol].isOccupied()) {
            // Ensure it's not a piece of the same color
            if (board[newRow][newCol].getPiece().getColor().equals(getColor())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void displayPieceIcon(JPanel panel) {
        if (getColor().equals("white")) {
            renderSVG(panel, whiteQueenSvg);
        } else {
            renderSVG(panel, blackQueenSvg);
        }
    }
/*
    private void displayPieceIcon() {
        JPanel panel = new JPanel(); // Assuming a JPanel is used for displaying icons
        displayPieceIcon(panel);
    }

    @Override
    public void displayMoveIcon(JPanel panel) {
        renderSVG(panel, circleMoveSvg);
    }

    @Override
    public void displayCaptureIcon(JPanel panel) {
        renderSVG(panel, edgeCaptureSvg);
    }

    private void renderSVG(JPanel panel, String svgFilePath) {
        // Implement SVG rendering logic here
        // Example code to load SVG into a panel (you may need to adjust based on your SVG handling library)
        // JSVGCanvas svgCanvas = new JSVGCanvas();
        // svgCanvas.setURI(getClass().getResource(svgFilePath).toString());
        // panel.removeAll();
        // panel.setLayout(new BorderLayout());
        // panel.add(svgCanvas, BorderLayout.CENTER);
        // panel.validate();
        // Replace this with your actual SVG rendering logic
        System.out.println("Rendering SVG: " + svgFilePath);
    }
*/
    @Override
    public String toString() {
        return "Queen{" +
                "color='" + getColor() + '\'' +
                ", row=" + getRow() +
                ", col=" + getCol() +
                '}';
    }
}
