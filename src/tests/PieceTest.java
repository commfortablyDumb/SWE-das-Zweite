package tests;

import board.Board;
import board.Move;
import abstraction.Spot;
import common.PieceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pieces.Piece;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PieceTest {
    @Mock
    private Move move;

    @Mock
    private Spot spot;


    private Piece piece;

    private

    @BeforeEach
    void setUp() {

        piece = new Piece("P", PieceColor.WHITE) {};

    }

    @Test
    void testIsMoveValid() {

        Board board = mock(Board.class);
        //board = Board.getBoard();

        Spot startSpotMock = mock(Spot.class);
        Spot endSpotMock = mock(Spot.class);

        // Definieren des Verhaltens der Spot-Mocks
        when(startSpotMock.getX()).thenReturn(2);
        when(startSpotMock.getY()).thenReturn(2);
        when(endSpotMock.getX()).thenReturn(5);
        when(endSpotMock.getY()).thenReturn(5);

        // Mock-Objekt für Move erstellen und den Konstruktor aufrufen
        Move moveMock = new Move(startSpotMock, endSpotMock);

        boolean outOfRange = board.isOutOfRange(moveMock);
        boolean isSpotEmpty = board.isSpotEmpty(endSpotMock);

        when(board.isOutOfRange(moveMock)).thenReturn(false);
        when(board.isSpotEmpty(any())).thenReturn(false);

        // Act
        boolean isValid = piece.isMoveValid(new Move(new Spot(1,1), new Spot(5,5)));

        // Assert
        assertTrue(isValid);
    }

    @Test
    void testIsAllyPiece() {
        Piece ally = new Piece("Pawn", PieceColor.WHITE) {};
        Piece enemy = new Piece("King", PieceColor.BLACK) {};

        assertTrue(piece.isAllyPiece(ally), "Should be ally if same color");
        assertFalse(piece.isAllyPiece(enemy), "Should not be ally if different color");
    }

    @Test
    void testPieceGotMoved() {
        assertEquals(0, piece.getMovedAmount(), "Initially, movedAmount should be 0");
        piece.pieceGotMoved();
        assertEquals(1, piece.getMovedAmount(), "MovedAmount should increment by 1 after move");
        assertTrue(piece.hasBeenMoved(), "Piece should report it has been moved after increment");
    }

    @Test
    void testAccessors() {
        assertEquals("P", piece.getSymbol(), "Symbol should match constructor input");
        assertEquals(PieceColor.WHITE, piece.getColor(), "Color should match constructor input");
    }
} 