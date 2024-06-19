package pieces;

import javax.swing.JButton;
import java.awt.Font;

import board.Field;

public abstract class Piece {
    private String color;
    private int row;
    private int col;
    private String circleMoveUnicode;
    private String edgeCaptureUnicode;
    private String pieceUnicode;

    public Piece(String color, int row, int col, String circleMoveUnicode, String edgeCaptureUnicode, String pieceUnicode) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.circleMoveUnicode = circleMoveUnicode;
        this.edgeCaptureUnicode = edgeCaptureUnicode;
        this.pieceUnicode = pieceUnicode;
    }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }

    public abstract boolean isValidMove(int newRow, int newCol, Field[][] board);

    public abstract boolean isValidCapture(int newRow, int newCol, Field[][] board);

    public void displayMoveIcon(JButton button) {
        button.setText(circleMoveUnicode);
    }

    public void displayCaptureIcon(JButton button) {
        button.setText(edgeCaptureUnicode);
    }

    public void displayPieceIcon(JButton button) {
        button.setText(pieceUnicode);
        button.setFont(new Font("Arial", Font.PLAIN, button.getHeight() - 10));
    }

    protected String getPieceUnicode() {
        return pieceUnicode;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "color='" + color + '\'' +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
