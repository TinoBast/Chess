package gameRules;

import board.Field;
import pieces.Rook;
import pieces.King;
import pieces.Piece;

public class Castle {
    public static boolean isValidCastling(int kingCol, int kingRow, int rookCol, Field[][] board) {
        // Rook must not have moved
        Piece kingPiece = board[kingRow][kingCol].getPiece();
        Piece rookPiece = board[kingRow][rookCol].getPiece();
        if (!(kingPiece instanceof King) || !(rookPiece instanceof Rook)) {
            return false;
        }
        if (((King) kingPiece).hasMoved() || ((Rook) rookPiece).hasMoved()) {
            return false;
        }

        // There should be no pieces between the king and the rook
        int minCol = Math.min(kingCol, rookCol);
        int maxCol = Math.max(kingCol, rookCol);
        for (int col = minCol + 1; col < maxCol; col++) {
            if (board[kingRow][col].isOccupied()) {
                return false;
            }
        }

        // The king cannot be in check, nor can it pass through a square that is attacked by an opponent's piece.
        // (This part is not implemented here and depends on the broader game logic)

        // The king cannot move into check
        // (This part is not implemented here and depends on the broader game logic)

        return true;
    }
}
