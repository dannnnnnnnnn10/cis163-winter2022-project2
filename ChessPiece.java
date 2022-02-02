package Project2;

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;

		if (move.fromRow < 0 || move.fromRow > 7 || move.fromColumn < 0
				|| move.fromColumn > 7 || move.toRow < 0
				|| move.toRow > 7 || move.toColumn < 0
				|| move.toColumn > 7) {
			throw new IndexOutOfBoundsException();
		}
		
		if (((move.fromRow == move.toRow) &&
				(move.fromColumn == move.toColumn))) {
			valid = false;
		}
		
		if (board[move.fromRow][move.fromColumn] != this) {
			throw new IllegalArgumentException();
		}
		
		if (board[move.toRow][move.toColumn].player() == this.player()) {
			valid = false;
		}

		return valid;
	}
}
