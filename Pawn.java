package Project2;

public class Pawn extends ChessPiece {

	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "Pawn";
	}

	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = super.isValidMove(move, board);
        if (valid == false) { return valid; }

		// start with checking player. white moves upwards
		// fromRow - toRow will be positive
		if (player() == Player.WHITE) {
			// check if piece is staying in the same column
			if (move.fromColumn == move.toColumn) {
				//check if there is a piece controlled by opponent in
				//desired move location
				if (board[move.toRow][move.toColumn].player()
						== player().next()) {
					valid = false;
				}
				// check if pawn is in starting row
				if (move.fromRow == 6) {
					// pawn can move one or two spaces from
					// starting row
					if (move.fromRow - move.toRow != 1
							|| move.fromRow - move.toRow != 2) {
						valid = false;
					}
					// check that if pawn moves two spaces, it doesn't
					// go through another piece
					if (move.fromRow - move.toRow == 2 &&
							board[move.toColumn][move.fromRow - 1]
									!= null) {
							valid = false;
						}

				}
				// pawn is not in starting row
				else {
					// pawn can only move forward once
					if (move.fromRow - move.toRow != 1) {
						valid = false;
					}
				}
			}
			// pawn not staying in same column
			else {
				// pawn can only move diagonally forward one space
				if (Math.abs(move.fromColumn - move.toColumn) == 1
						&& move.fromRow - move.toRow == 1) {
					// check to make sure to location has a piece
					// controlled by other player
					if (board[move.toRow][move.toColumn].player()
							!= Player.BLACK) {
						valid = false;
					}
				}
				else {
					valid = false;
				}
			}
		}
		// pawn is black, which moves downwards
		// fromRow - toRow will be negative
		else {
			// check if piece is staying in the same column
			if (move.fromColumn == move.toColumn) {
				//check if there is a piece controlled by opponent in
				//desired move location
				if (board[move.toRow][move.toColumn].player()
						== player().next()) {
					valid = false;
				}
				// check if pawn is in starting row
				if (move.fromRow == 1) {
					// pawn can move one or two spaces from
					// starting row
					if (move.fromRow - move.toRow != -1
							|| move.fromRow - move.toRow != -2) {
						valid = false;
					}
					// check that if pawn moves two spaces, it doesn't
					// go through another piece
					if (move.fromRow - move.toRow == -2) {
						if (board[move.toColumn][move.fromRow + 1]
								!= null) {
							valid = false;
						}
					}
				}
				// pawn is not in starting row
				else {
					// pawn can only move forward once
					if (move.fromRow - move.toRow != -1) {
						valid = false;
					}
				}
			}
			// pawn not staying in same column
			else {
				// pawn can only move diagonally forward one space
				if (Math.abs(move.fromColumn - move.toColumn) == 1
						&& move.fromRow - move.toRow == -1) {
					// check to make sure move location has a piece
					// controlled by other player
					if (board[move.toRow][move.toColumn].player()
							!= Player.WHITE) {
						valid = false;
					}
				}
				else {
					valid = false;
				}
			}
		}

		return valid;
	}
}
