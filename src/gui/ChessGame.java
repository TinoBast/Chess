package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import board.Board;
import gameRules.TurnHandler;

public class ChessGame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int BOARD_SIZE = 8;
    private JButton[][] squares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private Board board;
    private TurnHandler turnHandler;

    public ChessGame() {
        setTitle("Chess Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize status label
        JLabel statusLabel = new JLabel("White's Turn");
        add(createStatusPanel(statusLabel), BorderLayout.SOUTH);

        // Initialize board
        board = new Board();
        turnHandler = new TurnHandler(statusLabel);

        // Initialize squares and board
        initializeSquares();
        board.initializeBoard(squares, turnHandler);

        // Add the board panel to the center
        add(board, BorderLayout.CENTER);

        // Pack the frame and set minimum size
        pack();
        setMinimumSize(new Dimension(400, 400));

        // Add component listener for resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeSquares();
            }
        });
    }

    private void initializeSquares() {
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col] = new JButton();
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(240, 217, 181)); // Light square
                } else {
                    squares[row][col].setBackground(new Color(181, 136, 99)); // Dark square
                }
                boardPanel.add(squares[row][col]);
            }
        }
        // Remove the existing panel in the board and add the new one
        board.removeAll();
        board.setLayout(new BorderLayout());
        board.add(boardPanel, BorderLayout.CENTER);
    }

    private JPanel createStatusPanel(JLabel statusLabel) {
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        return statusPanel;
    }

    private void resizeSquares() {
        int width = getWidth() / BOARD_SIZE;
        int height = getHeight() / BOARD_SIZE;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                squares[row][col].setPreferredSize(new Dimension(width, height));
                squares[row][col].revalidate();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGame chessGame = new ChessGame();
            chessGame.setVisible(true);
        });
    }
}
