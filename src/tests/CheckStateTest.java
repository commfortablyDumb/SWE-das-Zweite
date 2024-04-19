package tests;

import abstraction.Spot;
import common.PieceColor;
import common.PiecesMetadata;
import gamestate.CheckState;
import gamestate.StateInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Hier wird geprüft, ob die Klasse CheckState korrekt funktioniert.
// Dabei wird mit Mocks für die Metadata gearbeitet.
// Ebenfalls wird die Funktion testIsIllegalForCurrentPlayer getestet.
// Da die aber nur ein true zurückgibt ist der Test etwas überflüssig und wird auch in der
// SWE-Dokumentation nicht beschrieben.

public class CheckStateTest {

    @Test
    public void testGetState() {
        //Erstellen der mocks und variablen
        PiecesMetadata metadata = mock(PiecesMetadata.class);
        PieceColor playerColor = PieceColor.WHITE;
        StateInfo stateInfo;

        //Position des Kings festlegen
        Spot kingSpot = new Spot(4, 4);
        when(metadata.findKingLocation(playerColor)).thenReturn(kingSpot);

        //Schach simulieren
        when(metadata.isSpotThreatened(playerColor, kingSpot)).thenReturn(true);

        // CheckState-Objekt erstellen und testen
        CheckState checkState = new CheckState();
        stateInfo = checkState.getState(metadata, playerColor);

        //Prüfen, ob die korrekte StateInfo zurückgegeben wird
        assertEquals(playerColor + " is checked", stateInfo.getStateDescription());
        assertEquals(1, stateInfo.getStateCode());
    }

    @Test
    public void testIsIllegalForCurrentPlayer() {
        // CheckState-Objekt erstellen und testen
        CheckState checkState = new CheckState();
        assertTrue(checkState.isIllegalForCurrentPlayer());
    }
}

