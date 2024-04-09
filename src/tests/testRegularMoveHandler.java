package tests;

import board.Board;
import board.Move;
import common.PiecesMetadata;
import movehandlers.RegularMoveHandler;
import org.junit.jupiter.api.Test;
import pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

//Test um die Regulären Züge zu behandeln. Läuft aktuell noch nicht korrekt
// Fehlerkorrektur muss noch erfolgen!
public class testRegularMoveHandler {

    @Test
    void testHandleMove() {
        // Mocking
        PiecesMetadata metadata = mock(PiecesMetadata.class);
        Move move = mock(Move.class);
        Board board = mock(Board.class);
        Piece startPiece = mock(Piece.class);
        Piece endPiece = mock(Piece.class);

        // Konfiguration des Mock-Verhaltens
        when(metadata.isMoveValid(move)).thenReturn(true);
        when(board.getPiece(any())).thenReturn(startPiece, endPiece);
        when(startPiece.isAllyPiece(endPiece)).thenReturn(false);

        RegularMoveHandler handler = new RegularMoveHandler();

        // Testen des erfolgreichen Zuges
        //when(board.movePiece(move)).thenReturn(true);
        boolean result = handler.handleMove(metadata, move);
        assertTrue(result);

        // Überprüfung, ob die Methode aufgerufen wurde
        verify(board).movePiece(move);

        // Testen des erfolglosen Zuges (weil nextMoveHandler null ist)
        //when(board.movePiece(move)).thenReturn(false);
        result = handler.handleMove(metadata, move);
        assertFalse(result);
    }
}
