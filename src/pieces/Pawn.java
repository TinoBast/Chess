package pieces;

import board.Board;
import board.Field;
import javax.swing.JPanel;

public class Pawn extends Piece {
    private boolean isFirstMove;
    private Board board;
    private String whitePawnSvg;
    private String blackPawnSvg;

    public Pawn(String color, int row, int col, Board board, String circleMoveSvg, String edgeCaptureSvg, String whitePawnSvg, String blackPawnSvg) {
        super(color, row, col, circleMoveSvg, edgeCaptureSvg);
        this.board = board;
        this.isFirstMove = true;
        this.whitePawnSvg = whitePawnSvg;
        this.blackPawnSvg = blackPawnSvg;
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Field[][] board) {
        int rowDiff = newRow - getRow();
        int colDiff = newCol - getCol();

        int direction = getColor().equals("white") ? -1 : 1;

        if (colDiff == 0 && rowDiff == direction && !board[newRow][newCol].isOccupied()) {
            return true;
        }

        if (colDiff == 0 && rowDiff == 2 * direction && isFirstMove 
                && !board[newRow][newCol].isOccupied() 
                && !board[getRow() + direction][getCol()].isOccupied()) {
            return true;
        }

        if (Math.abs(colDiff) == 1 && rowDiff == direction 
                && board[newRow][newCol].isOccupied() 
                && !board[newRow][newCol].getPiece().getColor().equals(getColor())) {
            return true;
        }

        if (Math.abs(colDiff) == 1 && rowDiff == direction && !board[newRow][newCol].isOccupied()) {
            Field adjacentField = board[getRow()][newCol];
            if (adjacentField.isOccupied() && adjacentField.getPiece() instanceof Pawn) {
                Pawn adjacentPawn = (Pawn) adjacentField.getPiece();
                if (adjacentPawn.isFirstMove 
                        && !adjacentPawn.getColor().equals(getColor()) 
                        && Math.abs(adjacentPawn.getRow() - getRow()) == 2) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void move(int newRow, int newCol) {
        super.move(newRow, newCol);
        this.isFirstMove = false;

        if ((getColor().equals("white") && newRow == 0) || (getColor().equals("black") && newRow == 7)) {
            promote();
        }
    }

    @Override
    public boolean isValidCapture(int newRow, int newCol, Field[][] board) {
        return isValidMove(newRow, newCol, board);
    }

    private void promote() {
        board.promotePawn(this, board.getField(getRow(), getCol()));
    }

    @Override
    public void displayPieceIcon(JPanel panel) {
        if (getColor().equals("white")) {
            renderSVG(panel, whitePawnSvg);
        } else {
            renderSVG(panel, blackPawnSvg);
        }
    }
    
    

    @Override
    public String toString() {
        return "Pawn{" +
                "color='" + getColor() + '\'' +
                ", row=" + getRow() +
                ", col=" + getCol() +
                ", isFirstMove=" + isFirstMove +
                '}';
    }
}
