package chess;

public class Bishop extends ChessPiece {

    public Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = super.isValidMove(move, board);
        if(valid == false){
            return valid;
        }

        //Bishop can only move diagonally unlimited spaces
        //meaning each move should increase the row and column equally
        if(Math.abs(move.fromRow - move.toRow) !=
                Math.abs(move.fromColumn - move.toColumn)){
            return false;
        }
        //Checks if bishop is moving up left
        if(move.fromRow - move.toRow < 0 &&
                move.fromColumn - move.toColumn > 0){
            for(int i = 1; i < Math.abs(move.fromRow - move.toRow); i++){
                if(board[move.fromRow + i][move.fromColumn - i] != null){
                    valid = false;
                }
            }
        }
        //Checks if bishop is move up right
        if(move.fromRow - move.toRow > 0 &&
                move.fromColumn - move.toColumn > 0){
            for(int i = 1; i < Math.abs(move.fromRow - move.toRow); ++i){
                if(board[move.fromRow - i][move.fromColumn - i] != null){
                    valid = false;
                }
            }
        }
        //Checks if bishop is moving down left
        if(move.fromRow - move.toRow < 0 &&
                move.fromColumn - move.toColumn < 0){
            for(int i = 1; i < Math.abs(move.fromRow - move.toRow); i++){
                if(board[move.fromRow + i][move.fromColumn + i] != null){
                    valid = false;
                }
            }
        }
        //Bishop is moving down right
        if(move.fromRow - move.toRow > 0 &&
                move.fromColumn - move.toColumn < 0){
            for(int i = 1; i < Math.abs(move.fromRow - move.toRow); i++){
                if(board[move.fromRow - i][move.fromColumn + i] != null){
                    valid = false;
                }
            }
        }
        return valid;

    }
}
