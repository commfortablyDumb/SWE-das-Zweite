package gamestate;

import board.Spot;
import common.PieceColor;
import common.PiecesMetadata;


public class CheckState implements GameStateCheck {
    @Override
    public StateInfo getState(PiecesMetadata metadata, PieceColor playerColor) {

        StateInfo stateInfo = new StateInfo(0, "");
        Spot kingSpot = metadata.findKingLocation(playerColor);
        if (metadata.isSpotThreatened(playerColor, kingSpot)) {
            stateInfo.setStateDescription(playerColor + " is checked");
            stateInfo.setStateCode(1);
        }
        return stateInfo;
    }

    @Override
    public boolean isIllegalForCurrentPlayer() {
        return true;
    }
}
