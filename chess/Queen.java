package chess;

/**********************************************************************
 * @author Dan Dietsche, Kyle Scott, Joshep Lentine
 * CIS 163 Winter 2022
 * 2/21/2022
 * Project 2
 *
 * Queen class implements the functions of a queen in chess
 */

public class Queen extends ChessPiece {

    /******************************************************************
     * constructs a Queen and sets owner to the given parameter
     *
     * @param player the owner of the chess piece
     */
    public Queen(Player player) {
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
        return "Queen";

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
     * @param move  a {@link Project2.Move} object describing the
     *                  move to be made.
     * @param board the {@link Project2.IChessPiece} in which this
     *                  piece resides.
     *
     * @return {@code true} if the proposed move is valid,
     *  				{@code false}otherwise.
     */
    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Bishop move1 = new Bishop
                (board[move.fromRow][move.fromColumn].player());
        Rook move2 = new Rook
                (board[move.fromRow][move.fromColumn].player());
        board[move.fromRow][move.fromColumn] = move1;
        boolean bishopIsValid = move1.isValidMove(move, board);
        board[move.fromRow][move.fromColumn] = move2;
        boolean rookIsValid = move2.isValidMove(move, board);
        board[move.fromRow][move.fromColumn] = this;
        return (bishopIsValid|| rookIsValid);



    }
}
