package chess;

public class Rook extends ChessPiece {

    /******************************************************************
     * constructs a Rook and sets owner to the given parameter
     *
     * @param player the owner of the chess piece
     */
    public Rook(Player player) {
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
        return "Rook";
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
        // run ChessPiece's isValidMove first, if false return that
        boolean valid = super.isValidMove(move, board);
        if (!valid) { return valid; }

        // rook can only move in the same row or the same column
        // if both row and column have changed, the movement is invalid
        if ((move.toRow != move.fromRow)
                && (move.toColumn != move.fromColumn)) {
            valid = false;
        }

        // rook is moving in a column
        if (move.toRow == move.fromRow) {
            // if negative, piece is moving right
            if (Integer.signum((move.fromColumn - move.toColumn))
                    == -1) {
                // loop through all spaces between start and finish
                // to check for pieces in the way
                for (int i = move.fromColumn + 1; i < move.toColumn;
                     ++i){
                    if (board[move.toRow][i] != null) {
                        valid = false;
                        break;
                    }
                }
            }
            // if positive, piece is moving left
            else {
                // loop through all spaces between start and finish
                // to check for pieces in the way
                for (int i = move.fromColumn - 1; i > move.toColumn;
                     --i){
                    if (board[move.toRow][i] != null) {
                        valid = false;
                        break;
                    }
                }
            }
        }


        // rook is moving in a row
        if (move.toColumn == move.fromColumn) {
            //if negative, piece is moving down
            if (Integer.signum((move.fromRow - move.toRow)) == -1) {
                // loop through all spaces between start and finish
                // to check for pieces in the way
                for (int i = (move.fromRow + 1); i < move.toRow; ++i){
                    if (board[i][move.toColumn] != null) {
                        valid = false;
                        break;
                    }
                }
            }
            // if positive, piece is moving up
            else {
                // loop through all spaces between start and finish
                // to check for pieces in the way
                for (int i = (move.fromRow - 1); i > move.toRow; --i){
                    if (board[i][move.toColumn] != null) {
                        valid = false;
                        break;
                    }
                }
            }
        }

        return valid;

    }

}
