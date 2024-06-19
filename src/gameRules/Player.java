package gameRules;

import java.util.ArrayList;
import java.util.List;

import pieces.Piece;

public class Player {
	private String name;
	private String color;
	private List<Piece> capturedPieces;
	private List<String> moves;

	public Player(String name, String color) {
		this.name = name;
		this.color = color;
		this.capturedPieces = new ArrayList<>();
		this.moves = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public List<Piece> getCapturedPieces() {
		return new ArrayList<>(capturedPieces); // return a copy for immutability
	}

	public List<String> getMoves() {
		return new ArrayList<>(moves); // return a copy for immutability
	}

	public void capturePiece(Piece piece) {
		capturedPieces.add(piece);
	}

	public void addMove(String move) {
		moves.add(move);
	}

	@Override
	public String toString() {
		return "Player{" + "name='" + name + '\'' + ", color='" + color + '\'' + ", capturedPieces=" + capturedPieces
				+ ", moves=" + moves + '}';
	}
}
