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

    // tests that Rook's move logic is working
    @Test
    public void testRookIsValidMove() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[3][2] = new Rook(Player.WHITE);

        Move move = new Move(3,2,5,2);

        assertTrue(board[3][2].isValidMove(move, board));

        board[4][2] = new Pawn(Player.BLACK);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 3, 0);

        assertTrue(board[3][2].isValidMove(move, board));

        board[3][1] = new Pawn(Player.BLACK);

        board[4][2] = null;

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 3, 7);

        assertTrue(board[3][2].isValidMove(move, board));

        board[3][1] = null;

        board[3][6] = new Pawn(Player.BLACK);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 2);

        assertTrue(board[3][2].isValidMove(move, board));

        board[2][2] = new Pawn(Player.BLACK);

        board[3][6] = new Pawn(Player.BLACK);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 3);

        assertFalse(board[3][2].isValidMove(move, board));

        assertSame("Rook", board[3][2].type());

    }

    // tests that Knight's move logic is working
    @Test
    public void testKnightIsValidMove() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[3][2] = new Knight(Player.WHITE);

        assertSame(board[3][2].type(), "Knight");

        Move move = new Move(3, 2, 5, 3);

        assertTrue(board[3][2].isValidMove(move, board));

        board[5][3] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 5, 1);

        assertTrue(board[3][2].isValidMove(move, board));

        board[5][1] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 1);

        assertTrue(board[3][2].isValidMove(move, board));

        board[1][1] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 3);

        assertTrue(board[3][2].isValidMove(move, board));

        board[1][3] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 4, 4);

        assertTrue(board[3][2].isValidMove(move, board));

        board[4][4] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 4, 0);

        assertTrue(board[3][2].isValidMove(move, board));

        board[4][0] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 2, 4);

        assertTrue(board[3][2].isValidMove(move, board));

        board[2][4] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 2, 0);

        assertTrue(board[3][2].isValidMove(move, board));

        board[2][0] = new Pawn(Player.BLACK);

        assertTrue(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 0);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 0, 0);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 2);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 1, 4);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 4, 3);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 3, 3);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 2, 2);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 5, 0);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 5, 2);

        assertFalse(board[3][2].isValidMove(move, board));

        move = new Move(3, 2, 3, 0);

        assertFalse(board[3][2].isValidMove(move, board));
    }

    //Tests movements for king
    @Test
    public void testKingIsValidMove(){
        //Creates Chess board
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
        //Checks that king can't move more than 1 row forward
        Move kingForward2 = new Move(7, 2, 5, 2);
        assertFalse(board[7][2].isValidMove(kingForward2, board));

        //Checks that moving left 1 is a valid move
        Move kingLeft1 = new Move(7,2,7,1);
        assertTrue(board[7][2].isValidMove(kingLeft1, board));
        //Checks that King can't make move if player has piece there
        board[7][1] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingLeft1, board));
        //Checks that King can still make move with enemy piece there
        board[7][1] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingLeft1, board));
        //Checks that King can't move more than 1 column left
        Move kingLeft2 = new Move(7, 2, 7, 0);
        assertFalse(board[7][2].isValidMove(kingLeft2, board));

        //Checks that moving right 1 is a valid move
        Move kingRight1 = new Move(7, 2, 7, 3);
        assertTrue(board[7][2].isValidMove(kingRight1, board));
        //Checks that King can't make move if player has piece there
        board[7][3] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingRight1, board));
        //Check that King can still make move with enemy piece there
        board[7][3] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingRight1, board));
        //Checks that King can't move more than 1 column right
        Move kingRight2 = new Move(7, 2, 7, 4);
        assertFalse(board[7][2].isValidMove(kingRight2, board));

        //Checks that moving diagonally forward left is a valid move
        Move kingDiagFrowardLeft = new Move(7, 2, 6, 1);
        assertTrue(board[7][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that King can't make move is player has piece there
        board[6][1] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that King can still make move with enemy piece there
        board[6][1] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingDiagFrowardLeft, board));
        //Checks that king can't move more than 1 diagonal left
        Move kingDiagForwardLeft2 = new Move(7, 2, 5, 0);
        assertFalse(board[7][2].isValidMove(kingDiagForwardLeft2, board));

        //Checks that moving diagonally forward right is a valid move
        Move kingDiagForwardRight = new Move(7, 2, 6, 3);
        assertTrue(board[7][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can't make move if player has piece there
        board[6][3] = new Pawn (Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can still make move with enemy piece there
        board[6][3] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingDiagForwardRight, board));
        //Checks that King can't moe more than 1 diagonal right
        Move kingDiagForwardRight2 = new Move(7, 2, 5, 4);
        assertFalse(board[7][2].isValidMove(kingDiagForwardRight2, board));

        //Removes old King
        board[7][2] = null;
        //Creates new King to move backwards and Checks it has been created properly
        board[5][2] = new King(Player.WHITE);
        assertTrue(board[5][2].player() == Player.WHITE);
        assertTrue(board[5][2].type().equals("King"));

        //Checks that moving back 1 is valid move
        Move kingBack1 = new Move(5, 2, 6, 2);
        assertTrue(board[5][2].isValidMove(kingBack1, board));
        //Checks that king can't make move if player has piece there
        board[6][2] = new Pawn(Player.WHITE);
        assertFalse(board[5][2].isValidMove(kingBack1, board));
        //Checks that king can still make move with enemy piece there
        board[6][2] = new Pawn(Player.BLACK);
        assertTrue(board[5][2].isValidMove(kingBack1, board));
        //Checks that king can't move more than 1 row back
        Move kingBack2 = new Move(5, 2, 7, 2);
        assertFalse(board[5][2].isValidMove(kingBack2, board));

        //Checks that moving Diagonally back left is valid move
        Move kingDiagBackLeft = new Move(5, 2, 6, 1);
        assertTrue(board[5][2].isValidMove(kingDiagBackLeft, board));
        //Checks that King can't make move if player has a piece there
        board[6][1] = new Pawn(Player.WHITE);
        assertFalse(board[5][2].isValidMove(kingDiagBackLeft, board));
        //Checks the King can make move with enemy piece there
        board[6][1] = new Pawn(Player.BLACK);
        assertTrue(board[5][2].isValidMove(kingDiagBackLeft, board));
        //Checks that King can't move more than 1 diagonal back left
        Move kingDiagBackLeft2 = new Move(5, 2, 7, 0);
        assertFalse(board[5][2].isValidMove(kingDiagBackLeft2, board));

        //Checks that moving Diagonally back right is valid move
        Move kingDiagBackRight = new Move(5, 2, 6, 3);
        assertTrue(board[5][2].isValidMove(kingDiagBackRight, board));
        //Checks that King can't make move if player has a piece there
        board[6][3] = new Pawn(Player.WHITE);
        assertFalse(board[5][2].isValidMove(kingDiagBackRight, board));
        //Checks that king can make move with enemy piece there
        board[6][3] = new Pawn(Player.BLACK);
        assertTrue(board[5][2].isValidMove(kingDiagBackRight, board));
        //Checks that King can't move more than 1 diagonal back right
        Move kingDiagBackRight2 = new Move(5,2, 7, 4);
        assertFalse(board[5][2].isValidMove(kingDiagBackRight2, board));
    }
    @Test
    //Tests queens movement
    public void testQueenIsValidMove(){
        //Creates Chess board
        IChessPiece[][] board = new IChessPiece[8][8];

        //Creates new Queen to move forward and Checks it has been created properly
        board[4][4] = new Queen(Player.WHITE);
        assertTrue(board[4][4].player() == Player.WHITE);
        assertTrue(board[4][4].type().equals("Queen"));

        //Checks that moving forward 1 is valid move
        Move queenForward1 = new Move(4, 4, 5, 4);
        assertTrue(board[4][4].isValidMove(queenForward1, board));

    }

    @Test
    //Tests Bishops movement
    public void testBishopIsValid(){
        //Creates chess board
        IChessPiece[][] board = new IChessPiece[8][8];

        //Creates new Bishop to move forward and checks it has been created properly
        board[4][4] = new Bishop(Player.WHITE);
        assertEquals(board[4][4].player(),  Player.WHITE);
        assertEquals(board[4][4].type(), ("Bishop"));

        //Checks that moving diagonal forward left 1 is valid move
        Move bishopDiagForwardLeft1 = new Move(4, 4, 3, 3);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardLeft1, board));
        //Checks that moving diagonal forward left 3 is a valid move
        Move bishopDiagForwardLeft3 = new Move(4, 4, 1, 1);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardLeft3, board));
        //Checks that moving to a spot with an enemy piece is valid
        board[3][3] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardLeft1, board));
        //Checks that move is not valid if player already has piece there
        board[3][3] = null;
        board[1][1] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagForwardLeft3, board));
        //Checks that move is not valid if bishop passes through opponent piece
        board[1][1] = null;
        board[3][3] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(bishopDiagForwardLeft3, board));
        //Checks that move is not valid if bishop passes through players piece
        board[3][3] = null;
        board[3][3] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagForwardLeft3, board));

        //Checks that moving diagonal forward right 1 is valid move
        Move bishopDiagForwardRight1 = new Move(4, 4, 3, 5);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardRight1, board));
        //Checks that moving diagonal forward right 3 is valid move
        Move bishopDiagForwardRight3 = new Move(4, 4, 1, 7);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardRight3, board));
        //Checks that moving to a spot with an enemy piece is valid
        board[3][5] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardRight1, board));
        //Checks that move is not valid if player already has a piece there
        board[3][5] = null;
        board[1][7] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagForwardRight3, board));
        //Checks that move is not valid if bishop passes through opponent piece
        board[1][7] = null;
        board[3][5] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(bishopDiagForwardRight3, board));
        //Checks that move is not valid if bishop passes through players piece
        board[3][5] = null;
        board[3][5] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagForwardRight3, board));

        //Checks that moving diagonal back left 1 is valid move
        Move bishopDiagBackLeft1 = new Move(4, 4, 5, 3);
        assertTrue(board[4][4].isValidMove(bishopDiagBackLeft1, board));
        //Checks that moving diagonal back left 3 is valid move
        Move bishopDiagBackLeft3 = new Move(4, 4, 7, 1);
        assertTrue(board[4][4].isValidMove(bishopDiagBackLeft3, board));
        //Checks that moving to a spot with an enemy piece is valid
        board[5][3] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(bishopDiagBackLeft1, board));
        //Checks that move is not valid if player already has a piece there
        board[5][3] = null;
        board[7][1] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagBackLeft3, board));
        //Checks that bishop cannot pass through opponent piece
        board[7][1] = null;
        board[5][3] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(bishopDiagBackLeft3, board));
        //Checks that bishop cannot pass through players piece
        board[5][3] = null;
        board[5][3] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagBackLeft3, board));

        //Checks that moving diagonal back right 1 is valid move
        Move bishopDiagBackRight1 = new Move(4, 4, 5, 5);
        assertTrue(board[4][4].isValidMove(bishopDiagBackRight1, board));
        //Checks that moving diagonal back right 3 is valid move
        Move bishopDiagBackRight3 = new Move(4, 4, 7, 7);
        assertTrue(board[4][4].isValidMove(bishopDiagBackRight3, board));
        //Checks that moving to a spot with enemy piece is valid
        board[5][5] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(bishopDiagBackRight1, board));
        //Checks that move is not valid if player already has a piece there
        board[5][5] = null;
        board[7][7] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagBackRight3, board));
        //Checks that bishop cannot pass through opponent piece
        board[7][7] = null;
        board[5][5] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(bishopDiagBackRight3, board));
        //Checks that bishop cannot pass through players piece
        board[5][5] = null;
        board[5][5] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(bishopDiagBackRight3, board));

        //Checks that the bishop cannot move straight forward at all
        Move bishopForward1 = new Move(4, 4, 3, 4);
        assertFalse(board[4][4].isValidMove(bishopForward1, board));
        Move bishopForward3 = new Move(4, 4, 1, 4);
        assertFalse(board[4][4].isValidMove(bishopForward3, board));
        //Checks that the bishop cannot move straight backwards at all
        Move bishopBack1 = new Move(4, 4, 5, 4);
        assertFalse(board[4][4].isValidMove(bishopBack1, board));
        Move bishopBack3 = new Move(4, 4, 7, 4);
        assertFalse(board[4][4].isValidMove(bishopBack3, board));
        //Checks that the bishop cannot move straight left at all
        Move bishopLeft1 = new Move(4, 4, 4, 3);
        assertFalse(board[4][4].isValidMove(bishopLeft1, board));
        Move bishopLeft3 = new Move(4, 4, 4, 1);
        assertFalse(board[4][4].isValidMove(bishopLeft3, board));
        //Checks that the bishop cannot move straight right at all
        Move bishopRight1 = new Move(4, 4, 4, 5);
        assertFalse(board[4][4].isValidMove(bishopRight1, board));
        Move bishopRight3 = new Move(4, 4, 4, 7);
        assertFalse(board[4][4].isValidMove(bishopRight3, board));
    }

}
