package pieces;

import board.Field;
import javax.swing.JButton;

public class Knight extends Piece {
    private String whiteKnightSvg;
    private String blackKnightSvg;

    public Knight(String color, int row, int col, String circleMoveSvg, String edgeCaptureSvg, String whiteKnightSvg, String blackKnightSvg) {
        super(color, row, col, circleMoveSvg, edgeCaptureSvg);
        this.whiteKnightSvg = whiteKnightSvg;
        this.blackKnightSvg = blackKnightSvg;
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = Math.abs(newRow - getRow());
        int colDiff = Math.abs(newCol - getCol());

        // Check if the move forms an L-shape (2 squares in one direction, 1 square in another)
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        // Check if the move is valid for a knight
        if (isValidMove(newRow, newCol, board)) {
            // Check if the destination square is occupied by an opponent's piece
            if (board[newRow][newCol].isOccupied()) {
                return !board[newRow][newCol].getPiece().getColor().equals(getColor());
            }
        }
        return false;
    }

    @Override
    public void displayPieceIcon(JButton button) {
        if (getColor().equals("white")) {
            renderSVG(button, whiteKnightSvg);
        } else {
            renderSVG(button, blackKnightSvg);
        }
    }

    @Override
    protected String getPieceSvgPath() {
        if (getColor().equals("white")) {
            return whiteKnightSvg;
        } else {
            return blackKnightSvg;
        }
    }

    @Override
    public String toString() {
        return "Knight{" +
                "color='" + getColor() + '\'' +
                ", row=" + getRow() +
                ", col=" + getCol() +
                '}';
    }
}
