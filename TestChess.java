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

        assertTrue(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][2] = new Pawn(Player.BLACK);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][2] = null;

        move = new Move(6, 2, 4, 2);

        assertTrue(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][2] = new Pawn(Player.BLACK);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][2] = null;

        board[4][2] = new Pawn(Player.BLACK);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        board[4][2] = null;

        move = new Move(6, 2, 3, 2);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        move = new Move(6, 2, 4, 3);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        move = new Move(6, 2, 5, 3);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][3] = new Pawn(Player.BLACK);

        assertTrue(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][3] = null;

        move = new Move(6, 2, 5, 1);

        assertFalse(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][1] = new Pawn(Player.BLACK);

        assertTrue(((Pawn) board[6][2]).isValidMove(move, board));

        board[5][1] = null;

        board[6][2] = null;

        board[4][2] = new Pawn(Player.WHITE);

        move = new Move(4, 2, 3, 2);

        assertTrue(((Pawn) board[4][2]).isValidMove(move, board));

        board[3][2] = new Pawn(Player.BLACK);

        assertFalse(((Pawn) board[4][2]).isValidMove(move, board));

        board[3][2] = null;

        move = new Move(4, 2, 2, 2);

        assertFalse(((Pawn) board[4][2]).isValidMove(move, board));
    }

    @Test
    public void testPawnIsValidMoveBlack() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[1][2] = new Pawn(Player.BLACK);

        assertTrue(board[1][2].player() == Player.BLACK);

        Move move = new Move(1, 2, 2, 2);

        assertTrue(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][2] = new Pawn(Player.WHITE);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][2] = null;

        move = new Move(1, 2, 3, 2);

        assertTrue(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][2] = new Pawn(Player.WHITE);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][2] = null;

        board[3][2] = new Pawn(Player.WHITE);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        board[3][2] = null;

        move = new Move(1, 2, 4, 2);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        move = new Move(1, 2, 3, 3);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        move = new Move(1, 2, 2, 3);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][3] = new Pawn(Player.WHITE);

        assertTrue(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][3] = null;

        move = new Move(1, 2, 2, 1);

        assertFalse(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][1] = new Pawn(Player.WHITE);

        assertTrue(((Pawn) board[1][2]).isValidMove(move, board));

        board[2][1] = null;

        board[1][2] = null;

        board[3][2] = new Pawn(Player.BLACK);

        move = new Move(3, 2, 4, 2);

        assertTrue(((Pawn) board[3][2]).isValidMove(move, board));

        board[4][2] = new Pawn(Player.WHITE);

        assertFalse(((Pawn) board[3][2]).isValidMove(move, board));

        board[4][2] = null;

        move = new Move(3, 2, 5, 2);

        assertFalse(((Pawn) board[3][2]).isValidMove(move, board));
    }

}
