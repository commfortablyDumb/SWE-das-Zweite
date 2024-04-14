package tests;

import board.Move;
import board.Spot;
import common.PieceColor;
import common.PiecesMetadata;
import gamestate.CheckMateState;
import gamestate.StateInfo;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class CheckMateStateTest {

    @Test
    public void testGetState() {
        // Mocks erstellen
        PiecesMetadata metadata = mock(PiecesMetadata.class);
        PieceColor playerColor = PieceColor.WHITE;
        StateInfo stateInfo = new StateInfo(0, "");

        // Königsposition festlegen
        Spot kingSpot = new Spot(4, 4);
        when(metadata.findKingLocation(playerColor)).thenReturn(kingSpot);

        // König im Schach simulieren
        when(metadata.isSpotThreatened(playerColor, kingSpot)).thenReturn(true);

        // Kein gültiger Zug für den König simulieren
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Spot next = new Spot(kingSpot.getX() + i, kingSpot.getY() + j);
                when(metadata.isMoveValid(new Move(kingSpot, next))).thenReturn(false);
            }
        }

        // Testobjekt erstellen
        CheckMateState checkMateState = new CheckMateState();

        // Test durchführen
        StateInfo result = checkMateState.getState(metadata, playerColor);

        // Überprüfen, ob der König Schachmatt ist
        assertEquals("CHECKMATE\n" + playerColor + " loses", result.getStateDescription());
        assertEquals(2, result.getStateCode());
    }
}
