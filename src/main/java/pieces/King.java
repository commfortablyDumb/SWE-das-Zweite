package pieces;

import abstraction.MathFunctions;
import board.Move;
import board.Spot;
import common.PieceColor;

public class King extends Piece{
    public King(PieceColor color) {
        super("King", color);
    }

    @Override
    public boolean isMoveValid(Move move) {
        if (!super.isMoveValid(move))
            return false;

        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();

        int xDistance = MathFunctions.abs(endSpot.getX() - startSpot.getX());
        int yDistance = MathFunctions.abs(endSpot.getY() - startSpot.getY());

        return xDistance <= 1 && yDistance <= 1;
    }

}
