package Project2;

public class CastlingData {
    public boolean blackKingMoved;
    public boolean blackRightRookMoved;
    public boolean blackLeftRookMoved;
    public boolean whiteKingMoved;
    public boolean whiteRightRookMoved;
    public boolean whiteLeftRookMoved;

    public CastlingData() {
        blackKingMoved = false;
        blackRightRookMoved = false;
        blackLeftRookMoved = false;
        whiteKingMoved = false;
        whiteLeftRookMoved = false;
        whiteRightRookMoved = false;
    }

    public void setBlackKingMoved(boolean blackKingMoved) {
        this.blackKingMoved = blackKingMoved;
    }

    public void setBlackRightRookMoved(boolean blackRightRookMoved) {
        this.blackRightRookMoved = blackRightRookMoved;
    }

    public void setBlackLeftRookMoved(boolean blackLeftRookMoved) {
        this.blackLeftRookMoved = blackLeftRookMoved;
    }

    public void setWhiteKingMoved(boolean whiteKingMoved) {
        this.whiteKingMoved = whiteKingMoved;
    }

    public void setWhiteRightRookMoved(boolean whiteRightRookMoved) {
        this.whiteRightRookMoved = whiteRightRookMoved;
    }

    public void setWhiteLeftRookMoved(boolean whiteLeftRookMoved) {
        this.whiteLeftRookMoved = whiteLeftRookMoved;
    }
}
