package Project2;

public class SaveState {
    public Move move;
    public IChessPiece fromPiece;
    public IChessPiece toPiece;
    public boolean wasEnPassant;
    public boolean wasCastling;
    public CastlingData data;

    public SaveState (Move move, IChessPiece[][] board) {
        this.move = move;
        this.fromPiece = copy(move.fromRow, move.fromColumn, board);
        this.toPiece = copy(move.toRow, move.toColumn, board);
        wasEnPassant = false;
        wasCastling = false;
        data = new CastlingData();
    }

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

    public void setWasCastling(boolean wasCastling) {
        this.wasCastling = wasCastling;
    }

    public void setEnPassant(boolean v) {
        wasEnPassant = v;
    }

    public void saveCastlingData(CastlingData d) {
        data.setBlackRightRookMoved(d.blackRightRookMoved);
        data.setBlackLeftRookMoved(d.blackLeftRookMoved);
        data.setBlackKingMoved(d.blackKingMoved);
        data.setWhiteKingMoved(d.whiteKingMoved);
        data.setWhiteLeftRookMoved(d.whiteLeftRookMoved);
        data.setWhiteRightRookMoved(d.whiteRightRookMoved);
    }

}
