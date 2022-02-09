package Project2;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestChess {

    @Test
    public void testChessPiece() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[6][2] = new Pawn(Player.WHITE);

        Move move = new Move(6, 2, 6, 2);

        assertFalse(board[6][2].isValidMove(move, board));

        move = new Move(6, 2, 5, 6);

        board[5][6] = new Queen(Player.WHITE);

        assertFalse(board[6][2].isValidMove(move, board));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testChessPieceOOB() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[6][2] = new Pawn(Player.WHITE);

        Move move = new Move(5, 2, 6, -1);

        board[6][2].isValidMove(move, board);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChessPieceIllegalArg() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[6][2] = new Pawn(Player.WHITE);

        Move move = new Move(5, 2, 6, 2);

        board[6][2].isValidMove(move, board);
    }

    // tests that Pawn's white move logic is working
    @Test
    public void testPawnIsValidMoveWhite() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[6][2] = new Pawn(Player.WHITE);

        assertSame(Player.WHITE, board[6][2].player());

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

        assertSame(Player.BLACK, board[1][2].player());

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

    @Test
    public void testChessModelSimple() {
        ChessModel model = new ChessModel();

        assertSame(8, model.numColumns());
        assertSame(8, model.numRows());
        assertSame(Player.WHITE, model.currentPlayer());

        model.setNextPlayer();
        assertSame(Player.BLACK, model.currentPlayer());

        assertSame("Pawn", model.pieceAt(6,0).type());
        assertSame(Player.BLACK, model.pieceAt(1,5).player());

        model.setPiece(4, 3, new Rook(Player.WHITE));
        assertEquals("Rook", model.pieceAt(4, 3).type());
        assertSame(Player.WHITE, model.pieceAt(4, 3).player());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testChessModelIsValidMoveOOB() {
        ChessModel model = new ChessModel();

        Move move = new Move(5, 3, 10, 2);

        model.isValidMove(move);

    }

    @Test
    public void testChessModelIsValidMove() {
        ChessModel model = new ChessModel();

        Move move = new Move(5, 3, 4, 2);

        assertFalse(model.isValidMove(move));

        move = new Move(1, 0, 2, 0);

        assertFalse(model.isValidMove(move));

        move = new Move(6, 0, 4, 0);

        assertTrue(model.isValidMove(move));

        model.setPiece(5, 2, new Rook(Player.BLACK));

        model.setPiece(5, 3, new Pawn(Player.WHITE));

        model.setPiece(5, 4, new King(Player.WHITE));

        model.setPiece(7, 4, null);

        move = new Move(5, 3, 4, 3);

        assertFalse(model.isValidMove(move));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testChessModelMoveOOB() {
        ChessModel model = new ChessModel();

        Move move = new Move(5, 3, 10, 2);

        model.move(move);

    }

    @Test
    public void testChessModelMoveAndUndo() {
        ChessModel model = new ChessModel();

        Move move = new Move(6, 2, 5, 2);

        model.move(move);

        assertNull(model.pieceAt(6,2));
        assertSame("Pawn", model.pieceAt(5, 2).type());
        assertSame(Player.WHITE, model.pieceAt(5, 2).player());
        assertSame(Player.BLACK, model.currentPlayer());

        model.undo();
        assertNull(model.pieceAt(5,2));
        assertSame("Pawn", model.pieceAt(6, 2).type());
        assertSame(Player.WHITE, model.pieceAt(6, 2).player());
        assertSame(Player.WHITE, model.currentPlayer());

        model = new ChessModel();
        model.setPiece(6,3, null);
        move = new Move(7, 3, 4, 3);
        model.move(move);
        assertSame("Queen", model.pieceAt(4,3).type());
        move = new Move(7, 2, 5, 4);
        model.move(move);
        assertSame("Bishop", model.pieceAt(5, 4).type());

    }

    @Test
    public void testChessModelCanPromote() {
        ChessModel model = new ChessModel();

        model.setPiece(0,5,null);
        model.setPiece(1,5,new Pawn(Player.WHITE));

        Move move = new Move(1,5,0,5);

        model.move(move);

        assertSame("Queen", model.pieceAt(0,5).type());

        model.undo();

        assertNull(model.pieceAt(0,5));
        assertSame("Pawn", model.pieceAt(1,5).type());

        model.setPiece(7,5,null);
        model.setPiece(6,5,new Pawn(Player.BLACK));

        move = new Move(6,5,7,5);

        model.move(move);

        assertSame("Queen", model.pieceAt(7,5).type());

        model.undo();

        assertNull(model.pieceAt(7, 5));
        assertSame("Pawn", model.pieceAt(6,5).type());
    }

    @Test
    public void testChessModelInCheck() {
        ChessModel model = new ChessModel();

        model.setPiece(6, 4, null);

        model.setPiece(3, 4, new Queen(Player.BLACK));

        assertTrue(model.inCheck(Player.WHITE));
        assertFalse(model.inCheck(Player.BLACK));
    }

    @Test
    public void testChessModelIsComplete() {
        ChessModel model = new ChessModel();

        assertFalse(model.isComplete());

        model.setPiece(6, 4, null);

        model.setPiece(5, 4, new Rook(Player.BLACK));

        assertFalse(model.isComplete());

        model.setPiece(4, 4, new Rook(Player.BLACK));

        model.setPiece(5, 4, null);

        model.setPiece(7, 3, new Pawn(Player.WHITE));

        model.setPiece(7, 6, null);

        model.setPiece(7, 5, new Pawn(Player.WHITE));

        assertTrue(model.isComplete());

    }

    @Test
    public void testChessModelEnPassant() {
        ChessModel model = new ChessModel();

        model.setPiece(4,3, new Pawn(Player.BLACK));
        Move move = new Move(6, 2, 4, 2);
        model.move(move);
        move = new Move(4, 3, 5, 2);
        model.move(move);
        assertNull(model.pieceAt(4, 2));
        assertSame("Pawn", model.pieceAt(5, 2).type());

        model = new ChessModel();

        model.setPiece(4,3, new Rook(Player.BLACK));
        move = new Move(6, 2, 4, 2);
        model.move(move);
        move = new Move(4, 3, 5, 2);
        assertFalse(model.isValidMove(move));

        model = new ChessModel();

        model.setPiece(4,3, new Pawn(Player.BLACK));
        move = new Move(6, 4, 4, 4);
        model.move(move);
        move = new Move(4, 3, 5, 4);
        model.move(move);
        assertNull(model.pieceAt(4, 4));
        assertSame("Pawn", model.pieceAt(5, 4).type());

        model = new ChessModel();

        model.setPiece(3,3, new Pawn(Player.WHITE));
        model.setNextPlayer();
        move = new Move(1, 4, 3, 4);
        model.move(move);
        move = new Move(3, 3, 2, 4);
        model.move(move);
        assertNull(model.pieceAt(3, 4));
        assertSame("Pawn", model.pieceAt(2, 4).type());

        model = new ChessModel();

        model.setPiece(3,3, new Pawn(Player.WHITE));
        model.setNextPlayer();
        move = new Move(1, 2, 3, 2);
        model.move(move);
        move = new Move(3, 3, 2, 2);
        model.move(move);
        assertNull(model.pieceAt(3, 2));
        assertSame("Pawn", model.pieceAt(2, 2).type());
        model.undo();
        assertTrue(model.isValidMove(move));

    }

    @Test
    public void testCastling() {
        ChessModel model = new ChessModel();

        model.setPiece(7,6,null);
        model.setPiece(7,5, new Pawn(Player.BLACK));

        Move move = new Move(7,4,7,6);

        assertFalse(model.isValidMove(move));

        model.setPiece(7,5, null);

        assertTrue(model.isValidMove(move));

        model.move(move);
        assertSame("Rook", model.pieceAt(7,5).type());
        assertSame("King", model.pieceAt(7,6).type());
        assertNull(model.pieceAt(7,4));
        assertNull(model.pieceAt(7,7));

        model.undo();
        assertSame("King", model.pieceAt(7,4).type());
        assertSame("Rook", model.pieceAt(7,7).type());
        assertNull(model.pieceAt(7,5));
        assertNull(model.pieceAt(7,6));

        model.setPiece(6,5,null);
        model.setPiece(4,5, new Rook(Player.BLACK));
        assertFalse(model.isValidMove(move));

        model.setPiece(6,5,new Pawn(Player.WHITE));

        model.setPiece(6,4, null);
        model.setPiece(4,4, new Rook(Player.BLACK));
        assertFalse(model.isValidMove(move));

        model.setPiece(4,4, new Pawn(Player.WHITE));

        move = new Move(7,4,7,5);
        model.move(move);
        move = new Move(7,5,7,4);
        model.move(move);
        move = new Move(7,4,7,6);
        assertFalse(model.isValidMove(move));

        model.undo();
        model.undo();

        move = new Move(7,7,7,5);
        model.move(move);
        move = new Move(7,5,7,7);
        model.move(move);
        move = new Move(7,4,7,6);
        assertFalse(model.isValidMove(move));

        model.undo();
        model.undo();
        assertTrue(model.isValidMove(move));

        model = new ChessModel();

        model.setPiece(7,3, null);
        model.setPiece(7,2, null);
        model.setPiece(7,1, new Pawn(Player.BLACK));

        move = new Move(7,4,7,2);

        assertFalse(model.isValidMove(move));

        model.setPiece(7,1, null);

        assertTrue(model.isValidMove(move));

        model.move(move);
        assertSame("Rook", model.pieceAt(7,3).type());
        assertSame("King", model.pieceAt(7,2).type());
        assertNull(model.pieceAt(7,4));
        assertNull(model.pieceAt(7,0));

        model.undo();
        assertSame("King", model.pieceAt(7,4).type());
        assertSame("Rook", model.pieceAt(7,0).type());
        assertNull(model.pieceAt(7,3));
        assertNull(model.pieceAt(7,2));

        move = new Move(7,0,7,3);
        model.move(move);
        move = new Move(7,3,7,0);
        model.move(move);
        move = new Move(7,4,7,2);
        assertFalse(model.isValidMove(move));

        model.undo();
        model.undo();
        assertTrue(model.isValidMove(move));

        model = new ChessModel();
        model.setNextPlayer();

        model.setPiece(0,6,null);
        model.setPiece(0,5, new Pawn(Player.WHITE));

        move = new Move(0,4,0,6);

        assertFalse(model.isValidMove(move));

        model.setPiece(0,5, null);

        assertTrue(model.isValidMove(move));

        model.move(move);
        assertSame("Rook", model.pieceAt(0,5).type());
        assertSame("King", model.pieceAt(0,6).type());
        assertNull(model.pieceAt(0,4));
        assertNull(model.pieceAt(0,7));

        model.undo();
        assertSame("King", model.pieceAt(0,4).type());
        assertSame("Rook", model.pieceAt(0,7).type());
        assertNull(model.pieceAt(0,5));
        assertNull(model.pieceAt(0,6));

        model.setPiece(1,5,null);
        model.setPiece(4,5, new Rook(Player.WHITE));
        assertFalse(model.isValidMove(move));

        model.setPiece(1,5,new Pawn(Player.BLACK));

        move = new Move(0,4,0,5);
        model.move(move);
        move = new Move(0,5,0,4);
        model.move(move);
        move = new Move(0,4,0,6);
        assertFalse(model.isValidMove(move));

        model.undo();
        model.undo();
        assertTrue(model.isValidMove(move));

        move = new Move(0,7,0,5);
        model.move(move);
        move = new Move(0,5,0,7);
        model.move(move);
        move = new Move(0,4,0,6);
        assertFalse(model.isValidMove(move));

        model.undo();
        model.undo();
        assertTrue(model.isValidMove(move));

        model = new ChessModel();
        model.setNextPlayer();

        model.setPiece(0,3, null);
        model.setPiece(0,2, null);
        model.setPiece(0,1, new Pawn(Player.BLACK));

        move = new Move(0,4,0,2);

        assertFalse(model.isValidMove(move));

        model.setPiece(0,1, null);

        assertTrue(model.isValidMove(move));

        model.move(move);
        assertSame("Rook", model.pieceAt(0,3).type());
        assertSame("King", model.pieceAt(0,2).type());
        assertNull(model.pieceAt(0,4));
        assertNull(model.pieceAt(0,0));

        model.undo();
        assertSame("King", model.pieceAt(0,4).type());
        assertSame("Rook", model.pieceAt(0,0).type());
        assertNull(model.pieceAt(0,3));
        assertNull(model.pieceAt(0,2));

        move = new Move(0,0,0,3);
        model.move(move);
        move = new Move(0,3,0,0);
        model.move(move);
        move = new Move(0,4,0,2);
        assertFalse(model.isValidMove(move));

        model.undo();
        model.undo();
        assertTrue(model.isValidMove(move));

    }

}
