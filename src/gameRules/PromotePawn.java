package gameRules;

import javax.swing.JOptionPane;
import javax.swing.JButton;

import board.Field;
import pieces.Bishop;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class PromotePawn {
    private Pawn promotedPawn;
    private JButton[][] squares; // Reference to board squares
    private Field[][] fields; // Reference to board fields

    public void promotePawn(Field field) {
        String[] options = {"Queen", "Rook", "Bishop", "Knight"};
        String choice = (String) JOptionPane.showInputDialog(
                null,
                "Promote pawn to:",
                "Pawn Promotion",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        Piece newPiece;
        switch (choice) {
            case "Rook":
                newPiece = new Rook(promotedPawn.getColor(), field.getRow(), field.getCol(),
                                    "circle_move.svg", "edge_capture.svg",
                                    "white_rook.svg", "black_rook.svg");
                break;
            case "Bishop":
                newPiece = new Bishop(promotedPawn.getColor(), field.getRow(), field.getCol(),
                                      "circle_move.svg", "edge_capture.svg",
                                      "white_bishop.svg", "black_bishop.svg");
                break;
            case "Knight":
                newPiece = new Knight(promotedPawn.getColor(), field.getRow(), field.getCol(),
                                      "circle_move.svg", "edge_capture.svg",
                                      "white_knight.svg", "black_knight.svg");
                break;
            case "Queen":
            default:
                newPiece = new Queen(promotedPawn.getColor(), field.getRow(), field.getCol(),
                                     "circle_move.svg", "edge_capture.svg",
                                     "white_queen.svg", "black_queen.svg");
                break;
        }

        field.setPiece(newPiece);
        updateSquare(field.getRow(), field.getCol());
    }

    private void updateSquare(int row, int col) {
        squares[row][col].setText(fields[row][col].getPiece().getClass().getSimpleName());
    }

    public void setPromotedPawn(Pawn promotedPawn) {
        this.promotedPawn = promotedPawn;
    }

    public void setSquares(JButton[][] squares) {
        this.squares = squares;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }
}

