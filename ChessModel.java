package Project2;

import java.util.ArrayList;

public class ChessModel implements IChessModel {
	private IChessPiece[][] board;
	private Player player;
	private ArrayList<SaveState> prevMoves;
	private int turn;
	private boolean canEnPassant;
	private CastlingData canCastle;

	public ChessModel() {
		board = new IChessPiece[8][8];
		player = Player.WHITE;
		turn = 0;
		prevMoves = new ArrayList<>(0);
		canEnPassant = false;
		canCastle = new CastlingData();

		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
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
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
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
		Player p = this.player;
		// check if current player is in check
		if (!inCheck(p)) {
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
								SaveState prevState = new SaveState(test, board);
								// try move
								tryMove(test);
								// check if player is still in check
								// if not, set valid to false and break
								// nested loops
								if (!inCheck(p)){
									valid = false;
									a = b = c = d = 8;
								}
								// reset board after test
								board[prevState.move.fromRow][prevState.move.fromColumn] = prevState.fromPiece;
								board[prevState.move.toRow][prevState.move.toColumn] = prevState.toPiece;
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

		if (board[move.fromRow][move.fromColumn] != null
				&& board[move.fromRow][move.fromColumn].player()
					== player) {
			if (isEnPassant(move, board) || board[move.fromRow][move.fromColumn].
					isValidMove(move, board) || isValidCastling(move, board)) {
				SaveState prevState = new SaveState(move, board);
				tryMove(move);
				if (!inCheck(player)) {
					valid = true;
				}
				board[prevState.move.fromRow][prevState.move.fromColumn] = prevState.fromPiece;
				board[prevState.move.toRow][prevState.move.toColumn] = prevState.toPiece;
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

		if (isEnPassant(move, board)) {
			prevMoves.add(new SaveState(move, board));
			board[move.toRow][move.toColumn] =
					board[move.fromRow][move.fromColumn];
			board[move.fromRow][move.fromColumn] = null;
			board[move.fromRow][move.toColumn] = null;
			prevMoves.get(prevMoves.size()-1).setEnPassant(true);
		}
		else if (isValidCastling(move, board)) {
			prevMoves.add(new SaveState(move, board));
			if (move.fromRow == 0) {
				if ((move.fromColumn - move.toColumn) > 0) {
					board[0][0] = null;
					board[0][4] = null;
					board[0][3] = new Rook(Player.BLACK);
					board[0][2] = new King(Player.BLACK);
				}
				else {
					board[0][7] = null;
					board[0][4] = null;
					board[0][5] = new Rook(Player.BLACK);
					board[0][6] = new King(Player.BLACK);
				}
			}
			if (move.fromRow == 7) {
				if ((move.fromColumn - move.toColumn) > 0) {
					board[7][0] = null;
					board[7][4] = null;
					board[7][3] = new Rook(Player.WHITE);
					board[7][2] = new King(Player.WHITE);
				}
				else {
					board[7][7] = null;
					board[7][4] = null;
					board[7][5] = new Rook(Player.WHITE);
					board[7][6] = new King(Player.WHITE);
				}
			}
			prevMoves.get(prevMoves.size()-1).setWasCastling(true);
		}
		else {
			prevMoves.add(new SaveState(move, board));
			board[move.toRow][move.toColumn] =
					board[move.fromRow][move.fromColumn];
			board[move.fromRow][move.fromColumn] = null;
		}
		if (board[move.toRow][move.toColumn].type().equals("Pawn")
		&& Math.abs(move.fromRow - move.toRow) == 2) {
			canEnPassant = true;
		}
		else {
			canEnPassant = false;
		}

		updateCastlingData(move, board);
		promotionCheck(board);
		setNextPlayer();
		turn++;

	}

	private void tryMove(Move move) {
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

	public void undo() {
		SaveState save = prevMoves.get(prevMoves.size()-1);

		board[save.move.fromRow][save.move.fromColumn] = save.fromPiece;
		board[save.move.toRow][save.move.toColumn] = save.toPiece;
		if (save.wasEnPassant) {
			canEnPassant = true;
		}
		else {
			canEnPassant = false;
		}
		if (save.wasCastling) {
			if (save.move.fromRow == 0) {
				canCastle.setBlackKingMoved(false);
				if (save.move.fromColumn - save.move.toColumn > 0) {
					canCastle.setBlackLeftRookMoved(false);
				}
				else {
					canCastle.setBlackRightRookMoved(false);
				}
			}
			if (save.move.fromRow == 7) {
				canCastle.setWhiteKingMoved(false);
				if (save.move.fromColumn - save.move.toColumn > 0) {
					canCastle.setWhiteLeftRookMoved(false);
				}
				else {
					canCastle.setWhiteRightRookMoved(false);
				}
			}
		}
		prevMoves.remove(prevMoves.size()-1);
		turn--;
		setNextPlayer();
	}

	public void promotionCheck(IChessPiece[][] board) {
		for (int i = 0; i < 8; ++i) {
				if (board[0][i] != null) {
					if (board[0][i].type().equals("Pawn") &&
							board[0][i].player().equals(Player.WHITE)) {
						board[0][i] = new Queen(Player.WHITE);
					}
				}
				if (board[7][i] != null) {
					if (board[7][i].type().equals("Pawn") &&
							board[7][i].player().equals(Player.BLACK)) {
						board[7][i] = new Queen(Player.BLACK);
					}
				}
			}
		}

	public boolean isEnPassant(Move move, IChessPiece[][] board) {
		boolean valid = false;
		if (turn == 0) {
			return valid;
		}
		SaveState lastTurn = prevMoves.get(prevMoves.size() - 1);
		if (!canEnPassant) {
			return valid;
		}
		if (!board[move.fromRow][move.fromColumn].type().equals("Pawn")) {
			return valid;
		}
		if (player == Player.WHITE) {
			if (move.fromRow == 3) {
				if (move.toColumn == lastTurn.move.fromColumn &&
						Math.abs(move.fromColumn - move.toColumn) == 1
						&& (move.fromRow - move.toRow) == 1) {
					valid = true;
				}
			}
		}
		else if (player == Player.BLACK) {
			if (move.fromRow == 4) {
				if (move.toColumn == lastTurn.move.fromColumn &&
						Math.abs(move.fromColumn - move.toColumn) == 1
						&& (move.fromRow - move.toRow) == -1) {
					valid = true;
				}
			}
		}
		return valid;
	}

	public boolean isValidCastling(Move move, IChessPiece[][] board) {
		boolean valid = false;
		if (inCheck(player)) {
			return valid;
		}
		if (!(Math.abs(move.fromColumn - move.toColumn) == 2 &&
				move.fromRow == move.toRow)) {
			return valid;
		}
		if (player == Player.WHITE) {
			if (canCastle.whiteKingMoved) {
				return valid;
			}
			if ((move.fromColumn - move.toColumn) < 0) {
				if (!canCastle.whiteRightRookMoved) {
					if (board[7][5] != null || board[7][6] != null) {
						return valid;
					}
					Move move1 = new Move(7, 4, 7, 5);
					boolean move1Valid = false;
					Move move2 = new Move(7, 4, 7, 6);
					boolean move2Valid = false;
					tryMove(move1);
					if (!inCheck(player)) {
						move1Valid = true;
					}
					board[move1.fromRow][move1.fromColumn] = new King(Player.WHITE);
					board[move1.toRow][move1.toColumn] = null;
					tryMove(move2);
					if (!inCheck(player)) {
						move2Valid = true;
					}
					board[move2.fromRow][move2.fromColumn] = new King(Player.WHITE);
					board[move2.toRow][move2.toColumn] = null;
					if (move1Valid && move2Valid) {
						valid = true;
					}
				}
			}
			if ((move.fromColumn - move.toColumn) > 0) {
				if (!canCastle.whiteLeftRookMoved) {
					if (board[7][3] != null || board[7][2] != null || board[7][1] != null) {
						return valid;
					}
					Move move1 = new Move(7, 4, 7, 3);
					boolean move1Valid = false;
					Move move2 = new Move(7, 4, 7, 2);
					boolean move2Valid = false;
					Move move3 = new Move(7, 4, 7, 1);
					boolean move3Valid = false;
					tryMove(move1);
					if (!inCheck(player)) {
						move1Valid = true;
					}
					board[move1.fromRow][move1.fromColumn] = new King(Player.WHITE);
					board[move1.toRow][move1.toColumn] = null;
					tryMove(move2);
					if (!inCheck(player)) {
						move2Valid = true;
					}
					board[move2.fromRow][move2.fromColumn] = new King(Player.WHITE);
					board[move2.toRow][move2.toColumn] = null;
					tryMove(move3);
					if (!inCheck(player)) {
						move3Valid = true;
					}
					board[move3.fromRow][move3.fromColumn] = new King(Player.WHITE);
					board[move3.toRow][move3.toColumn] = null;
					if (move1Valid && move2Valid && move3Valid) {
						valid = true;
					}
				}
			}
		}

		if (player == Player.BLACK) {
			if (canCastle.blackKingMoved) {
				return valid;
			}
			if ((move.fromColumn - move.toColumn) < 0) {
				if (!canCastle.blackRightRookMoved) {
					if (board[7][5] != null || board[7][6] != null) {
						return valid;
					}
					Move move1 = new Move(0, 4, 0, 5);
					boolean move1Valid = false;
					Move move2 = new Move(0, 4, 0, 6);
					boolean move2Valid = false;
					tryMove(move1);
					if (!inCheck(player)) {
						move1Valid = true;
					}
					board[move1.fromRow][move1.fromColumn] = new King(Player.BLACK);
					board[move1.toRow][move1.toColumn] = null;
					tryMove(move2);
					if (!inCheck(player)) {
						move2Valid = true;
					}
					board[move2.fromRow][move2.fromColumn] = new King(Player.BLACK);
					board[move2.toRow][move2.toColumn] = null;
					if (move1Valid && move2Valid) {
						valid = true;
					}
				}
			}
			if ((move.fromColumn - move.toColumn) > 0) {
				if (!canCastle.blackLeftRookMoved) {
					if (board[0][3] != null || board[0][2] != null || board[0][1] != null) {
						return valid;
					}
					Move move1 = new Move(0, 4, 0, 3);
					boolean move1Valid = false;
					Move move2 = new Move(0, 4, 0, 2);
					boolean move2Valid = false;
					Move move3 = new Move(0, 4, 0, 1);
					boolean move3Valid = false;
					tryMove(move1);
					if (!inCheck(player)) {
						move1Valid = true;
					}
					board[move1.fromRow][move1.fromColumn] = new King(Player.BLACK);
					board[move1.toRow][move1.toColumn] = null;
					tryMove(move2);
					if (!inCheck(player)) {
						move2Valid = true;
					}
					board[move2.fromRow][move2.fromColumn] = new King(Player.BLACK);
					board[move2.toRow][move2.toColumn] = null;
					tryMove(move3);
					if (!inCheck(player)) {
						move3Valid = true;
					}
					board[move3.fromRow][move3.fromColumn] = new King(Player.BLACK);
					board[move3.toRow][move3.toColumn] = null;
					if (move1Valid && move2Valid && move3Valid) {
						valid = true;
					}
				}
			}
		}

		return valid;
	}

	public void updateCastlingData(Move move, IChessPiece[][] board) {
		if (move.fromRow == 0 && move.fromColumn == 0 &&
				board[move.toRow][move.toColumn].type().equals("Rook")) {
			canCastle.setBlackLeftRookMoved(true);
		}
		else if (move.fromRow == 0 && move.fromColumn == 7 &&
				board[move.toRow][move.toColumn].type().equals("Rook")) {
			canCastle.setBlackRightRookMoved(true);
		}
		else if (move.fromRow == 0 && move.fromColumn == 4 &&
				board[move.toRow][move.toColumn].type().equals("King")) {
			canCastle.setBlackKingMoved(true);
		}
		else if (move.fromRow == 7 && move.fromColumn == 0 &&
				board[move.toRow][move.toColumn].type().equals("Rook")) {
			canCastle.setWhiteLeftRookMoved(true);
		}
		else if (move.fromRow == 7 && move.fromColumn == 7 &&
				board[move.toRow][move.toColumn].type().equals("Rook")) {
			canCastle.setWhiteRightRookMoved(true);
		}
		else if (move.fromRow == 7 && move.fromColumn == 4 &&
				board[move.toRow][move.toColumn].type().equals("King")) {
			canCastle.setWhiteKingMoved(true);
		}
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