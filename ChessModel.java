package Project2;

public class ChessModel implements IChessModel {	 
    private IChessPiece[][] board;
	private Player player;

	public ChessModel() {
		board = new IChessPiece[8][8];
		player = Player.WHITE;

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);

		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new King(Player.BLACK);
		board[0][4] = new Queen(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight (Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);

	}

	/******************************************************************
	 * Returns whether the game is complete.
	 *
	 * @return {@code true} if complete, {@code false} otherwise.
	 */
	public boolean isComplete() {
		boolean valid = true;
		Move test;
		IChessPiece[][] currentBoard = board;
		Player p;
		// check which player is in check
		if (inCheck(Player.BLACK)) {
			p = Player.BLACK;
		}
		else if (inCheck(Player.WHITE)) {
			p = Player.WHITE;
		}
		else {
			return false;
		}

		// go through rows
		for (int a = 0; a < 8; ++a) {
			// go through columns
			for (int b = 0; b < 8; ++b) {
				// check player for piece in location
				if (board[a][b] != null && board[a][b].player() == p) {
					// go through rows
					for (int c = 0; c < 8; ++c) {
						// go through columns
						for (int d = 0; d < 8; ++d) {
							// make new move
							test = new Move(a, b, c, d);
							// see if new move is valid
							if (board[a][b].isValidMove(test, board)) {
								// try move
								move(test);
								// check if player is still in check
								// if not, set valid to false and break
								// nested loops
								if (!inCheck(p)){
									valid = false;
									a = b = c = d = 8;
								}
								// reset board after test
								board = currentBoard;
							}
						}
					}
				}
			}
		}

		return valid;
	}

	/******************************************************************
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to
	 * location {@code [move.fromRow, move.fromColumn]}.
	 *
	 * @param move a {@link Project2.Move} object describing the
	 *                move to be made.
	 * @return {@code true} if the proposed move is valid,
	 * 				  {@code false} otherwise.
	 * @throws IndexOutOfBoundsException if either
	 * 				   {@code [move.fromRow, move.fromColumn]} or
	 * 				   {@code [move.toRow, move.toColumn]} don't
	 * 				   represent valid locations on the board.
	 */
	public boolean isValidMove(Move move) {
		if (move.fromRow < 0 || move.fromRow > 7 || move.fromColumn < 0
				|| move.fromColumn > 7 || move.toRow < 0
				|| move.toRow > 7 || move.toColumn < 0
				|| move.toColumn > 7) {
			throw new IndexOutOfBoundsException();
		}

		boolean valid = false;

		if (board[move.fromRow][move.fromColumn] != null) {
			if (board[move.fromRow][move.fromColumn].
					isValidMove(move, board)) {
				return true;
			}
		}
		return valid;
	}

	/******************************************************************
	 * Moves the piece from location {@code [move.fromRow,
	 * move.fromColumn]} to location {@code [move.fromRow,
	 * move.fromColumn]}.
	 *
	 * @param move a {@link Project2.Move} object describing the
	 *               move to be made.
	 * @throws IndexOutOfBoundsException if either
	 *  			 {@code [move.fromRow, move.fromColumn]} or
	 *  			 {@code [move.toRow, move.toColumn]} don't
	 *  			 represent valid locations on the board.
	 */
	public void move(Move move) {
		if (move.fromRow < 0 || move.fromRow > 7 || move.fromColumn < 0
				|| move.fromColumn > 7 || move.toRow < 0
				|| move.toRow > 7 || move.toColumn < 0
				|| move.toColumn > 7) {
			throw new IndexOutOfBoundsException();
		}

		board[move.toRow][move.toColumn] =
				board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/******************************************************************
	 * Report whether the current player p is in check.
	 * @param  p {@link Project2.Move} the Player being checked
	 * @return {@code true} if the current player is in check,
	 * {@code false} otherwise.
	 */
	public boolean inCheck(Player p) {
		boolean valid = false;
		int kingRow = -1;
		int kingCol = -1;
		Move test;

		// loop through board to find king
		for (int a = 0; a < 8; ++a) {
			for (int b = 0; b < 8; ++b) {
				if (board[a][b] != null && board[a][b].player() == p
						&& board[a][b].type().equals("King")) {
					kingRow = a;
					kingCol = b;
					a = 8;
					b = 8;
				}
			}
		}

		// loop through board checking if any piece has a valid move
		// to king's location
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				test = new Move(i, j, kingRow, kingCol);
				if (board[i][j] != null
						&& board[i][j].isValidMove(test, board)) {
					valid = true;
					i = 8;
					j = 8;
				}
			}
		}

		return valid;
	}


	/******************************************************************
	 * Return the current player.
	 *
	 * @return the current player
	 */
	public Player currentPlayer() {
		return player;
	}

	/******************************************************************
	 * Return the number of rows
	 *
	 * @return 8
	 */
	public int numRows() {
		return 8;
	}

	/******************************************************************
	 * Return the number of columns
	 *
	 * @return 8
	 */
	public int numColumns() {
		return 8;
	}

	/******************************************************************
	 * return piece at specified location
	 *
	 * @param row specified row
	 * @param column specified column
	 *
	 * @return returns piece at row, column
	 */
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/******************************************************************
	 * changes active player
	 *
	 */
	public void setNextPlayer() {
		player = player.next();
	}

	/******************************************************************
	 * places a piece at specified location
	 *
	 * @param row specified row
	 * @param column specified column
	 * @param piece specified piece
	 */
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	/******************************************************************
	 * AI for game to be added later
	 *
	 */
	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order. 
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check 
		 * 
		 * b. Attempt to put opponent into check (or checkmate). 
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game. 
		 *
		 *c. Determine if any of your pieces are in danger, 
		 *		i. Move them if you can. 
		 *		ii. Attempt to protect that piece. 
		 *
		 *d. Move a piece (pawns first) forward toward opponent king 
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		}
}
