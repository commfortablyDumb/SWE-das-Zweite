package pieces;

import abstraction.MathFunctions;
import board.Board;
import board.Move;
import abstraction.Spot;
import common.PieceColor;


public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super("Bishop", color);
    }

    //Hier wird geprüft, ob ein Zug möglich ist.
    @Override
    public boolean isMoveValid(Move move) {
        Board board = Board.getBoard();
        if (!super.isMoveValid(move))
            return false;

        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();
        int xDistance = endSpot.getX() - startSpot.getX();
        int yDistance = endSpot.getY() - startSpot.getY();
        canMove check = new canMove(board, startSpot, xDistance, yDistance);

        boolean canMove = true;
        if (MathFunctions.abs(xDistance) == MathFunctions.abs(yDistance) && xDistance != 0) {
                canMove = check.canMoveDiagonal();
        } else
            canMove = false;
        return canMove;
    }
}
