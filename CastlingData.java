package chess;

/**********************************************************************
 * @author Dan Dietsche
 * CIS 163 Winter 2022
 * Project 2
 *
 * holds data on if any of the kings or rooks have moved yet this game
 *
 */
public class CastlingData {

    /** if the black king has moved from its starting location */
    public boolean blackKingMoved;

    /** if the black right rook has moved from its starting location */
    public boolean blackRightRookMoved;

    /** if the black left rook has moved from its starting location */
    public boolean blackLeftRookMoved;

    /** if the white king has moved from its starting location */
    public boolean whiteKingMoved;

    /** if the white right rook has moved from its starting location */
    public boolean whiteRightRookMoved;

    /** if the white left rook has moved from its starting location */
    public boolean whiteLeftRookMoved;

    /******************************************************************
     * default constructor that sets all variables to false
     *
     */
    public CastlingData() {
        blackKingMoved = false;
        blackRightRookMoved = false;
        blackLeftRookMoved = false;
        whiteKingMoved = false;
        whiteLeftRookMoved = false;
        whiteRightRookMoved = false;
    }

    /******************************************************************
     * sets blackKingMoved to given boolean parameter
     *
     * @param blackKingMoved has the black king moved
     */
    public void setBlackKingMoved(boolean blackKingMoved) {
        this.blackKingMoved = blackKingMoved;
    }

    /******************************************************************
     * sets blackRightRookMoved to given boolean parameter
     *
     * @param blackRightRookMoved has the black right rook moved
     */
    public void setBlackRightRookMoved(boolean blackRightRookMoved) {
        this.blackRightRookMoved = blackRightRookMoved;
    }

    /******************************************************************
     * sets blackLeftRookMoved to given boolean parameter
     *
     * @param blackLeftRookMoved has the black left rook moved
     */
    public void setBlackLeftRookMoved(boolean blackLeftRookMoved) {
        this.blackLeftRookMoved = blackLeftRookMoved;
    }

    /******************************************************************
     * sets whiteKingMoved to given boolean parameter
     *
     * @param whiteKingMoved has the white king moved
     */
    public void setWhiteKingMoved(boolean whiteKingMoved) {
        this.whiteKingMoved = whiteKingMoved;
    }

    /******************************************************************
     * sets whiteRightRookMoved to given boolean parameter
     *
     * @param whiteRightRookMoved has the white right rook moved
     */
    public void setWhiteRightRookMoved(boolean whiteRightRookMoved) {
        this.whiteRightRookMoved = whiteRightRookMoved;
    }

    /******************************************************************
     * sets whiteLeftRookMoved to given boolean parameter
     *
     * @param whiteLeftRookMoved has the white left rook moved
     */
    public void setWhiteLeftRookMoved(boolean whiteLeftRookMoved) {
        this.whiteLeftRookMoved = whiteLeftRookMoved;
    }
}
