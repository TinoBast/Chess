package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gameRules.PromotePawn;
import gameRules.TurnHandler;
import pieces.Knight;
import pieces.Piece;

public class Board extends JPanel {
    private static final int BOARD_SIZE = 8;
    private Field[][] fields = new Field[BOARD_SIZE][BOARD_SIZE];
    private Field selectedField = null;
    private TurnHandler turnHandler;
    private PromotePawn promoteHandler;

    public Board() {
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        initializeFields();
    }

    private void initializeFields() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                fields[row][col] = new Field(row, col);
            }
        }
    }

    public void initializeBoard(JButton[][] squares, PromotePawn promoteHandler, TurnHandler turnHandler) {
        this.turnHandler = turnHandler;
        this.promoteHandler = promoteHandler;

        // Initialize pieces on the board
        initializeKnights();

        // Initialize GUI representation of the pieces
        updatePieceGUI(squares);

        // Add action listeners to the buttons
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col].addActionListener(new SquareClickListener(row, col, squares));
            }
        }
    }

    private void initializeKnights() {
        // SVG file paths for Knights
        String circleMoveSvg = "circle_move.svg";
        String edgeCaptureSvg = "edge_capture.svg";
        String whiteKnightSvg = "white_knight.svg";
        String blackKnightSvg = "black_knight.svg";

        fields[0][1].setPiece(new Knight("black", 0, 1, circleMoveSvg, edgeCaptureSvg, whiteKnightSvg, blackKnightSvg));
        fields[0][6].setPiece(new Knight("black", 0, 6, circleMoveSvg, edgeCaptureSvg, whiteKnightSvg, blackKnightSvg));
        fields[7][1].setPiece(new Knight("white", 7, 1, circleMoveSvg, edgeCaptureSvg, whiteKnightSvg, blackKnightSvg));
        fields[7][6].setPiece(new Knight("white", 7, 6, circleMoveSvg, edgeCaptureSvg, whiteKnightSvg, blackKnightSvg));
    }

    private void updatePieceGUI(JButton[][] squares) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece != null) {
                    piece.displayPieceIcon(squares[row][col]);
                } else {
                    // Clear the button if no piece is present
                    squares[row][col].setIcon(null);
                    squares[row][col].setText("");
                }
            }
        }
    }

    private void showPossibleMovesAndCaptures(Piece piece, JButton[][] squares, Field[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (piece.isValidMove(row, col, board)) {
                    piece.displayMoveIcon(squares[row][col]);
                } else if (piece.isValidCapture(row, col, board)) {
                    piece.displayCaptureIcon(squares[row][col]);
                }
            }
        }
    }

    private class SquareClickListener implements ActionListener {
        private int row;
        private int col;
        private JButton[][] squares;

        public SquareClickListener(int row, int col, JButton[][] squares) {
            this.row = row;
            this.col = col;
            this.squares = squares;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Field field = fields[row][col];
            if (selectedField != null) {
                if (selectedField.getPiece().isValidMove(row, col, fields)) {
                    Piece movingPiece = selectedField.getPiece();
                    selectedField.setPiece(null);
                    field.setPiece(movingPiece);
                    movingPiece.move(row, col);

                    /* // Check for promotion
                    if (movingPiece instanceof Pawn) {
                        promoteHandler.setPromotedPawn((Pawn) movingPiece);
                        promoteHandler.promotePawn(field);
                    }
                    */ 

                    turnHandler.switchTurn();
                    selectedField = null;

                    // Update the GUI
                    updatePieceGUI(squares);
                } else {
                    System.out.println("Invalid move. Try again.");
                    selectedField = null;
                }
            } else if (field.isOccupied() && field.getPiece().getColor().equals(turnHandler.getCurrentPlayer())) {
                selectedField = field;
                showPossibleMovesAndCaptures(field.getPiece(), squares, fields);
            }
        }
    }
}
