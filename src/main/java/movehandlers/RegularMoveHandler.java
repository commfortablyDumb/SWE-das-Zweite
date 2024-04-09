package movehandlers;

import board.Board;
import board.Move;
import common.PiecesMetadata;

public class RegularMoveHandler extends MoveHandler {
    @Override
    public boolean handleMove(PiecesMetadata metadata, Move move) {
        Board board = Board.getBoard();

        if (metadata.isMoveValid(move)
                && !board.getPiece(move.getStartSpot()).isAllyPiece(board.getPiece(move.getEndSpot()))) {
            board.movePiece(move);
            return true;
        }
        if (nextMoveHandler != null)
            return nextMoveHandler.handleMove(metadata, move);
        else
            return false;
    }
}
