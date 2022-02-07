package chess;

public class King extends ChessPiece {

    /******************************************************************
     * constructs a King and sets owner to the given parameter
     *
     * @param player the owner of the chess piece
     */
    public King(Player player) {
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
        return "King";
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
     *      		    {@code false}otherwise.
     */
    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = super.isValidMove(move, board);
        if (valid == false) {
            return valid;
        }
        if (move.fromColumn == move.toColumn) {
            //King can move 1 space back and forward
            // if staying in same column
            if (Math.abs(move.fromRow - move.toRow) != 1) {
                valid = false;
            }
        }
        //Checks if player is staying in same row
        else if(move.fromRow == move.toRow){
            //King can move 1 space left and right
            // if staying in same row
            if (Math.abs(move.fromColumn - move.toColumn) != 1) {
                valid = false;
            }
        }
        //Checks if player is moving diagonally
        else{
            //King can move 1 space left and right
            // and 1 space forward and back
            if(Math.abs(move.fromRow - move.toRow) != 1
                    || Math.abs(move.fromColumn - move.toColumn) != 1){
                valid = false;
            }
        }

        return valid;
    }
}
