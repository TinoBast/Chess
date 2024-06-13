package board;

import pieces.Piece;

public class Field {
    private int row;
    private int col;
    private Piece piece;

    public Field(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null; // Initially, the field does not have a piece
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

    @Override
    public String toString() {
        return "Field{" +
                "row=" + row +
                ", col=" + col +
                ", piece=" + piece +
                '}';
    }
}
