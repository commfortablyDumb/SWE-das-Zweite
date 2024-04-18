package pieces;

import abstraction.MathFunctions;
import board.Board;
import board.Spot;

public class canMove {
    Board board;
    Spot startSpot;
    int xDistance;
    int yDistance;

    public canMove(Board board, Spot startSpot, int xDistance, int yDistance) {
        this.board = board;
        this.startSpot = startSpot;
        this.xDistance = xDistance;
        this.yDistance = yDistance;
    }

    //check if Piece can move vertical
    public boolean canMoveVertical() {
        int direction = xDistance > 0 ? 1 : -1;
        for (int i = 1; i < MathFunctions.abs(xDistance); i++) {
            if (!board.isSpotEmpty(new Spot(startSpot.getX() + i * direction, startSpot.getY()))) {
                return false;
            }
        }
        return true;
    }

    //check if Piece can move horizontal
    public boolean canMoveHorizontal() {
        int direction = yDistance > 0 ? 1 : -1;
        for (int i = 1; i < MathFunctions.abs(yDistance); i++) {
            if (!board.isSpotEmpty(new Spot(startSpot.getX(), startSpot.getY() + i * direction))) {
                return false;
            }
        }
        return true;
    }

    //check if Piece can move diagonal
    public boolean canMoveDiagonal() {
        int verticalDirection = xDistance > 0 ? 1 : -1;
        int horizontalDirection = yDistance > 0 ? 1 : -1;

        for (int i = 1; i < MathFunctions.abs(xDistance); i++) {
            int x = startSpot.getX() + i * verticalDirection;
            int y = startSpot.getY() + i * horizontalDirection;
            if (!board.isSpotEmpty(new Spot(x, y)))
                return false;
        }
        return true;
    }
}
