package board;

import pieces.Piece;

public class Field {
	private int row;
	private int col;
	private Piece piece;
	private boolean highlighted;

	public Field(int row, int col) {
		this.row = row;
		this.col = col;
		this.piece = null; // Initially, the field does not have a piece
		this.highlighted = false; // Initially, the field is not highlighted
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isOccupied() {
		return piece != null;
	}

	public boolean isOccupiedByOpponent(Piece otherPiece) {
		return isOccupied() && !piece.getColor().equals(otherPiece.getColor());
	}

	public boolean isOccupiedBySameColor(Piece otherPiece) {
		return isOccupied() && piece.getColor().equals(otherPiece.getColor());
	}

	public boolean isOccupiedByDifferentColor(Piece otherPiece) {
		return isOccupied() && !piece.getColor().equals(otherPiece.getColor());
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	@Override
	public String toString() {
		return "Field{" + "row=" + row + ", col=" + col + ", piece=" + piece + ", highlighted=" + highlighted + '}';
	}
}
