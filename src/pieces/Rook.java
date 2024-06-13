package pieces;

import board.Field;
import javax.swing.JPanel;


public class Rook extends Piece {
    private boolean hasMoved;
    private String whiteRookSvg;
    private String blackRookSvg;

    public Rook(String color, int row, int col, String circleMoveSvg, String edgeCaptureSvg, String whiteRookSvg, String blackRookSvg) {
        super(color, row, col, circleMoveSvg, edgeCaptureSvg);
        this.hasMoved = false;
        this.whiteRookSvg = whiteRookSvg;
        this.blackRookSvg = blackRookSvg;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = newRow - getRow();
        int colDiff = newCol - getCol();

        // Check for valid horizontal or vertical move
        if (rowDiff != 0 && colDiff != 0) {
            return false; // Rook can only move horizontally or vertically
        }

        // Determine the direction of the move
        int rowDirection = Integer.signum(rowDiff);
        int colDirection = Integer.signum(colDiff);

        // Check each square along the path for obstacles
        int currentRow = getRow() + rowDirection;
        int currentCol = getCol() + colDirection;
        while (currentRow != newRow || currentCol != newCol) {
            // Boundary check
            if (!isValidPosition(currentRow, currentCol, board.length, board[0].length)) {
                return false;
            }
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
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        // Rook captures similarly to how it moves, so we can reuse its move logic
        return isValidMove(newRow, newCol, board);
    }

    @Override
    public void move(int newRow, int newCol) {
        super.move(newRow, newCol);
        this.hasMoved = true;
    }

    @Override
    public void displayPieceIcon(JPanel panel) {
        if (getColor().equals("white")) {
            renderSVG(panel, whiteRookSvg);
        } else {
            renderSVG(panel, blackRookSvg);
        }
    }

    @Override
    public String toString() {
        return "Rook{" +
                "color='" + getColor() + '\'' +
                ", row=" + getRow() +
                ", col=" + getCol() +
                '}';
    }

    private boolean isValidPosition(int row, int col, int numRows, int numCols) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
}
