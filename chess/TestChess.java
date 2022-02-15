package chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestChess {


    // tests ChessPiece isValidMove logic
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

    // tests ChessPiece giving oob logic
    @Test(expected = IndexOutOfBoundsException.class)
    public void testChessPieceOOB() {
        IChessPiece[][] board = new IChessPiece[8][8];

        board[6][2] = new Pawn(Player.WHITE);

        Move move = new Move(5, 2, 6, -1);

        board[6][2].isValidMove(move, board);
    }

    //tests ChessPiece giving illegal argument
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

    // test simple model methods
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

    // test if moves are OOB
    @Test(expected = IndexOutOfBoundsException.class)
    public void testChessModelIsValidMoveOOB() {
        ChessModel model = new ChessModel();

        Move move = new Move(5, 3, 10, 2);

        model.isValidMove(move);

    }

    // tests basic isValidMove method
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

    // tests move with OOB move data
    @Test(expected = IndexOutOfBoundsException.class)
    public void testChessModelMoveOOB() {
        ChessModel model = new ChessModel();

        Move move = new Move(5, 3, 10, 2);

        model.move(move);

    }

    // tests basic move and undo functionality
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

    // tests inCheck method
    @Test
    public void testChessModelInCheck() {
        ChessModel model = new ChessModel();

        model.setPiece(6, 4, null);

        model.setPiece(3, 4, new Queen(Player.BLACK));

        assertTrue(model.inCheck(Player.WHITE));
        assertFalse(model.inCheck(Player.BLACK));
    }

    // tests isComplete method
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

    // tests logic in isEnPassant, isValidMove, move, and undo
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

    // tests logic in isCastling, isValidMove, move, and undo
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

    // test if pawns can promote
    @Test
    public void testChessModelCanPromote() {
        ChessModel model = new ChessModel();

        model.setPiece(0,5,null);
        model.setPiece(1,5,new Pawn(Player.WHITE));

        Move move = new Move(1,5,0,5);

        model.move(move);

        assertTrue(model.canPromote());

        model.promote("Queen");

        assertSame("Queen", model.pieceAt(0,5).type());

        model.undo();

        assertNull(model.pieceAt(0,5));
        assertSame("Pawn", model.pieceAt(1,5).type());

        model.setPiece(7,5,null);
        model.setPiece(6,5,new Pawn(Player.BLACK));

        move = new Move(6,5,7,5);

        model.move(move);

        model.promote("Bishop");

        assertSame("Bishop", model.pieceAt(7,5).type());

        model.undo();

        assertNull(model.pieceAt(7, 5));
        assertSame("Pawn", model.pieceAt(6,5).type());
    }

    //Tests movements for king
    @Test
    public void testKingIsValidMove(){
        //Creates Chess board
        IChessPiece[][] board = new IChessPiece[8][8];

        //Creates new King to move up and Checks it has been created properly
        board[7][2] = new King(Player.WHITE);
        assertTrue(board[7][2].player() == Player.WHITE);
        assertTrue(board[7][2].type().equals("King"));

        //Checks that moving up 1 is a valid move
        Move kingUp1 = new Move(7, 2, 6, 2);
        assertTrue(board[7][2].isValidMove(kingUp1, board));
        //Checks that king can't make move if player has piece there
        board[6][2] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingUp1, board));
        //checks that King can still make move with enemy piece there
        board[6][2] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingUp1, board));
        //Checks that king can't move more than 1 row up
        Move kingUp2 = new Move(7, 2, 5, 2);
        assertFalse(board[7][2].isValidMove(kingUp2, board));
        board[6][2] = null;

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
        board[7][1] = null;

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
        board[7][3] = null;

        //Checks that moving up left 1 is a valid move
        Move kingUpLeft1 = new Move(7, 2, 6, 1);
        assertTrue(board[7][2].isValidMove(kingUpLeft1, board));
        //Checks that King can't make move is player has piece there
        board[6][1] = new Pawn(Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingUpLeft1, board));
        //Checks that King can still make move with enemy piece there
        board[6][1] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingUpLeft1, board));
        //Checks that king can't move more than 1 up left
        Move kingUpLeft2 = new Move(7, 2, 5, 0);
        assertFalse(board[7][2].isValidMove(kingUpLeft2, board));
        board[6][1] = null;

        //Checks that moving up right 1 is a valid move
        Move kingUpRight1 = new Move(7, 2, 6, 3);
        assertTrue(board[7][2].isValidMove(kingUpRight1, board));
        //Checks that King can't make move if player has piece there
        board[6][3] = new Pawn (Player.WHITE);
        assertFalse(board[7][2].isValidMove(kingUpRight1, board));
        //Checks that King can still make move with enemy piece there
        board[6][3] = new Pawn(Player.BLACK);
        assertTrue(board[7][2].isValidMove(kingUpRight1, board));
        //Checks that King can't move more than 1 up right
        Move kingUpRight2 = new Move(7, 2, 5, 4);
        assertFalse(board[7][2].isValidMove(kingUpRight2, board));
        board[6][3] = null;

        //Removes old King
        board[7][2] = null;
        //Creates new King to move up and Checks it has been created properly
        board[5][2] = new King(Player.WHITE);
        assertTrue(board[5][2].player() == Player.WHITE);
        assertTrue(board[5][2].type().equals("King"));

        //Checks that moving down 1 is valid move
        Move kingDown1 = new Move(5, 2, 6, 2);
        assertTrue(board[5][2].isValidMove(kingDown1, board));
        //Checks that king can't make move if player has piece there
        board[6][2] = new Pawn(Player.WHITE);
        assertFalse(board[5][2].isValidMove(kingDown1, board));
        //Checks that king can still make move with enemy piece there
        board[6][2] = new Pawn(Player.BLACK);
        assertTrue(board[5][2].isValidMove(kingDown1, board));
        //Checks that king can't move more than 1 row down
        Move kingDown2 = new Move(5, 2, 7, 2);
        assertFalse(board[5][2].isValidMove(kingDown2, board));

        //Checks that moving down left is valid move
        Move kingDownLeft1 = new Move(5, 2, 6, 1);
        assertTrue(board[5][2].isValidMove(kingDownLeft1, board));
        //Checks that King can't make move if player has a piece there
        board[6][1] = new Pawn(Player.WHITE);
        assertFalse(board[5][2].isValidMove(kingDownLeft1, board));
        //Checks the King can make move with enemy piece there
        board[6][1] = new Pawn(Player.BLACK);
        assertTrue(board[5][2].isValidMove(kingDownLeft1, board));
        //Checks that King can't move more than 1 down left
        Move kingDownLeft2 = new Move(5, 2, 7, 0);
        assertFalse(board[5][2].isValidMove(kingDownLeft2, board));

        //Checks that moving down right is valid move
        Move kingDownRight1 = new Move(5, 2, 6, 3);
        assertTrue(board[5][2].isValidMove(kingDownRight1, board));
        //Checks that King can't make move if player has a piece there
        board[6][3] = new Pawn(Player.WHITE);
        assertFalse(board[5][2].isValidMove(kingDownRight1, board));
        //Checks that king can make move with enemy piece there
        board[6][3] = new Pawn(Player.BLACK);
        assertTrue(board[5][2].isValidMove(kingDownRight1, board));
        //Checks that King can't move more than 1 down right
        Move kingDownRight2 = new Move(5,2, 7, 4);
        assertFalse(board[5][2].isValidMove(kingDownRight2, board));
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

        //Checks that moving down 1 is valid move
        Move queenDown1 = new Move(4, 4, 5, 4);
        assertTrue(board[4][4].isValidMove(queenDown1, board));
        //Checks that moving down 3 is valid move
        Move queenDown3 = new Move(4, 4, 7, 4);
        assertTrue(board[4][4].isValidMove(queenDown3, board));
        //Checks that queen can move to spot with opponent piece
        board[5][4] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenDown1, board));
        //Checks that queen cannot move to spot with player piece
        board[5][4] = null;
        board[5][4] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenDown1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenDown3, board));
        //Checks that queen cannot move through opponent piece
        board[5][4] = null;
        board[5][4] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenDown3, board));
        board[5][4] = null;

        //Checks that moving down left 1 is valid move
        Move queenDownLeft1 = new Move(4, 4, 5, 3);
        assertTrue(board[4][4].isValidMove(queenDownLeft1, board));
        //Checks that moving down left 3 is valid move
        Move queenDownLeft3 = new Move(4, 4, 7, 1);
        assertTrue(board[4][4].isValidMove(queenDownLeft3, board));
        //Checks that queen can move to spot with opponent piece
        board[5][3] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenDownLeft1, board));
        //Checks that queen cannot move to spot with player piece
        board[5][3] = null;
        board[5][3] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenDownLeft1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenDownLeft3, board));
        //Checks that queen cannot move through opponent piece
        board[5][3] = null;
        board[5][3] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenDownLeft3, board));
        board[5][3] = null;

        //Checks that moving down right 1 is valid move
        Move queenDownRight1 = new Move(4, 4, 5, 5);
        assertTrue(board[4][4].isValidMove(queenDownRight1, board));
        //Checks that moving down right 3 is a valid move
        Move queenDownRight3 = new Move(4, 4, 7, 7);
        assertTrue(board[4][4].isValidMove(queenDownRight3, board));
        //Checks that queen can move to spot with opponent piece
        board[5][5] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenDownRight1, board));
        //Checks that queen cannot move to spot with player piece
        board[5][5] = null;
        board[5][5] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenDownRight1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenDownRight3, board));
        //Checks that queen cannot move through opponent piece
        board[5][5] = null;
        board[5][5] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenDownRight3, board));
        board[5][5] = null;

        //Checks that moving left 1 is valid move
        Move queenLeft1 = new Move(4, 4, 4, 3);
        assertTrue(board[4][4].isValidMove(queenLeft1, board));
        //Checks that moving left 3 is valid move
        Move queenLeft3 = new Move(4, 4, 4, 1);
        assertTrue(board[4][4].isValidMove(queenLeft3, board));
        //Checks that queen can move to spot with opponent piece
        board[4][3] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenLeft1, board));
        //Checks that queen cannot move to spot with player piece
        board[4][3] = null;
        board[4][3] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenLeft1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenLeft3, board));
        //Checks that queen cannot move through opponent piece
        board[4][3] = null;
        board[4][3] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenLeft3, board));
        board[4][3] = null;

        //Checks that moving right 1 is valid move
        Move queenRight1 = new Move(4, 4, 4, 5);
        assertTrue(board[4][4].isValidMove(queenRight1, board));
        //Checks that moving left 3 is valid move
        Move queenRight3 = new Move(4, 4, 4, 7);
        assertTrue(board[4][4].isValidMove(queenRight3, board));
        //Checks that queen can move to spot with opponent piece
        board[4][5] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenRight1, board));
        //Checks that queen cannot move to spot with player piece
        board[4][5] = null;
        board[4][5] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenRight1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenRight3, board));
        //Checks that queen cannot move through opponent piece
        board[4][5] = null;
        board[4][5] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenRight3, board));
        board[4][5] = null;

        //Checks that moving up left 1 is valid move
        Move queenUpLeft1 = new Move(4, 4, 3, 3);
        assertTrue(board[4][4].isValidMove(queenUpLeft1, board));
        //Checks that moving down left 3 is valid move
        Move queenUpLeft3 = new Move(4, 4, 1, 1);
        assertTrue(board[4][4].isValidMove(queenUpLeft3, board));
        //Checks that queen can move to spot with opponent piece
        board[3][3] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenUpLeft1, board));
        //Checks that queen cannot move to spot with player piece
        board[3][3] = null;
        board[3][3] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenUpLeft1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenUpLeft3, board));
        //Checks that queen cannot move through opponent piece
        board[3][3] = null;
        board[3][3] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenUpLeft3, board));
        board[3][3] = null;

        //Checks that moving up right 1 is valid move
        Move queenUpRight1 = new Move(4, 4, 3, 5);
        assertTrue(board[4][4].isValidMove(queenUpRight1, board));
        //Checks that moving down right 3 is a valid move
        Move queenUpRight3 = new Move(4, 4, 1, 7);
        assertTrue(board[4][4].isValidMove(queenUpRight3, board));
        //Checks that queen can move to spot with opponent piece
        board[3][5] = new Pawn(Player.BLACK);
        assertTrue(board[4][4].isValidMove(queenUpRight1, board));
        //Checks that queen cannot move to spot with player piece
        board[3][5] = null;
        board[3][5] = new Pawn(Player.WHITE);
        assertFalse(board[4][4].isValidMove(queenUpRight1, board));
        //Checks that queen cannot move through player piece
        assertFalse(board[4][4].isValidMove(queenUpRight3, board));
        //Checks that queen cannot move through opponent piece
        board[3][5] = null;
        board[3][5] = new Pawn(Player.BLACK);
        assertFalse(board[4][4].isValidMove(queenUpRight3, board));
        board[3][5] = null;

        //Checking obvious invalid moves
        Move invalid1 = new Move(4, 4, 3, 7);
        assertFalse(board[4][4].isValidMove(invalid1, board));
        Move invalid2 = new Move(4, 4, 7, 3);
        assertFalse(board[4][4].isValidMove(invalid2, board));
    }

    @Test
    //Tests Bishops movement
    public void testBishopIsValid(){
        //Creates chess board
        IChessPiece[][] board = new IChessPiece[8][8];

        //Creates new Bishop and checks it has been created properly
        board[4][4] = new Bishop(Player.WHITE);
        assertEquals(board[4][4].player(),  Player.WHITE);
        assertEquals(board[4][4].type(), ("Bishop"));

        //Checks that moving up left 1 is valid move
        Move bishopDiagForwardLeft1 = new Move(4, 4, 3, 3);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardLeft1, board));
        //Checks that moving up left 3 is a valid move
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

        //Checks that moving up right 1 is valid move
        Move bishopDiagForwardRight1 = new Move(4, 4, 3, 5);
        assertTrue(board[4][4].isValidMove(bishopDiagForwardRight1, board));
        //Checks that moving up right 3 is valid move
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

        //Checks that moving down left 1 is valid move
        Move bishopDiagBackLeft1 = new Move(4, 4, 5, 3);
        assertTrue(board[4][4].isValidMove(bishopDiagBackLeft1, board));
        //Checks that moving down left 3 is valid move
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

        //Checks that moving down right 1 is valid move
        Move bishopDiagBackRight1 = new Move(4, 4, 5, 5);
        assertTrue(board[4][4].isValidMove(bishopDiagBackRight1, board));
        //Checks that moving up right 3 is valid move
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

        //Checks that the bishop cannot move straight up at all
        Move bishopForward1 = new Move(4, 4, 3, 4);
        assertFalse(board[4][4].isValidMove(bishopForward1, board));
        Move bishopForward3 = new Move(4, 4, 1, 4);
        assertFalse(board[4][4].isValidMove(bishopForward3, board));
        //Checks that the bishop cannot move straight down at all
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