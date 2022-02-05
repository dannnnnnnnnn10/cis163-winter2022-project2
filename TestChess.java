package chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestChess {

    // tests that Pawn's white move logic is working
    @Test
    public void testPawnIsValidMoveWhite() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[6][2] = new Pawn(Player.WHITE);

        assertTrue(board[6][2].player() == Player.WHITE);

        assertSame("Pawn", board[6][2].type());

        Move move = new Move(6, 2, 5, 2);

        assertTrue(board[6][2].isValidMove(move, board));

        board[5][2] = new Pawn(Player.BLACK);

        assertFalse(board[6][2].isValidMove(move, board));

        board[5][2] = null;

        move = new Move(6, 2, 4, 2);

        assertTrue(board[6][2].isValidMove(move, board));

        board[5][2] = new Pawn(Player.BLACK);

        assertFalse(board[6][2].isValidMove(move, board));

        board[5][2] = null;

        board[4][2] = new Pawn(Player.BLACK);

        assertFalse(board[6][2].isValidMove(move, board));

        board[4][2] = null;

        move = new Move(6, 2, 3, 2);

        assertFalse(board[6][2].isValidMove(move, board));

        move = new Move(6, 2, 4, 3);

        assertFalse(board[6][2].isValidMove(move, board));

        move = new Move(6, 2, 5, 3);

        assertFalse(board[6][2].isValidMove(move, board));

        board[5][3] = new Pawn(Player.BLACK);

        assertTrue(board[6][2].isValidMove(move, board));

        board[5][3] = null;

        move = new Move(6, 2, 5, 1);

        assertFalse(board[6][2].isValidMove(move, board));

        board[5][1] = new Pawn(Player.BLACK);

        assertTrue(board[6][2].isValidMove(move, board));

        board[5][1] = null;

        board[6][2] = null;

        board[4][2] = new Pawn(Player.WHITE);

        move = new Move(4, 2, 3, 2);

        assertTrue(board[4][2].isValidMove(move, board));

        board[3][2] = new Pawn(Player.BLACK);

        assertFalse(board[4][2].isValidMove(move, board));

        board[3][2] = null;

        move = new Move(4, 2, 2, 2);

        assertFalse(board[4][2].isValidMove(move, board));
    }

    @Test
    public void testPawnIsValidMoveBlack() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[1][2] = new Pawn(Player.BLACK);

        assertTrue(board[1][2].player() == Player.BLACK);

        Move move = new Move(1, 2, 2, 2);

        assertTrue(board[1][2].isValidMove(move, board));

        board[2][2] = new Pawn(Player.WHITE);

        assertFalse(board[1][2].isValidMove(move, board));

        board[2][2] = null;

        move = new Move(1, 2, 3, 2);

        assertTrue(board[1][2].isValidMove(move, board));

        board[2][2] = new Pawn(Player.WHITE);

        assertFalse(board[1][2].isValidMove(move, board));

        board[2][2] = null;

        board[3][2] = new Pawn(Player.WHITE);

        assertFalse(board[1][2].isValidMove(move, board));

        board[3][2] = null;

        move = new Move(1, 2, 4, 2);

        assertFalse(board[1][2].isValidMove(move, board));

        move = new Move(1, 2, 3, 3);

        assertFalse(board[1][2].isValidMove(move, board));

        move = new Move(1, 2, 2, 3);

        assertFalse(board[1][2].isValidMove(move, board));

        board[2][3] = new Pawn(Player.WHITE);

        assertTrue(board[1][2].isValidMove(move, board));

        board[2][3] = null;

        move = new Move(1, 2, 2, 1);

        assertFalse(board[1][2].isValidMove(move, board));

        board[2][1] = new Pawn(Player.WHITE);

        assertTrue(board[1][2].isValidMove(move, board));

        board[2][1] = null;

        board[1][2] = null;

        board[3][2] = new Pawn(Player.BLACK);

        move = new Move(3, 2, 4, 2);

        assertTrue(board[3][2].isValidMove(move, board));

        board[4][2] = new Pawn(Player.WHITE);

        assertFalse(board[3][2].isValidMove(move, board));

        board[4][2] = null;

        move = new Move(3, 2, 5, 2);

        assertFalse(board[3][2].isValidMove(move, board));
    }

    //Tests movements for White king
    @Test
    public void testKingIsValidWhite(){
        IChessPiece[][] board = new IChessPiece[8][8];

        //Creates new King to move forward and Checks it has been created properly
        board[7][2] = new King(Player.WHITE);
        assertTrue(board[7][2].player() == Player.WHITE);
        assertTrue(board[7][2].type().equals("King"));

        //Checks that moving forward 1 is a valid move
        Move kingForward1 = new Move(7, 2, 6, 2);
        assertTrue(board[7][2].isValidMove(kingForward1, board));
        //Checks that king can't make move if player has piece there
        board[6][2] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingForward1, board));
        //checks that King can still make move with enemy piece there
        board[6][2] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingForward1, board));

        //Checks that moving left 1 is a valid move
        Move kingLeft1 = new Move(7,2,7,1);
        assertTrue(board[7][2].isValidMove(kingLeft1, board));
        //Checks that King can't make move if player has piece there
        board[7][1] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingLeft1, board));
        //Checks that King can still make move with enemy piece there
        board[7][1] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingLeft1, board));

        //Checks that moving right 1 is a valid move
        Move kingRight1 = new Move(7, 2, 7, 3);
        assertTrue(board[7][2].isValidMove(kingRight1, board));
        //Checks that King can't make move if player has piece there
        board[7][3] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingRight1, board));
        //Check that King can still make move with enemy piece there
        board[7][3] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingRight1, board));

        //Checks that moving diagonally forward left is a valid move
        Move kingDiagFrowardLeft = new Move(7, 2, 6, 1);
        assertTrue(board[7][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that King can't make move is player has piece there
        board[6][1] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that King can still make move with enemy piece there
        board[6][1] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingDiagFrowardLeft, board));

        //Checks that moving diagonally forward right is a valid move
        Move kingDiagForwardRight = new Move(7, 2, 6, 3);
        assertTrue(board[7][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can't make move if player has piece there
        board[6][3] = new Pawn (Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can still make move with enemy piece there
        board[6][3] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingDiagForwardRight, board));

        //Removes old King
        board[7][2] = null;
        //Creates new King to move backwards and Checks it has been created properly
        board[6][2] = new King(Player.WHITE);
        assertTrue(board[6][2].player() == Player.WHITE);
        assertTrue(board[6][2].type().equals("King"));

        //Checks that moving back 1 is valid move
        Move kingBack1 = new Move(6, 2, 7, 2);
        assertTrue(board[6][2].isValidMove(kingBack1, board));
        //Checks that king can't make move if player has piece there
        board[7][2] = new Pawn(Player.WHITE);
        assertFalse(board[6][2].isValidMove(kingBack1, board));
        //Checks that king can still make move with enemy piece there
        board[7][2] = new Pawn(Player.BLACK);
        assertTrue(board[6][2].isValidMove(kingBack1, board));

        //Checks that moving Diagonally back left is valid move
        Move kingDiagBackLeft = new Move(6, 2, 7, 1);
        assertTrue(board[6][2].isValidMove(kingDiagBackLeft, board));
        //Checks that King can't make move if player has a piece there
        board[7][1] = new Pawn(Player.WHITE);
        assertFalse(board[6][2].isValidMove(kingDiagBackLeft, board));
        //Checks the King can make move with enemy piece there
        board[7][1] = new Pawn(Player.BLACK);
        assertTrue(board[6][2].isValidMove(kingDiagBackLeft, board));

        //Checks that moving Diagonally back right is valid move
        Move kingDiagBackRight = new Move(6, 2, 7, 3);
        assertTrue(board[6][2].isValidMove(kingDiagBackRight, board));
        //Checks that King can't make move if player has a piece there
        board[7][3] = new Pawn(Player.WHITE);
        assertFalse(board[6][2].isValidMove(kingDiagBackRight, board));
        //Checks that king can make move with enemy piece there\
        board[7][3] = new Pawn(Player.BLACK);
        assertTrue(board[6][2].isValidMove(kingDiagBackRight, board));
    }

    //tests movement for black king
    @Test
    public void testKingIsValidBlack(){
        IChessPiece[][] board = new IChessPiece[8][8];

        //Creates new King to move forward and Checks it has been created properly
        board[1][2] = new King(Player.WHITE);
        assertTrue(board[1][2].player() == Player.WHITE);
        assertTrue(board[1][2].type().equals("King"));

        //Checks that moving forward 1 is a valid move
        Move kingForward1 = new Move(1, 2, 2, 2);
        assertTrue(board[1][2].isValidMove(kingForward1, board));
        //Checks that king can't make move if player has piece there
        board[2][2] = new Pawn(Player.WHITE);
        assertFalse(board[1][2].isValidMove(kingForward1, board));
        //checks that King can still make move with enemy piece there
        board[2][2] = new Pawn(Player.BLACK);
        assertTrue(board[1][2].isValidMove(kingForward1, board));

        //Checks that moving left 1 is a valid move
        Move kingLeft1 = new Move(1,2,1,1);
        assertTrue(board[1][2].isValidMove(kingLeft1, board));
        //Checks that King can't make move if player has piece there
        board[1][1] = new Pawn(Player.WHITE);
        assertFalse(board[1][2].isValidMove(kingLeft1, board));
        //Checks that King can still make move with enemy piece there
        board[1][1] = new Pawn(Player.BLACK);
        assertTrue(board[1][2].isValidMove(kingLeft1, board));

        //Checks that moving right 1 is a valid move
        Move kingRight1 = new Move(1, 2, 1, 3);
        assertTrue(board[1][2].isValidMove(kingRight1, board));
        //Checks that King can't make move if player has piece there
        board[1][3] = new Pawn(Player.WHITE);
        assertFalse(board[1][2].isValidMove(kingRight1, board));
        //Check that King can still make move with enemy piece there
        board[1][3] = new Pawn(Player.BLACK);
        assertTrue(board[1][2].isValidMove(kingRight1, board));

        //Checks that moving diagonally forward left is a valid move
        Move kingDiagFrowardLeft = new Move(1, 2, 2, 1);
        assertTrue(board[1][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that King can't make move is player has piece there
        board[2][1] = new Pawn(Player.WHITE);
        assertFalse(board[1][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that King can still make move with enemy piece there
        board[2][1] = new Pawn(Player.BLACK);
        assertTrue(board[1][2].isValidMove(kingDiagFrowardLeft, board));

        //Checks that moving diagonally forward right is a valid move
        Move kingDiagForwardRight = new Move(1, 2, 2, 3);
        assertTrue(board[1][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can't make move if player has piece there
        board[2][3] = new Pawn (Player.WHITE);
        assertFalse(board[1][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can still make move with enemy piece there
        board[2][3] = new Pawn(Player.BLACK);
        assertTrue(board[1][2].isValidMove(kingDiagForwardRight, board));

        //Removes old King
        board[1][2] = null;
        //Creates new King to move backwards and Checks it has been created properly
        board[2][2] = new King(Player.WHITE);
        assertTrue(board[2][2].player() == Player.WHITE);
        assertTrue(board[2][2].type().equals("King"));

        //Checks that moving back 1 is valid move
        Move kingBack1 = new Move(2, 2, 1, 2);
        assertTrue(board[2][2].isValidMove(kingBack1, board));
        //Checks that king can't make move if player has piece there
        board[1][2] = new Pawn(Player.WHITE);
        assertFalse(board[2][2].isValidMove(kingBack1, board));
        //Checks that king can still make move with enemy piece there
        board[1][2] = new Pawn(Player.BLACK);
        assertTrue(board[2][2].isValidMove(kingBack1, board));

        //Checks that moving Diagonally back left is valid move
        Move kingDiagBackLeft = new Move(2, 2, 1, 1);
        assertTrue(board[2][2].isValidMove(kingDiagBackLeft, board));
        //Checks that King can't make move if player has a piece there
        board[1][1] = new Pawn(Player.WHITE);
        assertFalse(board[2][2].isValidMove(kingDiagBackLeft, board));
        //Checks the King can make move with enemy piece there
        board[1][1] = new Pawn(Player.BLACK);
        assertTrue(board[2][2].isValidMove(kingDiagBackLeft, board));

        //Checks that moving Diagonally back right is valid move
        Move kingDiagBackRight = new Move(2, 2, 1, 3);
        assertTrue(board[2][2].isValidMove(kingDiagBackRight, board));
        //Checks that King can't make move if player has a piece there
        board[1][3] = new Pawn(Player.WHITE);
        assertFalse(board[2][2].isValidMove(kingDiagBackRight, board));
        //Checks that king can make move with enemy piece there\
        board[1][3] = new Pawn(Player.BLACK);
        assertTrue(board[2][2].isValidMove(kingDiagBackRight, board));
    }

}