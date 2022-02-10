package chess;

public class Queen extends ChessPiece {

    public Queen(Player player) {
        super(player);

    }
    public String type() {
        return "Queen";

    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());
        board[move.fromRow][move.fromColumn] = move1;
        boolean bishopIsValid = move1.isValidMove(move, board);
        board[move.fromRow][move.fromColumn] = move2;
        boolean rookIsValid = move2.isValidMove(move, board);
        board[move.fromRow][move.fromColumn] = this;
        return (bishopIsValid|| rookIsValid);



    }
}
