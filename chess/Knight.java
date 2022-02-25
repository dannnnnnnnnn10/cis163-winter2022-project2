package chess;

/**********************************************************************
 * @author Dietsche, Kyle Scott, Joseph Lentine
 * CIS 163 Winter 2022
 * 2/21/2022
 * Project 2
 *
 * Knight class implements the functions of a knight in chess
 */

public class Knight extends ChessPiece {

    /******************************************************************
     * Constructs a Knight and sets owner to given parameter
     *
     * @param player the owner of the chess piece
     */
    public Knight(Player player) {
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
        return "Knight";
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
    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean valid = super.isValidMove(move, board);
        if (valid == false) { return valid; }

        valid = false;

        if (Math.abs(move.fromRow - move.toRow) == 2
                && Math.abs(move.fromColumn - move.toColumn) == 1) {
            valid = true;
        }
        if (Math.abs(move.fromRow - move.toRow) == 1
                && Math.abs(move.fromColumn - move.toColumn) == 2) {
            valid = true;
        }

        return valid;

    }

}
