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

public class testregularmovehandler {

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

        // Konfiguration des Mock-Verhaltens für board.movePiece(move)
        doReturn(true).when(board).movePiece(move);

        // Testen des erfolgreichen Zuges
        boolean result = handler.handleMove(metadata, move);
        assertTrue(result);

        // Überprüfung, ob die Methode aufgerufen wurde
        verify(board).movePiece(move);

        // Konfiguration des Mock-Verhaltens für den erfolglosen Zug
        doReturn(false).when(board).movePiece(move);

        // Testen des erfolglosen Zuges (weil nextMoveHandler null ist)
        result = handler.handleMove(metadata, move);
        assertFalse(result);
    }
}
