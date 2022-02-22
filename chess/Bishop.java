package chess;

/**********************************************************************
 * @author Dan Dietsche, Kyle Scott, Joshep Lentine
 * CIS 163 Winter 2022
 * 2/21/2022
 * Project 2
 *
 * Bishop class, implemets the functions of a bishop in chess
 */

public class Bishop extends ChessPiece {

    /******************************************************************
     * Constructs a Bishop and sets owner to given parameter
     *
     * @param player
     */
    public Bishop(Player player) {
        super(player);
    }

    /******************************************************************
     * Return the type of this piece ("King", "Queen", "Rook", etc.).
     * Note:  In this case "type" refers to the game of chess, not
     * the type of the Java class.
     *
     * @return the type of chess piece
     */
    @Override
    public String type() {
        return "Bishop";
    }

    /******************************************************************
     * Returns whether the piece at location
     * {@code [move.fromRow, move.fromColumn]} is allowed to move to
     * location {@code [move.fromRow, move.fromColumn]}
     *
     * Note:  Pieces don't store their own location
     * (because doing so would be redundant).  Therefore, the
     * {@code [move.fromRow, move.fromColumn]} component of
     * {@code move} is necessary.{@code this} object must be the piece
     * at location {@code [move.fromRow, move.fromColumn]}.
     * (This method makes no sense otherwise.)
     *
     * @param move  a {@link Project2.Move} object describing the
     *                  move to be made.
     * @param board the {@link Project2.IChessPiece} in which this
     *                  piece resides.
     * @return {@code true} if the proposed move is valid,
     * 				{@code false}otherwise.
     */
    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = super.isValidMove(move, board);
        if(valid == false){
            return valid;
        }

        //Bishop can only move diagonally unlimited spaces
        //meaning the amount of rows and columns moved should be equal
        if(Math.abs(move.fromRow - move.toRow) !=
                Math.abs(move.fromColumn - move.toColumn)){
            return false;
        }
        //Checks if bishop is moving up left
        if(move.fromRow - move.toRow < 0 &&
                move.fromColumn - move.toColumn > 0){
            for(int i = 1;
                i < Math.abs(move.fromRow - move.toRow); i++){
                if(board[move.fromRow + i][move.fromColumn - i]
                        != null){
                    valid = false;
                }
            }
        }
        //Checks if bishop is move up right
        if(move.fromRow - move.toRow > 0 &&
                move.fromColumn - move.toColumn > 0){
            for(int i = 1;
                i < Math.abs(move.fromRow - move.toRow); ++i){
                if(board[move.fromRow - i][move.fromColumn - i]
                        != null){
                    valid = false;
                }
            }
        }
        //Checks if bishop is moving down left
        if(move.fromRow - move.toRow < 0 &&
                move.fromColumn - move.toColumn < 0){
            for(int i = 1;
                i < Math.abs(move.fromRow - move.toRow); i++){
                if(board[move.fromRow + i][move.fromColumn + i]
                        != null){
                    valid = false;
                }
            }
        }
        //Bishop is moving down right
        if(move.fromRow - move.toRow > 0 &&
                move.fromColumn - move.toColumn < 0){
            for(int i = 1;
                i < Math.abs(move.fromRow - move.toRow); i++){
                if(board[move.fromRow - i][move.fromColumn + i]
                        != null){
                    valid = false;
                }
            }
        }
        return valid;

    }
}
