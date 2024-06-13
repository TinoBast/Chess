package gameRules;

import board.Field;
import pieces.King;
import pieces.Piece;

public class Checkmate {
    private Check check;

    public Checkmate() {
        this.check = new Check();
    }

    public boolean isCheckmate(String color, Field[][] fields) {
        if (!check.isInCheck(color, fields)) {
            return false;
        }

        for (int row = 0; row < fields.length; row++) {
            for (int col = 0; col < fields[row].length; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece != null && piece.getColor().equals(color)) {
                    for (int newRow = 0; newRow < fields.length; newRow++) {
                        for (int newCol = 0; newCol < fields[row].length; newCol++) {
                            if (piece.isValidMove(newRow, newCol, fields)) {
                                // Temporarily move piece
                                Field targetField = fields[newRow][newCol];
                                Piece originalPiece = targetField.getPiece();
                                fields[piece.getRow()][piece.getCol()].setPiece(null);
                                piece.move(newRow, newCol);
                                targetField.setPiece(piece);

                                boolean stillInCheck = check.isInCheck(color, fields);

                                // Undo move
                                piece.move(row, col);
                                fields[row][col].setPiece(piece);
                                targetField.setPiece(originalPiece);

                                if (!stillInCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
