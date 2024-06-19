package gameRules;

import javax.swing.JLabel;

public class TurnHandler {
	private Turn turn;
	private JLabel statusLabel;

	public TurnHandler(JLabel statusLabel) {
		this.turn = new Turn();
		this.statusLabel = statusLabel;
		updateStatusLabel();
	}

	public String getCurrentPlayer() {
		return turn.getCurrentPlayer();
	}

	public void nextTurn() {
		switchTurn();
	}

	public void switchTurn() {
		turn.switchTurn();
		updateStatusLabel();
	}

	public boolean isTurn(String color) {
		return turn.isPlayerTurn(color);
	}

	private void updateStatusLabel() {
		statusLabel.setText(turn.getCurrentPlayer() + "'s Turn");
	}
}
