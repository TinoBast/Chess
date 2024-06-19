package gameRules;

import board.Field;
import pieces.Piece;

public class DiscoveredCheck {
	private Check check;

	public DiscoveredCheck() {
		this.check = new Check();
	}

	public boolean isDiscoveredCheck(Piece movingPiece, int newRow, int newCol, Field[][] fields) {
		Field originalField = fields[movingPiece.getRow()][movingPiece.getCol()];
		Field targetField = fields[newRow][newCol];

		// Temporarily move the piece to the new location
		Piece originalPiece = targetField.getPiece();
		originalField.setPiece(null);
		movingPiece.move(newRow, newCol);
		targetField.setPiece(movingPiece);

		// Check if the move exposes a check from any other piece
		boolean isDiscoveredCheck = check.isInCheck(movingPiece.getColor().equals("white") ? "black" : "white", fields);

		// Undo the move
		movingPiece.move(originalField.getRow(), originalField.getCol());
		originalField.setPiece(movingPiece);
		targetField.setPiece(originalPiece);

		return isDiscoveredCheck;
	}
}
