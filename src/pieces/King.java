package pieces;

import board.Field;
import gameRules.Castle;

import javax.swing.JPanel;

public class King extends Piece {
    private boolean hasMoved;
    private String whiteKingSvg;
    private String blackKingSvg;

    public King(String color, int row, int col, String circleMoveSvg, String edgeCaptureSvg, String whiteKingSvg, String blackKingSvg) {
        super(color, row, col, circleMoveSvg, edgeCaptureSvg);
        this.hasMoved = false;
        this.whiteKingSvg = whiteKingSvg;
        this.blackKingSvg = blackKingSvg;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = Math.abs(newRow - getRow());
        int colDiff = Math.abs(newCol - getCol());

        // Check for valid move (one square in any direction)
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if the destination square is not attacked by opponent pieces
            if (!isSquareAttacked(newRow, newCol, board, getColor())) {
                // Check if the destination square is not occupied by own piece
                if (!board[newRow][newCol].isOccupied() || !board[newRow][newCol].getPiece().getColor().equals(getColor())) {
                    return true;
                }
            }
        }

        // Check for castling
        if (isValidCastling(newCol, board)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        // King captures similarly to how it moves, so we can reuse its move logic
        return isValidMove(newRow, newCol, board);
    }

    public boolean isValidCastling(int rookCol, Field[][] board) {
        int kingRow = getRow();
        int kingCol = getCol();

        // Check if the king and rook are eligible for castling
        if (!hasMoved && !board[kingRow][kingCol].isOccupied()) {
            if (Castle.isValidCastling(kingRow, kingCol, rookCol, board)) {
                return true;
            }
        }

        return false;
    }

    private boolean isSquareAttacked(int row, int col, Field[][] board, String attackerColor) {
        // Check if any opponent piece can attack the given square
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece != null && !piece.getColor().equals(attackerColor)) {
                    if (piece.isValidMove(row, col, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void displayPieceIcon(JPanel panel) {
        if (getColor().equals("white")) {
            renderSVG(panel, whiteKingSvg);
        } else {
            renderSVG(panel, blackKingSvg);
        }
    }



    @Override
    public String toString() {
        return "King{" +
                "color='" + getColor() + '\'' +
                ", row=" + getRow() +
                ", col=" + getCol() +
                '}';
    }
}
