package gui;

import board.Board;
import gameRules.PromotePawn;
import gameRules.TurnHandler;

import javax.swing.*;
import java.awt.*;

public class ChessGame extends JFrame {
    private static final long serialVersionUID = 1L; // Added serialVersionUID
    private static final int BOARD_SIZE = 8;
    private JButton[][] squares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private Board board;
    private TurnHandler turnHandler;
    private PromotePawn promoteHandler;

    public ChessGame() {
        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize status label
        JLabel statusLabel = new JLabel("White's Turn");
        add(createStatusPanel(statusLabel), BorderLayout.SOUTH);

        // Initialize game components
        board = new Board();
        turnHandler = new TurnHandler(statusLabel);
        promoteHandler = new PromotePawn();

        add(board, BorderLayout.CENTER);

        // Initialize the board
        initializeSquares();
        board.initializeBoard(squares, promoteHandler, turnHandler);
    }

    private void initializeSquares() {
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col] = new JButton();
                squares[row][col].setPreferredSize(new Dimension(80, 80));
                boardPanel.add(squares[row][col]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private JPanel createStatusPanel(JLabel statusLabel) {
        JPanel panel = new JPanel();
        panel.add(statusLabel);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGame game = new ChessGame();
            game.setVisible(true);
        });
    }
}
