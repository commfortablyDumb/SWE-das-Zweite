package pieces;

import abstraction.Spot;
import board.Board;
import board.Move;
import common.PieceColor;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super("Rook", color);
    }

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
        //vertical movement check
        if (xDistance != 0 && yDistance == 0) {
            canMove = check.canMoveVertical();
        } else //horizontal movement check
            if (xDistance == 0 && yDistance != 0) {
                canMove = check.canMoveHorizontal();
            } else
                canMove = false;

        return canMove;
    }
}
