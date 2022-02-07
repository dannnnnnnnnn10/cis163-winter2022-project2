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
        //meaning each move should increase the row and column
        if (Math.abs(move.fromRow - move.toRow) < 1 ||
                Math.abs(move.fromColumn - move.toColumn) < 1) {
            valid = false;
        }
        //Checks if bishop is moving forward left
        if(move.fromRow - move.toRow > 0 &&
                move.fromColumn - move.toColumn >0){
            for(int i = move.fromRow - 1; i > move.toRow; i--){
                for(int k = move.fromColumn - 1; k > move.toColumn; k--){
                    if(board[i][k] != null){
                        valid = false;
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
        }
        //Checks if bishop is move forward right
        if(move.fromRow - move.toRow > 0 &&
                move.toColumn - move.fromColumn >0){
            for(int i = move.fromRow - 1; i > move.toRow; i--){
                for(int k = move.fromColumn + 1; k < move.toColumn; k++){
                    if(board[i][k] != null){
                        valid = false;
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
        }
        //Checks if bishop is moving back left
        if(move.toRow - move.fromRow > 0 &&
                move.fromColumn - move.toColumn > 0){
            for(int i = move.fromRow + 1; i < move.toRow; i++){
                for(int k = move.fromColumn - 1; k > move.toColumn; k--){
                    if(board[i][k] != null){
                        valid = false;
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
        }
        //Bishop is moving back right
        if(move.toRow - move.fromRow > 0 &&
                move.toColumn - move.fromColumn > 0){
            for(int i = move.fromRow + 1; i < move.toRow; i++){
                for(int k = move.fromColumn + 1; k < move.toColumn; k++){
                    if(board[i][k] != null){
                        valid = false;
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
        }

        return valid;

    }
}
