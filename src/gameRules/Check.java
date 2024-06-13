package gameRules;

import board.Field;
import pieces.King;
import pieces.Piece;
import java.util.List;

public class Check {

    // Method to find the king of a specific color on the board
    private Field findKing(String color, Field[][] fields) {
        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields[row].length; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return fields[row][col];
                }
            }
        }
        return null;
    }

    // Method to check if the king is in check
    public boolean isInCheck(String color, Field[][] fields) {
        Field kingField = findKing(color, fields);
        if (kingField == null) {
            return false; // King not found, should not happen in a valid game
        }
        
        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields[row].length; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece != null && !piece.getColor().equals(color)) {
                    if (piece.isValidMove(kingField.getRow(), kingField.getCol(), fields)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

