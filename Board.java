package five;

import java.util.ArrayList;

public class Board {

	private Tile[][] board;
	private ArrayList<int[]> validSpots = new ArrayList<>();
	private Turn turn;
	private boolean gameWon;

	public Board() {
		board = new Tile[9][9];
		turn = Turn.WHITE;
		int[] validStart = {4, 4};
		validSpots.add(validStart);
		gameWon = false;
	}

	public void reset(){
		board = new Tile[9][9];
		turn = Turn.WHITE;
		validSpots = new ArrayList<>();
		int[] validStart = {4, 4};
		validSpots.add(validStart);
		gameWon = false;
	}
	public boolean placeTile(int x, int y) {
		int[] coor = {x, y};
		boolean valid = false;
		for (int[] arr : validSpots) {
			if (arr[0] == coor[0] && arr[1] == coor[1]) valid = true;
		}
		if (board[x][y] != null) valid = false;
		if (valid) {
			board[x][y] = new Tile(turn, x, y);
			if (gameOver(x, y)) gameWon = true;
			setValidSpots(x, y);
			nextTurn();
			return true;
		} return false;
	}

	public Turn turn(){
		return turn;
	}

	public boolean gameWon() {
		return gameWon;
	}

	private void setValidSpots(int x, int y) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int[] addCoor = {x + i, y + j};
				validSpots.add(addCoor);
			}
		}
	}

	private void nextTurn() {
		if (turn == Turn.WHITE) turn = Turn.BLACK;
		else turn = Turn.WHITE;
	}

	private boolean gameOver(int x, int y) {
		// Checks Y
		int inARow = 1;
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x][y+i] != null) {
					if (board[x][y+i].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x][y-i] != null) {
					if (board[x][y-i].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		// Checks X
		inARow = 1;
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x+i][y] != null) {
					if (board[x+i][y].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x-i][y] != null) {
					if (board[x-i][y].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		// Checks X,Y
		inARow = 1;
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x+i][y+i] != null) {
					if (board[x+i][y+i].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x-i][y-i] != null) {
					if (board[x-i][y-i].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		// Checks X,-Y
		inARow = 1;
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x-i][y+i] != null) {
					if (board[x-i][y+i].color() == turn) inARow++;
					if (inARow == 5) return true;
				} else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		for (int i = 1; i < 5; i++) {
			try {
				if (board[x+i][y-i] != null) {
					if (board[x+i][y-i].color() == turn) inARow++;
					if (inARow == 5) return true;
				}  else break;
			} catch (ArrayIndexOutOfBoundsException e) {break;}
		}
		return false;		
	}

}
