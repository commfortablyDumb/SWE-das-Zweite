package movehandlers;

import board.Move;
import common.PiecesMetadata;

public interface IMoveHandler {
    void setNext(IMoveHandler nextMoveHandler);

    boolean handleMove(PiecesMetadata metadata, Move move);
}
