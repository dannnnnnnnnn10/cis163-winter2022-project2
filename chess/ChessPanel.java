package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private JButton newGame;
    private JButton undo;
    private ChessModel model;
    private JLabel blank;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    // declare other instance variables as needed

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        newGame = new JButton("New Game");
        undo = new JButton("Undo");
        blank = new JLabel("");
        listener = new listener();
        undo.addActionListener(listener);
        newGame.addActionListener(listener);
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));
        buttonpanel.setLayout(new GridLayout(22, 1, 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else
                    placeBlackPieces(r, c);
                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        buttonpanel.add(newGame);
        buttonpanel.add(undo);
        buttonpanel.add(blank);
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel, BorderLayout.EAST);
        firstTurnFlag = true;

    }

    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(new Color(239, 206, 164));
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(new Color(175, 128, 103, 255));
        }
    }

    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void placeBlackPieces(int r, int c){
        if(model.pieceAt(r, c).type().equals("Pawn")){
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/chess/wRook.png");
        wBishop = new ImageIcon("./src/chess/wBishop.png");
        wQueen = new ImageIcon("./src/chess/wQueen.png");
        wKing = new ImageIcon("./src/chess/wKing.png");
        wPawn = new ImageIcon("./src/chess/wPawn.png");
        wKnight = new ImageIcon("./src/chess/wKnight.png");
        // Sets the Image for black player pieces
        bRook = new ImageIcon("./src/chess/bRook.png");
        bBishop = new ImageIcon("./src/chess/bBishop.png");
        bQueen = new ImageIcon("./src/chess/bQueen.png");
        bKing = new ImageIcon("./src/chess/bKing.png");
        bPawn = new ImageIcon("./src/chess/bPawn.png");
        bKnight = new ImageIcon("./src/chess/bKnight.png");
    }

    // method that updates the board
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                } else {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
                }
        }
        repaint();

    }


    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == undo){
                model.undo();
                // comment out one undo when turning off ai
                model.undo();
                displayBoard();
            }
            if (event.getSource() == newGame) {
                model = new ChessModel();
                displayBoard();
            }
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource()) {
                        if (firstTurnFlag == true) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m)) == true) {
                                model.move(m);
                                if (model.canPromote()) {
                                    String[] promotionChoices = {"Queen", "Bishop", "Knight", "Rook"};
                                    String input = (String) JOptionPane.showInputDialog(null, "What do you want to promote this pawn to?", "Promotion", JOptionPane.QUESTION_MESSAGE, null, promotionChoices, promotionChoices[0]);
                                    model.promote(input);
                                }
                                displayBoard();
                            }
                            else{
                                showMessageDialog(null, "Move is invalid", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            if(model.isComplete() == true){
                                int response = JOptionPane.showConfirmDialog(null, "Would you like to play a new game?", "Game Over",JOptionPane.YES_NO_OPTION,  JOptionPane.INFORMATION_MESSAGE);
                                switch (response) {
                                    case JOptionPane.YES_OPTION:
                                        model = new ChessModel();
                                        displayBoard();
                                        break;
                                    case JOptionPane.NO_OPTION:
                                    case JOptionPane.CLOSED_OPTION:
                                        System.exit(0);
                                        break;
                                }
                                if(model.noValidMoves() == true) {
                                    int response2 = JOptionPane.showConfirmDialog(null, "Would you like to play a new game?", "Draw Game", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                                    switch (response2) {
                                        case JOptionPane.YES_OPTION:
                                            model = new ChessModel();
                                            displayBoard();
                                            break;
                                        case JOptionPane.NO_OPTION:
                                        case JOptionPane.CLOSED_OPTION:
                                            System.exit(0);
                                            break;
                                    }
                                }


                            }
                            if(model.inCheck(Player.WHITE) == true){
                                showMessageDialog(null, "White in Check", "Check Warning", JOptionPane.ERROR_MESSAGE);
                            }
                            else if(model.inCheck(Player.BLACK) == true){
                                showMessageDialog(null, "Black in Check", "Check Warning", JOptionPane.ERROR_MESSAGE);
                            }

                            // comment this out to turn off ai
                            if (model.currentPlayer() == Player.BLACK) {
                                model.AI();
                                if (model.canPromote()) {
                                    model.promote("Queen");
                                }
                                displayBoard();
                                if(model.inCheck(Player.WHITE) == true){
                                    showMessageDialog(null, "White in Check", "Check Warning", JOptionPane.ERROR_MESSAGE);
                                }
                                if(model.isComplete() == true){
                                    int response3 = JOptionPane.showConfirmDialog(null, "Would you like to play a new game?", "Game Over",JOptionPane.YES_NO_OPTION,  JOptionPane.INFORMATION_MESSAGE);
                                    switch (response3) {
                                        case JOptionPane.YES_OPTION:
                                            model = new ChessModel();
                                            displayBoard();
                                            break;
                                        case JOptionPane.NO_OPTION:
                                        case JOptionPane.CLOSED_OPTION:
                                            System.exit(0);
                                            break;
                                    }
                                    if(model.noValidMoves() == true) {
                                        int response4 = JOptionPane.showConfirmDialog(null, "Would you like to play a new game?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                                        switch (response4) {
                                            case JOptionPane.YES_OPTION:
                                                model = new ChessModel();
                                                displayBoard();
                                                break;
                                            case JOptionPane.NO_OPTION:
                                            case JOptionPane.CLOSED_OPTION:
                                                System.exit(0);
                                                break;
                                        }
                                    }

                                }
                            }
                        }
                    }
        }
    }
}

