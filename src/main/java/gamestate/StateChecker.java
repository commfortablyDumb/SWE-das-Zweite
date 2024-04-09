package gamestate;

import common.PieceColor;
import common.PiecesMetadata;

public interface StateChecker {

    StateInfo checkState(PiecesMetadata metadata, PieceColor playerColor);
    StateInfo checkIllegalStates(PiecesMetadata metadata, PieceColor playerColor);
}
