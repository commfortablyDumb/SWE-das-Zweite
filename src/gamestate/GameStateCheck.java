package gamestate;

import common.PieceColor;
import common.PiecesMetadata;

public interface GameStateCheck {

    StateInfo getState(PiecesMetadata metadata, PieceColor playerColor);

    boolean isIllegalForCurrentPlayer();

}
