package chess;

import java.util.ArrayList;

/**********************************************************************
 * @author Dan Dietsche, Kyle Scott, Joseph Lentine
 * CIS 163 Winter 2022
 * Project 2
 *
 * ChessModel class, uses varoius piece Class logic to play a full game
 * of chess
 *
 */
public class ChessModel implements IChessModel {

    /** array of arrays that represents the chess board */
    private IChessPiece[][] board;

    /** current player */
    private Player player;

    /** keeps track of what turn it is */
    private int turn;

    /** array of SaveStates that holds relevant data for all prior
     *  moves */
    private ArrayList<SaveState> prevMoves;

    /** keeps track of if an en passant move can be done this turn */
    private boolean canEnPassant;

    /** holds all data on if castling can be done */
    private CastlingData canCastle;

    /******************************************************************
     * default constructor. sets up board and instantiates variables
     *
     */
    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;
        turn = 0;
        prevMoves = new ArrayList<>(0);
        canEnPassant = false;
        canCastle = new CastlingData();

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        board[6][0] = new Pawn(Player.WHITE);
        board[6][1] = new Pawn(Player.WHITE);
        board[6][2] = new Pawn(Player.WHITE);
        board[6][3] = new Pawn(Player.WHITE);
        board[6][4] = new Pawn(Player.WHITE);
        board[6][5] = new Pawn(Player.WHITE);
        board[6][6] = new Pawn(Player.WHITE);
        board[6][7] = new Pawn(Player.WHITE);

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

        board[1][0] = new Pawn(Player.BLACK);
        board[1][1] = new Pawn(Player.BLACK);
        board[1][2] = new Pawn(Player.BLACK);
        board[1][3] = new Pawn(Player.BLACK);
        board[1][4] = new Pawn(Player.BLACK);
        board[1][5] = new Pawn(Player.BLACK);
        board[1][6] = new Pawn(Player.BLACK);
        board[1][7] = new Pawn(Player.BLACK);

    }

    /******************************************************************
     * Returns whether the game is complete.
     *
     * @return {@code true} if complete, {@code false} otherwise.
     */
    public boolean isComplete() {
        boolean valid = true;
        Move test;
        Player p = player;

        // go through rows
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // check player for piece in location
                if (board[a][b] != null && board[a][b].player() == p) {
                    // go through rows
                    for (int c = 0; c < 8; ++c) {
                        // go through columns
                        for (int d = 0; d < 8; ++d) {
                            // make new move
                            test = new Move(a, b, c, d);
                            // see if new move is valid
                            if (isValidMove(test)) {
                                valid = false;
                                a = b = c = d = 8;
                            }
                        }
                    }
                }
            }
        }

        return valid;
    }

    /******************************************************************
     * Returns whether the piece at location
     * {@code [move.fromRow, move.fromColumn]} is allowed to move to
     * location {@code [move.fromRow, move.fromColumn]}. also checks
     * for if move is a valid en passant or castling move
     *
     * @param move a {@link chess.Move} object describing the
     *                move to be made.
     * @return {@code true} if the proposed move is valid,
     * 				  {@code false} otherwise.
     * @throws IndexOutOfBoundsException if either
     * 				   {@code [move.fromRow, move.fromColumn]} or
     * 				   {@code [move.toRow, move.toColumn]} don't
     * 				   represent valid locations on the board.
     */
    public boolean isValidMove(Move move) {
        // checks if move is out of bounds
        if (move.fromRow < 0 || move.fromRow > 7 || move.fromColumn < 0
                || move.fromColumn > 7 || move.toRow < 0
                || move.toRow > 7 || move.toColumn < 0
                || move.toColumn > 7) {
            throw new IndexOutOfBoundsException();
        }

        boolean valid = false;

        // makes sure from location is not null and piece at from
        // location is owned by the active player
        if (board[move.fromRow][move.fromColumn] != null
                && board[move.fromRow][move.fromColumn].player()
                == player) {
            //checks if the move is valid to the piece, an en passant
            // or castling
            if (isEnPassant(move, board) ||
                    board[move.fromRow][move.fromColumn].
                            isValidMove(move, board) ||
                    isValidCastling(move, board)) {
                // tries move
                move(move);
                // checks if move will put active player into check
                if (!inCheck(player.next())) {
                    valid = true;
                }
                // undoes move
                undo();
            }
        }

        return valid;
    }

    /******************************************************************
     * Moves the piece from location {@code [move.fromRow,
     * move.fromColumn]} to location {@code [move.fromRow,
     * move.fromColumn]}. handles special movement for en passant and
     * castling. then updates castling data, turn count, and active
     * player
     *
     * @param move a {@link chess.Move} object describing the
     *               move to be made.
     * @throws IndexOutOfBoundsException if either
     *  			 {@code [move.fromRow, move.fromColumn]} or
     *  			 {@code [move.toRow, move.toColumn]} don't
     *  			 represent valid locations on the board.
     */
    public void move(Move move) {
        // checks if move is out of bounds
        if (move.fromRow < 0 || move.fromRow > 7 || move.fromColumn < 0
                || move.fromColumn > 7 || move.toRow < 0
                || move.toRow > 7 || move.toColumn < 0
                || move.toColumn > 7) {
            throw new IndexOutOfBoundsException();
        }

        // checks if move is en passant
        if (isEnPassant(move, board)) {
            // saves move state to prevMoves array
            prevMoves.add(new SaveState(move, board));
            prevMoves.get(prevMoves.size()-1).
                    saveCastlingData(canCastle);
            // performs en passant move
            board[move.toRow][move.toColumn] =
                    board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
            board[move.fromRow][move.toColumn] = null;
            // finishes saving board state
            prevMoves.get(prevMoves.size()-1).setEnPassant(true);
        }
        else if (isValidCastling(move, board)) {
            // saves move state to prevMoves array
            prevMoves.add(new SaveState(move, board));
            prevMoves.get(prevMoves.size()-1).
                    saveCastlingData(canCastle);
            // checks if black is making move
            if (move.fromRow == 0) {
                // checks if queenside castling
                if ((move.fromColumn - move.toColumn) > 0) {
                    board[0][0] = null;
                    board[0][4] = null;
                    board[0][3] = new Rook(Player.BLACK);
                    board[0][2] = new King(Player.BLACK);
                }
                // runs kingside castling
                else {
                    board[0][7] = null;
                    board[0][4] = null;
                    board[0][5] = new Rook(Player.BLACK);
                    board[0][6] = new King(Player.BLACK);
                }
            }
            // checks if white is making move
            if (move.fromRow == 7) {
                // checks if queenside castling
                if ((move.fromColumn - move.toColumn) > 0) {
                    board[7][0] = null;
                    board[7][4] = null;
                    board[7][3] = new Rook(Player.WHITE);
                    board[7][2] = new King(Player.WHITE);
                }
                // runs kingside castling
                else {
                    board[7][7] = null;
                    board[7][4] = null;
                    board[7][5] = new Rook(Player.WHITE);
                    board[7][6] = new King(Player.WHITE);
                }
            }
            // finishes saving move state
            prevMoves.get(prevMoves.size()-1).setWasCastling(true);
        }
        // performs regular move if neither en passant or castling
        else {
            // saves move state to prevMoves array
            prevMoves.add(new SaveState(move, board));
            prevMoves.get(prevMoves.size()-1).
                    saveCastlingData(canCastle);
            // performs move
            board[move.toRow][move.toColumn] =
                    board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
        }

        // checks if next move can be an en passant
        if (board[move.toRow][move.toColumn].type().equals("Pawn")
                && Math.abs(move.fromRow - move.toRow) == 2) {
            canEnPassant = true;
        }
        else {
            canEnPassant = false;
        }

        updateCastlingData(move, board);
        setNextPlayer();
        turn++;

    }

    /******************************************************************
     * runs the given move regardless of validity
     *
     * @param move given move
     */
    private void tryMove(Move move) {
        board[move.toRow][move.toColumn] =
                board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    /******************************************************************
     * Report whether the current player p is in check.
     * @param  p {@link chess.Move} the Player being checked
     * @return {@code true} if the current player is in check,
     * {@code false} otherwise.
     */
    public boolean inCheck(Player p) {
        boolean valid = false;
        int kingRow = -1;
        int kingCol = -1;
        Move test;

        // loop through board to find king
        for (int a = 0; a < 8; ++a) {
            for (int b = 0; b < 8; ++b) {
                if (board[a][b] != null && board[a][b].player() == p
                        && board[a][b].type().equals("King")) {
                    kingRow = a;
                    kingCol = b;
                    a = 8;
                    b = 8;
                }
            }
        }

        // loop through board checking if any piece has a valid move
        // to king's location
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                test = new Move(i, j, kingRow, kingCol);
                if (board[i][j] != null
                        && board[i][j].isValidMove(test, board)) {
                    valid = true;
                    i = 8;
                    j = 8;
                }
            }
        }

        return valid;
    }

    /******************************************************************
     * undoes the previous action
     *
     */
    public void undo() {
        // can't undo if no moves have been done
        if (turn == 0) {
            return;
        }

        // looks up previous move data
        SaveState save = prevMoves.get(prevMoves.size()-1);

        //sets canCastle to saved castling data
        canCastle = save.data;

        //sets pieces to previous spots
        board[save.move.fromRow][save.move.fromColumn] =
                save.fromPiece;
        board[save.move.toRow][save.move.toColumn] = save.toPiece;

        // sets canEnPassant to saved data
        if (save.wasEnPassant) {
            canEnPassant = true;
            if (save.fromPiece.player() == Player.BLACK) {
                board[save.move.toRow - 1][save.move.toColumn] =
                        new Pawn(Player.WHITE);
            }
            else {
                board[save.move.toRow + 1][save.move.toColumn] =
                        new Pawn(Player.BLACK);
            }
        }
        else {
            canEnPassant = false;
        }

        // undoes castling if castling was done
        if (save.wasCastling) {
            if (save.move.fromRow == 0) {
                if (save.move.fromColumn - save.move.toColumn > 0) {
                    board[0][0] = new Rook(Player.BLACK);
                    board[0][3] = null;
                }
                else {
                    board[0][7] = new Rook(Player.BLACK);
                    board[0][5] = null;
                }
            }
            if (save.move.fromRow == 7) {
                if (save.move.fromColumn - save.move.toColumn > 0) {
                    board[7][0] = new Rook(Player.WHITE);
                    board[7][3] = null;
                }
                else {
                    board[7][7] = new Rook(Player.WHITE);
                    board[7][5] = null;
                }
            }
        }
        // deletes last SaveState in prevMoves array
        prevMoves.remove(prevMoves.size()-1);
        // decrements turn count
        turn--;
        // switches player
        setNextPlayer();
    }

    /******************************************************************
     * checks if there is a promotable pawn in a back row
     * @return returns true if a pawn can be promoted
     */
    public boolean canPromote() {
        // checks if there is a pawn in a back row
        for (int i = 0; i < 8; ++i) {
            if (board[0][i] != null) {
                if (board[0][i].type().equals("Pawn") &&
                        board[0][i].player().equals(Player.WHITE)) {
                    return true;
                }
            }
            if (board[7][i] != null) {
                if (board[7][i].type().equals("Pawn") &&
                        board[7][i].player().equals(Player.BLACK)) {
                    return true;
                }
            }
        }
        return false;
    }

    /******************************************************************
     * promotes the first promotable pawn to the type given
     *
     * @param type string with the type to be promoted to
     */
    public void promote(String type) {
        Player p = player.next();
        IChessPiece promotion = new Pawn(p);

        switch (type) {
            case "Rook":
                promotion = new Rook(p);
                break;
            case "Knight":
                promotion = new Knight(p);
                break;
            case "Bishop":
                promotion = new Bishop(p);
                break;
            case "Queen":
                promotion = new Queen(p);
                break;
        }
        for (int i = 0; i < 8; ++i) {
            if (board[0][i] != null) {
                if (board[0][i].type().equals("Pawn") &&
                        board[0][i].player().equals(Player.WHITE)) {
                    board[0][i] = promotion;
                }
            }
            if (board[7][i] != null) {
                if (board[7][i].type().equals("Pawn") &&
                        board[7][i].player().equals(Player.BLACK)) {
                    board[7][i] = promotion;
                }
            }
        }

    }

    /******************************************************************
     * returns true if the move provided on the board provided is valid
     * en passant, false otherwise
     *
     * @param move given move
     * @param board given board
     * @return returns true if move is valid en passant
     */
    public boolean isEnPassant(Move move, IChessPiece[][] board) {
        boolean valid = false;
        // checks if turn is zero
        if (turn == 0) {
            return valid;
        }
        // accesses the previous move data
        SaveState lastTurn = prevMoves.get(prevMoves.size() - 1);
        // checks if the move can be an en passant
        if (!canEnPassant) {
            return valid;
        }
        // checks if the last piece to move was a pawn
        if (!board[move.fromRow][move.fromColumn].type()
                .equals("Pawn")) {
            return valid;
        }
        // checks if player is white
        if (player == Player.WHITE) {
            // checks if the moving piece is in row 3 (the only row
            // white can en passant from)
            if (move.fromRow == 3) {
                // checks if the pawn is moving to a spot behind the
                // pawn that moved last turn
                if (move.toColumn == lastTurn.move.fromColumn &&
                        Math.abs(move.fromColumn - move.toColumn) == 1
                        && (move.fromRow - move.toRow) == 1) {
                    valid = true;
                }
            }
        }
        // checks if player is black
        else if (player == Player.BLACK) {
            // checks if the moving piece is in row 4 ( the only row
            // black can en passant from)
            if (move.fromRow == 4) {
                // checks if the pawn is moving to a spot behind the
                // pawn that moved last turn
                if (move.toColumn == lastTurn.move.fromColumn &&
                        Math.abs(move.fromColumn - move.toColumn) == 1
                        && (move.fromRow - move.toRow) == -1) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    /******************************************************************
     * returns true if the move provided on the board provided is valid
     * castling, false otherwise
     *
     * @param move move data
     * @param board board data
     * @return returns true if move is valid castling
     */
    public boolean isValidCastling(Move move, IChessPiece[][] board) {
        boolean valid = false;
        // checks if player is in check (cannot castle if in check)
        if (inCheck(player)) {
            return valid;
        }
        // checks if the move is two spaces to the right or left
        if (!(Math.abs(move.fromColumn - move.toColumn) == 2 &&
                move.fromRow == move.toRow)) {
            return valid;
        }
        // checks white logic
        if (player == Player.WHITE) {
            // checks if the white king has moved this game
            if (canCastle.whiteKingMoved) {
                return valid;
            }
            // logic if movement is to right
            if ((move.fromColumn - move.toColumn) < 0) {
                // checks if white rook has moved this game
                if (!canCastle.whiteRightRookMoved) {
                    // checks that the spaces between the rook and king
                    // are empty
                    if (board[7][5] != null || board[7][6] != null) {
                        return valid;
                    }
                    // checks that the king would not be in check in
                    // either of the spaces it is moving through
                    Move move1 = new Move(7, 4, 7, 5);
                    boolean move1Valid = false;
                    Move move2 = new Move(7, 4, 7, 6);
                    boolean move2Valid = false;
                    tryMove(move1);
                    if (!inCheck(player)) {
                        move1Valid = true;
                    }
                    board[move1.fromRow][move1.fromColumn] =
                            new King(Player.WHITE);
                    board[move1.toRow][move1.toColumn] = null;
                    tryMove(move2);
                    if (!inCheck(player)) {
                        move2Valid = true;
                    }
                    board[move2.fromRow][move2.fromColumn] =
                            new King(Player.WHITE);
                    board[move2.toRow][move2.toColumn] = null;
                    if (move1Valid && move2Valid) {
                        valid = true;
                    }
                }
            }
            // logic if movement is to left
            if ((move.fromColumn - move.toColumn) > 0) {
                // checks if left rook has moved this game
                if (!canCastle.whiteLeftRookMoved) {
                    // checks that the spaces between the rook and king
                    // are empty
                    if (board[7][3] != null || board[7][2] != null
                            || board[7][1] != null) {
                        return valid;
                    }
                    // checks that the king would not be in check in
                    // either of the spaces it is moving through
                    Move move1 = new Move(7, 4, 7, 3);
                    boolean move1Valid = false;
                    Move move2 = new Move(7, 4, 7, 2);
                    boolean move2Valid = false;
                    tryMove(move1);
                    if (!inCheck(player)) {
                        move1Valid = true;
                    }
                    board[move1.fromRow][move1.fromColumn] =
                            new King(Player.WHITE);
                    board[move1.toRow][move1.toColumn] = null;

                    tryMove(move2);
                    if (!inCheck(player)) {
                        move2Valid = true;
                    }
                    board[move2.fromRow][move2.fromColumn] =
                            new King(Player.WHITE);
                    board[move2.toRow][move2.toColumn] = null;

                    if (move1Valid && move2Valid) {
                        valid = true;
                    }
                }
            }
        }
        // logic for if player is black
        if (player == Player.BLACK) {
            // checks if king has moved this game
            if (canCastle.blackKingMoved) {
                return valid;
            }
            // logic if movement is to right
            if ((move.fromColumn - move.toColumn) < 0) {
                // checks if right rook has moved this game
                if (!canCastle.blackRightRookMoved) {
                    // checks that the spaces between the king and rook
                    // are empty
                    if (board[0][5] != null || board[0][6] != null) {
                        return valid;
                    }
                    // checks that the king would not be in check in
                    // either of the spaces it is moving through
                    Move move1 = new Move(0, 4, 0, 5);
                    boolean move1Valid = false;
                    Move move2 = new Move(0, 4, 0, 6);
                    boolean move2Valid = false;
                    tryMove(move1);
                    if (!inCheck(player)) {
                        move1Valid = true;
                    }
                    board[move1.fromRow][move1.fromColumn] =
                            new King(Player.BLACK);
                    board[move1.toRow][move1.toColumn] = null;
                    tryMove(move2);
                    if (!inCheck(player)) {
                        move2Valid = true;
                    }
                    board[move2.fromRow][move2.fromColumn] =
                            new King(Player.BLACK);
                    board[move2.toRow][move2.toColumn] = null;
                    if (move1Valid && move2Valid) {
                        valid = true;
                    }
                }
            }
            // logic if movement is to left
            if ((move.fromColumn - move.toColumn) > 0) {
                // checks if left rook has moved this game
                if (!canCastle.blackLeftRookMoved) {
                    // checks that the spaces between the king and rook
                    // are empty
                    if (board[0][3] != null || board[0][2] != null ||
                            board[0][1] != null) {
                        return valid;
                    }
                    // checks that the king would not be in check in
                    // either of the spaces it is moving through
                    Move move1 = new Move(0, 4, 0, 3);
                    boolean move1Valid = false;
                    Move move2 = new Move(0, 4, 0, 2);
                    boolean move2Valid = false;
                    tryMove(move1);
                    if (!inCheck(player)) {
                        move1Valid = true;
                    }
                    board[move1.fromRow][move1.fromColumn] =
                            new King(Player.BLACK);
                    board[move1.toRow][move1.toColumn] = null;
                    tryMove(move2);
                    if (!inCheck(player)) {
                        move2Valid = true;
                    }
                    board[move2.fromRow][move2.fromColumn] =
                            new King(Player.BLACK);
                    board[move2.toRow][move2.toColumn] = null;

                    if (move1Valid && move2Valid) {
                        valid = true;
                    }
                }
            }
        }

        return valid;
    }

    /******************************************************************
     * updates if the kings or rooks have moved from their starting
     * position
     *
     * @param move move data
     * @param board board state
     */
    public void updateCastlingData(Move move, IChessPiece[][] board) {
        if (move.fromRow == 0 && move.fromColumn == 0 &&
                board[move.toRow][move.toColumn].type().equals("Rook"))
        { canCastle.setBlackLeftRookMoved(true); }
        else if (move.fromRow == 0 && move.fromColumn == 7 &&
                board[move.toRow][move.toColumn].type().equals("Rook"))
        { canCastle.setBlackRightRookMoved(true); }
        else if (move.fromRow == 0 && move.fromColumn == 4 &&
                board[move.toRow][move.toColumn].type().equals("King"))
        { canCastle.setBlackKingMoved(true); }
        else if (move.fromRow == 7 && move.fromColumn == 0 &&
                board[move.toRow][move.toColumn].type().equals("Rook"))
        { canCastle.setWhiteLeftRookMoved(true); }
        else if (move.fromRow == 7 && move.fromColumn == 7 &&
                board[move.toRow][move.toColumn].type().equals("Rook"))
        { canCastle.setWhiteRightRookMoved(true); }
        else if (move.fromRow == 7 && move.fromColumn == 4 &&
                board[move.toRow][move.toColumn].type().equals("King"))
        { canCastle.setWhiteKingMoved(true); }
    }

    /******************************************************************
     * Return the current player.
     *
     * @return the current player
     */
    public Player currentPlayer() {
        return player;
    }

    /******************************************************************
     * Return the number of rows
     *
     * @return 8
     */
    public int numRows() {
        return 8;
    }

    /******************************************************************
     * Return the number of columns
     *
     * @return 8
     */
    public int numColumns() {
        return 8;
    }

    /******************************************************************
     * return piece at specified location
     *
     * @param row specified row
     * @param column specified column
     *
     * @return returns piece at row, column
     */
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /******************************************************************
     * changes active player
     *
     */
    public void setNextPlayer() {
        player = player.next();
    }

    /******************************************************************
     * places a piece at specified location
     *
     * @param row specified row
     * @param column specified column
     * @param piece specified piece
     */
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /******************************************************************
     * AI for black, holds unique moves for first and second turns, and
     * logic to follow for later turns
     *
     */
    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or
         *         placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         * 		i. Attempt to put opponent into check without losing
         *         your piece
         *		ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *		i. check to see if that piece is in danger of being
         *         removed, if so, move a different piece.
         */

        // check if it's black's first move
        if (prevMoves.size() == 1) {
            // grab the location of the piece that white just moved
            int fromRow = prevMoves.get(0).move.fromRow;
            int fromColumn = prevMoves.get(0).move.fromColumn;
            // if they moved queenside pawn, respond with same
            if (fromRow ==6 && fromColumn == 3) {
                move(new Move(1, 3, 3, 3));
                return;
            }
            // else move kingside pawn up one
            else {
                move(new Move(1, 4, 2, 4));
                return;
            }
        }

        // check if it's black's second move
        if (prevMoves.size() == 3) {
            // get ints for white's last move
            int fromRow = prevMoves.get(2).move.fromRow;
            int fromColumn = prevMoves.get(2).move.fromColumn;
            int toRow = prevMoves.get(2).move.toRow;
            int toColumn = prevMoves.get(2).move.toColumn;
            // if white continues into queen's gambit, respond with
            // slab defense
            if (fromRow == 6 && fromColumn == 2 && toRow == 4
                    && toColumn == 2) {
                move(new Move(1, 2, 2, 2));
                return;
            }
        }

        if (!dodgeCheck()) {
            if (!protectPiece()) {
                if (!attackOpponent()) {
                    if (!releaseTheQueen()) {
                        if (!pushTheRanks()) {
                            if (!moveSomethingOtherThanTheKing()) {
                                if (!okayGottaMakeAnActualMove()) {
                                    moveKing();
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /******************************************************************
     * logic to get out of check if black king is in check
     *
     * @return returns true if move was performed
     */
    private boolean dodgeCheck() {
        // see if black king is in check
        if (inCheck(player)) {
            // see if attacking will take the king out of check
            // be aggressive
            if (attackOpponent()) {
                return true;
            }
            Move test;
            // go through rows
            for (int a = 0; a < 8; ++a) {
                // go through columns
                for (int b = 0; b < 8; ++b) {
                    // check for black piece
                    if (board[a][b] != null && board[a][b].player()
                            == player) {
                        // go through rows
                        for (int c = 0; c < 8; ++c) {
                            // go through columns
                            for (int d = 0; d < 8; ++d) {
                                // make new move
                                test = new Move(a, b, c, d);
                                // see if new move is valid
                                if (isValidMove(test)) {
                                    // perform move and return true
                                    move(test);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // return false if no move was done
        return false;
    }

    /******************************************************************
     * logic for if black piece is under attack. will perform a move if
     * the piece being attacked can be protected without sacrificing
     * the defending piece
     *
     * @return returns true if move was performed
     */
    private boolean protectPiece() {
        Move test;
        // go through rows
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // find white piece
                if (board[a][b] != null && board[a][b].player()
                        == player.next()) {
                    // go through rows
                    for (int c = 0; c < 8; ++c) {
                        // go through columns
                        for (int d = 0; d < 8; ++d) {
                            // find black piece that isn't a pawn
                            if (board[c][d] != null && !board[c][d].
                                    type().equals("Pawn") &&
                                    board[c][d].player() == player) {
                                // make move for the white piece
                                // capturing the black piece
                                test = new Move(a, b, c, d);
                                // see if new move is valid. player
                                // needs to change as isValidMove
                                // checks to make sure the piece being
                                // moved is owned by the current player
                                player = player.next();
                                if (isValidMove(test)) {
                                    // check if a move can be done to
                                    // keep that piece from being
                                    // captured
                                    if (canProtect(test)) {
                                        // return true if canProtect
                                        // kept a move
                                        return true;
                                    }
                                }
                                // set player back to black
                                player = player.next();
                            }
                        }
                    }
                }
            }
        }
        // return false if no move is kept
        return false;
    }

    /******************************************************************
     * logic to check if potential white move can be protected against
     *
     * @param move move that white will perform to take piece
     * @return returns true if move will successfully protect piece
     *      without putting new piece at risk from the attacking white
     *      piece
     */
    private boolean canProtect(Move move) {
        Move test;
        // go through rows
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // find black piece
                if (board[a][b] != null && board[a][b].player()
                        == player.next()) {
                    // go through rows
                    for (int c = 0; c < 8; ++c) {
                        // go through columns
                        for (int d = 0; d < 8; ++d) {
                            // switch to next player so isValidMove
                            // will check for moves valid to black
                            player = player.next();
                            // check all moves that black can do
                            test = new Move(a, b, c, d);
                            // see if new move is valid
                            if (isValidMove(test)) {
                                // try out the move
                                move(test);
                                // see if the parameter move that was
                                // given is no longer valid
                                if (!isValidMove(move)) {
                                    // make a new move to see if white
                                    // piece that was threatening
                                    // will be able to capture the
                                    // black piece that just moved
                                    // to protect
                                    Move try2 = new Move(move.fromRow,
                                            move.fromColumn,
                                            test.toRow, test.toColumn);
                                    if (!isValidMove(try2)) {
                                        // return true if move will be
                                        // kept
                                        return true;
                                    }
                                }
                                // undo move if it doesn't meet all
                                // criteria
                                undo();
                            }
                            // set player back to white
                            player = player.next();
                        }
                    }
                }
            }
        }
        // return false if no move was kept
        return false;
    }

    /******************************************************************
     * logic if black can take a piece. will not make the attack if the
     * piece will then be under attack
     *
     * @return returns true if move is made
     */
    private boolean attackOpponent() {

        Move test;
        // go through rows
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // check for black piece
                if (board[a][b] != null && board[a][b].player()
                        == player) {
                    // go through rows
                    for (int c = 0; c < 8; ++c) {
                        // go through columns
                        for (int d = 0; d < 8; ++d) {
                            // check for white piece
                            if (board[c][d] != null && board[c][d]
                                    .player() == player.next()) {
                                // make new move
                                test = new Move(a, b, c, d);
                                // see if new move is valid
                                if (isValidMove(test)) {
                                    // take the piece
                                    move(test);
                                    // check if piece moved into threat
                                    if (!movedIntoThreat(test)) {
                                        // return true if piece didn't move
                                        // into threat
                                        return true;
                                    }
                                    // undo if piece moved into threat
                                    undo();
                                }
                            }
                        }
                    }
                }
            }
        }
        // return false if no move was done
        return false;
    }

    /******************************************************************
     * logic to move the queen as far down-right as possible, without
     * it being under attack after the move.
     *
     * @return returns true if move is made
     */
    private boolean releaseTheQueen() {
        Move test;
        //go through rows
        for (int a = 7; a >= 0; --a) {
            // go through columns
            for (int b = 7; b >= 0; --b) {
                // find the black king
                if (board[a][b] != null && board[a][b].player()
                        == player
                        && board[a][b].type().equals("Queen")) {
                    // go through rows
                    for (int c = 7; c >= 0; --c) {
                        // go through columns
                        for (int d = 7; d >= 0; --d) {
                            // make new move
                            test = new Move(a, b, c, d);
                            // see if new move is valid
                            if (isValidMove(test)) {
                                // perform move
                                move(test);
                                // check if piece moved into threat
                                if (!movedIntoThreat(test)) {
                                    // return true if piece didn't move
                                    // into threat
                                    return true;
                                }
                                // undo if piece moved into threat
                                undo();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /******************************************************************
     * logic to find a pawn in the starting pawn row and move it
     * forward. starts checking on the right side, moves to left, will
     * move the piece as far down as possible
     *
     * @return returns true if move is made
     */
    private boolean pushTheRanks() {
        Move test;
        // go through columns
        for (int b = 7; b >= 0; --b) {
            // check player for non-king black piece
            if (board[1][b] != null && board[1][b].player()
                    == player && board[1][b].type()
                    .equals("Pawn")) {
                // go through rows
                for (int c = 7; c >= 0; --c) {
                    // go through columns
                    for (int d = 7; d >= 0; --d) {
                        // make new move
                        test = new Move(1, b, c, d);
                        // see if new move is valid
                        if (isValidMove(test)) {
                            // perform move
                            move(test);
                            // check if piece moved into threat
                            if (!movedIntoThreat(test)) {
                                // return true if piece didn't move
                                // into threat
                                return true;
                            }
                            // undo if piece moved into threat
                            undo();
                        }
                    }
                }
            }
        }
    // return false if no move was done
    return false;
    }

    /******************************************************************
     * moves the upper-left most piece as far down-right as possible.
     * does not attempt the move if it will be captured
     *
     * @return returns true if move is made
     */
    private boolean moveSomethingOtherThanTheKing() {
        Move test;
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // check player for non-king black piece
                if (board[a][b] != null && board[a][b].player()
                        == player && !board[a][b].type()
                        .equals("King")) {
                    // go through rows
                    for (int c = 7; c >= 0; --c) {
                        // go through columns
                        for (int d = 7; d >= 0; --d) {
                            // make new move
                            test = new Move(a, b, c, d);
                            // see if new move is valid
                            if (isValidMove(test)) {
                                // perform move
                                move(test);
                                // check if piece moved into threat
                                if (!movedIntoThreat(test)) {
                                    // return true if piece didn't move
                                    // into threat
                                    return true;
                                }
                                // undo if piece moved into threat
                                undo();
                            }
                        }
                    }
                }
            }
        }
        // return false if no move was done
        return false;
    }

    /******************************************************************
     * logic to move a piece other than the king, regardless of if it
     * will get captured. the ai needs to make a move, in the end. will
     * move the upper-left most piece as far down-right as possible
     *
     * @return returns true if move is made
     */
    private boolean okayGottaMakeAnActualMove() {
        Move test;
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // check player for non-king black piece
                if (board[a][b] != null && board[a][b].player()
                        == player && !board[a][b].type()
                        .equals("King")) {
                    // go through rows
                    for (int c = 7; c >= 0; --c) {
                        // go through columns
                        for (int d = 7; d >= 0; --d) {
                            // make new move
                            test = new Move(a, b, c, d);
                            // see if new move is valid
                            if (isValidMove(test)) {
                                // perform move
                                move(test);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        // return false if no move was done
        return false;
    }

    /******************************************************************
     * logic to move the king if no other piece can move. this will
     * move the king to the top left of the board. will move the king
     * to the upper-left corner
     *
     */
    public void moveKing() {
        Move test;
        //go through rows
        for (int a = 7; a >= 0; --a) {
            // go through columns
            for (int b = 7; b >= 0; --b) {
                // find the black king
                if (board[a][b] != null && board[a][b].player()
                        == player
                        && board[a][b].type().equals("King")) {
                    // go through rows
                    for (int c = 0; c < 8; ++c) {
                        // go through columns
                        for (int d = 0; d < 8; ++d) {
                            // make new move
                            test = new Move(a, b, c, d);
                            // see if new move is valid
                            if (isValidMove(test)) {
                                // perform move
                                move(test);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    /******************************************************************
     * checks to see if given move puts piece in risk of being captured
     *
     * @param move move just performed
     * @return returns true if piece is in threat
     */
    private boolean movedIntoThreat(Move move) {
        Move test;
        // go through rows
        for (int a = 0; a < 8; ++a) {
            // go through columns
            for (int b = 0; b < 8; ++b) {
                // find black piece
                if (board[a][b] != null && board[a][b].player()
                        == player) {
                    test = new Move(a, b, move.toRow, move.toColumn);
                    if (isValidMove(test)) {
                        return true;
                    }
                }
            }
        }
    return false;
    }
}