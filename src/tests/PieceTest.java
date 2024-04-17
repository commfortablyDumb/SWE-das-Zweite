package tests;

import board.Board;
import board.Move;
import board.Spot;
import common.PieceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Piece;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PieceTest {

    Piece piece = mock(Piece.class);

    @BeforeEach
    public void setUp() {
        piece = new Piece("Pawn", PieceColor.WHITE) {};

    }

    @Test
    void testIsMoveValid() {

        Board board = mock(Board.class);
        //board = Board.getBoard();
/*
        Spot startSpotMock = mock(Spot.class);
        Spot endSpotMock = mock(Spot.class);

        when(startSpotMock.getX()).thenReturn(1);
        when(startSpotMock.getY()).thenReturn(1);
        when(endSpotMock.getX()).thenReturn(1);
        when(endSpotMock.getY()).thenReturn(3);

        Move move = new Move(startSpotMock, endSpotMock);
        */

        Move mockMove = mock(Move.class);
        when(mockMove.getEndSpot()).thenReturn(new Spot(1,1));
        when(mockMove.getStartSpot()).thenReturn(new Spot(1,3));

        when(board.isOutOfRange(mockMove)).thenReturn(false);
        when(board.isSpotEmpty(any())).thenReturn(false);

        boolean isValid = piece.isMoveValid(mockMove);

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
        assertEquals("Pawn", piece.getSymbol(), "Symbol should match constructor input");
        assertEquals(PieceColor.WHITE, piece.getColor(), "Color should match constructor input");
    }
} 