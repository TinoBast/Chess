package gameRules;

public class Turn {
	private String currentPlayer;

	public Turn() {
		this.currentPlayer = "white";
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void switchTurn() {
		if (currentPlayer.equals("white")) {
			currentPlayer = "black";
		} else {
			currentPlayer = "white";
		}
	}

	public boolean isPlayerTurn(String color) {
		return currentPlayer.equals(color);
	}
}
