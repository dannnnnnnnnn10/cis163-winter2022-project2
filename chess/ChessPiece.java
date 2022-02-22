package chess;

/**********************************************************************
 * @author Dan Dietsche, Kyle Scott, Joseph Lentine
 * CIS 163 Winter 2022
 * 2/21/2022
 * Project 2
 *
 * ChessPiece class that is extended by all other piece classes
 *
 */
public abstract class ChessPiece implements IChessPiece {

    /** the owner of the piece */
    private Player owner;

    /******************************************************************
     * constructs a Chess piece and sets owner to the given parameter
     *
     * @param player the owner of the chess piece
     */
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /******************************************************************
     * Return the type of this piece ("King", "Queen", "Rook", etc.).
     * Note:  In this case "type" refers to the game of chess, not
     * the type of the Java class.
     *
     * @return the type of this piece
     */
    @Override
    public abstract String type();

    /******************************************************************
     * Return the player that owns this piece.
     *
     * @return the player that owns this piece.
     */
    @Override
    public Player player() {
        return owner;
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
     * @return {@code true} if the proposed move is valid,
     *  				{@code false}otherwise.
     * @throws IndexOutOfBoundsException if either {@code
     *  		[move.fromRow, move.fromColumn]} or {@code [move.toRow,
     *  		move.toColumn]} don't represent valid locations on the
     *  		board.
     * @throws IllegalArgumentException  if {@code this} object isn't
     * 			the piece at location {@code [move.fromRow,
     * 			move.fromColumn]}.
     */
    @Override
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

        if (board[move.toRow][move.toColumn] != null
                && board[move.toRow][move.toColumn].player()
                == this.player())
        {
            valid = false;
        }

        return valid;
    }
}