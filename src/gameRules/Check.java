package gameRules;

import board.Field;
import pieces.Piece;

public class Check {

	// Method to find the king of a specific color on the board
	private Field findKing(String color, Field[][] fields) {
		for (Field[] field : fields) {
			for (Field element : field) {
				Piece piece = element.getPiece();
				if (piece instanceof King && piece.getColor().equals(color)) {
					return element;
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

		for (Field[] field : fields) {
			for (Field element : field) {
				Piece piece = element.getPiece();
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
