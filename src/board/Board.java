package board;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import gameRules.TurnHandler;
import pieces.Knight;
import pieces.Piece;

public class Board extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int BOARD_SIZE = 8;
    private Field[][] fields = new Field[BOARD_SIZE][BOARD_SIZE];
    private Field selectedField = null;
    private TurnHandler turnHandler;

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

    public void initializeBoard(JButton[][] squares, TurnHandler turnHandler) {
        this.turnHandler = turnHandler;

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
        String circleMoveUnicode = "\u25CB";  // Unicode for an empty circle
        String edgeCaptureUnicode = "\u25CF"; // Unicode for a filled circle

        fields[0][1].setPiece(new Knight("black", 0, 1, circleMoveUnicode, edgeCaptureUnicode));
        fields[0][6].setPiece(new Knight("black", 0, 6, circleMoveUnicode, edgeCaptureUnicode));
        fields[7][1].setPiece(new Knight("white", 7, 1, circleMoveUnicode, edgeCaptureUnicode));
        fields[7][6].setPiece(new Knight("white", 7, 6, circleMoveUnicode, edgeCaptureUnicode));
    }

    private void updatePieceGUI(JButton[][] squares) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = fields[row][col].getPiece();
                if (piece != null) {
                    piece.displayPieceIcon(squares[row][col]);
                } else {
                    squares[row][col].setText(null);
                    squares[row][col].removeAll();
                    squares[row][col].repaint();
                }
            }
        }
    }

    private void clearPossibleMoves(JButton[][] squares) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (fields[row][col].isHighlighted()) {
                    fields[row][col].setHighlighted(false);
                    squares[row][col].setText(null);
                    squares[row][col].removeAll();
                    squares[row][col].repaint();
                }
            }
        }
    }

    private void highlightPossibleMoves(JButton[][] squares, Field selectedField) {
        Piece piece = selectedField.getPiece();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (piece.isValidMove(row, col, fields)) {
                    fields[row][col].setHighlighted(true);
                    piece.displayMoveIcon(squares[row][col]);
                } else if (piece.isValidCapture(row, col, fields)) {
                    fields[row][col].setHighlighted(true);
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
            Field clickedField = fields[row][col];
            Piece piece = clickedField.getPiece();

            if (selectedField == null && piece != null && piece.getColor().equals(turnHandler.getCurrentPlayer())) {
                selectedField = clickedField;
                // Highlight possible moves
                highlightPossibleMoves(squares, selectedField);
            } else if (selectedField != null) {
                // Handle move or capture
                Piece selectedPiece = selectedField.getPiece();
                if (selectedPiece != null && (selectedPiece.isValidMove(row, col, fields) || selectedPiece.isValidCapture(row, col, fields))) {
                    // Move the piece
                    fields[selectedField.getRow()][selectedField.getCol()].setPiece(null);
                    fields[clickedField.getRow()][clickedField.getCol()].setPiece(selectedPiece);
                    selectedPiece.move(clickedField.getRow(), clickedField.getCol());

                    // Debug output
                    System.out.println("Moved " + selectedPiece + " to (" + clickedField.getRow() + ", " + clickedField.getCol() + ")");

                    // Update GUI for the moved piece
                    updatePieceGUI(squares);
                    
                    // Clear selection and highlighted moves
                    selectedField = null;
                    clearPossibleMoves(squares);
                    
                    // Switch turn
                    turnHandler.switchTurn();
                } else {
                    // Clear selection if the move is invalid
                    selectedField = null;
                    clearPossibleMoves(squares);
                }
            }
        }
    }
}
