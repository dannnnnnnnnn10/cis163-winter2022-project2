package chess;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean valid = super.isValidMove(move, board);
        if (valid == false) { return valid; }

        valid = false;

        if (Math.abs(move.fromRow - move.toRow) == 2 && Math.abs(move.fromColumn - move.toColumn) == 1) {
            valid = true;
        }
        if (Math.abs(move.fromRow - move.toRow) == 1 && Math.abs(move.fromColumn - move.toColumn) == 2) {
            valid = true;
        }

        return valid;

    }

}
