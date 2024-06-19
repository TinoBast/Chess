package pieces;

import board.Field;

public class Knight extends Piece {
    private static final String WHITE_KNIGHT_UNICODE = "\u2658"; // Unicode for white knight
    private static final String BLACK_KNIGHT_UNICODE = "\u265E"; // Unicode for black knight

    public Knight(String color, int row, int col, String circleMoveUnicode, String edgeCaptureUnicode) {
        super(color, row, col, circleMoveUnicode, edgeCaptureUnicode, 
              color.equals("white") ? WHITE_KNIGHT_UNICODE : BLACK_KNIGHT_UNICODE);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = Math.abs(newRow - getRow());
        int colDiff = Math.abs(newCol - getCol());
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        if (isValidMove(newRow, newCol, board)) {
            if (board[newRow][newCol].isOccupied()) {
                return !board[newRow][newCol].getPiece().getColor().equals(getColor());
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Knight{" + "color='" + getColor() + '\'' + ", row=" + getRow() + ", col=" + getCol() + '}';
    }
}
