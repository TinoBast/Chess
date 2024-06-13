package pieces;

import board.Field;
import javax.swing.JPanel;

public class Bishop extends Piece {
    private String whiteBishopSvg;
    private String blackBishopSvg;

    public Bishop(String color, int row, int col, String circleMoveSvg, String edgeCaptureSvg, String whiteBishopSvg, String blackBishopSvg) {
        super(color, row, col, circleMoveSvg, edgeCaptureSvg);
        this.whiteBishopSvg = whiteBishopSvg;
        this.blackBishopSvg = blackBishopSvg;
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = Math.abs(newRow - getRow());
        int colDiff = Math.abs(newCol - getCol());

        // Bishops move diagonally, so row and column difference must be the same
        if (rowDiff != colDiff) {
            return false;
        }

        // Determine the direction of the move
        int rowDirection = Integer.signum(newRow - getRow());
        int colDirection = Integer.signum(newCol - getCol());

        // Check each square along the path for obstacles
        int currentRow = getRow() + rowDirection;
        int currentCol = getCol() + colDirection;
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
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        // Bishop captures similarly to how it moves, so we can reuse its move logic
        return isValidMove(newRow, newCol, board);
    }

    @Override
    public void displayPieceIcon(JPanel panel) {
        if (getColor().equals("white")) {
            renderSVG(panel, whiteBishopSvg);
        } else {
            renderSVG(panel, blackBishopSvg);
        }
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "color='" + getColor() + '\'' +
                ", row=" + getRow() +
                ", col=" + getCol() +
                '}';
    }
}
