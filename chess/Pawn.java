package chess;

/**********************************************************************
 * @author Dan Dietsche (CIS), Kyle Scott (CIS), Joseph Lentine (ENG)
 * CIS 163 Winter 2022
 * 2/21/2022
 * Project 2
 *
 * Pawn class, implements the functions of a pawn in chess
 */

public class Pawn extends ChessPiece{

    /******************************************************************
     * constructs a Pawn and sets owner to the given parameter
     *
     * @param player the owner of the chess piece
     */
    public Pawn(Player player) {
        super(player);
    }

    /******************************************************************
     * Return the type of this piece ("King", "Queen", "Rook", etc.).
     * Note:  In this case "type" refers to the game of chess, not
     * the type of the Java class.
     *
     * @return the type of this piece
     */
    @Override
    public String type() {
        return "Pawn";
    }

    /******************************************************************
     * Returns whether the piece at location
     * {@code [move.fromRow, move.fromColumn]} is allowed to move to
     * location {@code [move.fromRow, move.fromColumn]}.
     *
     * Note:  Pieces don't store their own location
     * (because doing so would be redundant).  Therefore, the
     * {@code [move.fromRow, move.fromColumn]} component of
     * {@code move} is necessary. {@code this} object must be the piece
     * at location {@code [move.fromRow, move.fromColumn]}.
     * (This method makes no sense otherwise.)
     *
     * @param move  a {@link chess.Move} object describing the
     *                  move to be made.
     * @param board the {@link chess.IChessPiece} in which this
     *                  piece resides.
     *
     * @return {@code true} if the proposed move is valid,
     *  				{@code false}otherwise.
     */
    @Override
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
                if (board[move.toRow][move.toColumn] != null) {
                    valid = false;
                }
                // check if pawn is in starting row
                if (move.fromRow == 6) {
                    // pawn can move one or two spaces from
                    // starting row
                    if (move.fromRow - move.toRow < 1
                            || move.fromRow - move.toRow > 2) {
                        valid = false;
                    }
                    // check that if pawn moves two spaces, it doesn't
                    // go through another piece
                    if (move.fromRow - move.toRow == 2 &&
                            board[move.fromRow - 1][move.toColumn]
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
                    if (board[move.toRow][move.toColumn] == null) {
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
                if (board[move.toRow][move.toColumn] != null) {
                    valid = false;
                }
                // check if pawn is in starting row
                if (move.fromRow == 1) {
                    // pawn can move one or two spaces from
                    // starting row
                    if (move.fromRow - move.toRow > -1
                            || move.fromRow - move.toRow < -2) {
                        valid = false;
                    }
                    // check that if pawn moves two spaces, it doesn't
                    // go through another piece
                    if (move.fromRow - move.toRow == -2) {
                        if (board[move.fromRow + 1][move.toColumn]
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
                    if (board[move.toRow][move.toColumn] == null) {
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

