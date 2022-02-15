package chess;

/**********************************************************************
 * @author Dan Dietsche
 * CIS 163 Winter 2022
 * Project 2
 *
 * holds move data, copies of the to and from pieces, if the move was
 * en passant or castling, and data on if castling can be done. this is
 * used in ChessModel to be able to undo moves.
 *
 */
public class SaveState {

    /** move stored */
    public Move move;

    /** piece stored in the move from location */
    public IChessPiece fromPiece;

    /** piece stored in the move to location */
    public IChessPiece toPiece;

    /** if the move stored was en passant */
    public boolean wasEnPassant;

    /** if the move stored was castling */
    public boolean wasCastling;

    /** what the CastlingData of the model was right before the move */
    public CastlingData data;

    /******************************************************************
     * constructor that saves the given move and pieces at the to and
     * from locations
     *
     * @param move given move
     * @param board given board
     */
    public SaveState (Move move, IChessPiece[][] board) {
        this.move = move;
        this.fromPiece = copy(move.fromRow, move.fromColumn, board);
        this.toPiece = copy(move.toRow, move.toColumn, board);
        wasEnPassant = false;
        wasCastling = false;
        data = new CastlingData();
    }

    /******************************************************************
     * returnss a new IChessPiece of the same piece type and player as
     * the piece in the given row and column, so that the piece stored
     * in this save state doesn't point at the old location
     *
     * @param row given row in the board
     * @param col given column in the board
     * @param board given board
     * @return returns new version of piece
     */
    public IChessPiece copy(int row, int col, IChessPiece[][] board) {
        if (board[row][col] != null) {
            String type = board[row][col].type();
            Player player = board[row][col].player();
            switch (type) {
                case "Pawn":
                    return new Pawn(player);
                case "Rook":
                    return new Rook(player);
                case "Knight":
                    return new Knight(player);
                case "Bishop":
                    return new Bishop(player);
                case "Queen":
                    return new Queen(player);
                case "King":
                    return new King(player);
            }
        }
        return null;
    }

    /******************************************************************
     * sets wasCastling to the given boolean value
     *
     * @param wasCastling was the move being stored a castling move
     */
    public void setWasCastling(boolean wasCastling) {
        this.wasCastling = wasCastling;
    }

    /******************************************************************
     * sets wasEnPassant to the given boolean value
     * @param wasEnPassant was the move being stored an en passant move
     */
    public void setEnPassant(boolean wasEnPassant) {
        this.wasEnPassant = wasEnPassant;
    }

    /******************************************************************
     * copies all the data from the given CastlingData into the
     * CastlingData stored in this method
     *
     * @param d CastlingData being copied from
     */
    public void saveCastlingData(CastlingData d) {
        data.setBlackRightRookMoved(d.blackRightRookMoved);
        data.setBlackLeftRookMoved(d.blackLeftRookMoved);
        data.setBlackKingMoved(d.blackKingMoved);
        data.setWhiteKingMoved(d.whiteKingMoved);
        data.setWhiteLeftRookMoved(d.whiteLeftRookMoved);
        data.setWhiteRightRookMoved(d.whiteRightRookMoved);
    }

}