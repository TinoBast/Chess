package pieces;

import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;

import board.Field;

public abstract class Piece {
    private String color;  // "white" or "black"
    private int row;
    private int col;

    // SVG file paths
    private String circleMoveSvg;
    private String edgeCaptureSvg;

    public Piece(String color, int row, int col, String circleMoveSvg, String edgeCaptureSvg) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.circleMoveSvg = circleMoveSvg;
        this.edgeCaptureSvg = edgeCaptureSvg;
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
        renderSVG(button, circleMoveSvg);
    }

    public void displayCaptureIcon(JButton button) {
        renderSVG(button, edgeCaptureSvg);
    }

    public void displayPieceIcon(JButton button) {
        renderSVG(button, getPieceSvgPath());
    }

    protected abstract String getPieceSvgPath();

    protected void renderSVG(JComponent component, String svgFilePath) {
        JSVGCanvas svgCanvas = new JSVGCanvas();
        svgCanvas.setURI(getClass().getResource("/svg/" + svgFilePath).toString());
        component.removeAll();
        component.setLayout(new BorderLayout());
        component.add(svgCanvas, BorderLayout.CENTER);
        component.validate();
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
