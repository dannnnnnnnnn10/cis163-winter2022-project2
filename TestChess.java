package Project2;

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

        assertTrue( board[6][2].isValidMove(move, board));

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

    // tests that Pawn's black move logic is working
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

}
