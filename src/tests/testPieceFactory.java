package tests;

import common.PieceColor;
import org.junit.jupiter.api.Test;
import pieces.*;

import static org.junit.jupiter.api.Assertions.*;

public class testPieceFactory {

    // Test der Piece Factory
    // Hier wird die Erstellung der einzelnen Figuren getestet,
    // ob die Zuordnung zu den jeweiligen Klassen und der spezifischen Farbe korrekt sind.
    // Am Ende wird noch eine Falscheingabe geprüft
    @Test
    void testCreatePiece() {
        PieceFactory factory = new PieceFactory();

        // Test für jede Figur
        Piece pawn = factory.createPiece("Pawn", PieceColor.WHITE);
        assertNotNull(pawn);
        assertTrue(pawn instanceof Pawn);
        assertEquals(PieceColor.WHITE, pawn.getColor());

        Piece rook = factory.createPiece("Rook", PieceColor.BLACK);
        assertNotNull(rook);
        assertTrue(rook instanceof Rook);
        assertEquals(PieceColor.BLACK, rook.getColor());

        Piece knight = factory.createPiece("Knight", PieceColor.WHITE);
        assertNotNull(knight);
        assertTrue(knight instanceof Knight);
        assertEquals(PieceColor.WHITE, knight.getColor());

        Piece bishop = factory.createPiece("Bishop", PieceColor.BLACK);
        assertNotNull(bishop);
        assertTrue(bishop instanceof Bishop);
        assertEquals(PieceColor.BLACK, bishop.getColor());

        Piece queen = factory.createPiece("Queen", PieceColor.WHITE);
        assertNotNull(queen);
        assertTrue(queen instanceof Queen);
        assertEquals(PieceColor.WHITE, queen.getColor());

        Piece king = factory.createPiece("King", PieceColor.BLACK);
        assertNotNull(king);
        assertTrue(king instanceof King);
        assertEquals(PieceColor.BLACK, king.getColor());

        // Test für falschen Schachstücktyp
        Piece invalidPiece = factory.createPiece("InvalidPiece", PieceColor.WHITE);
        assertNull(invalidPiece);
    }

}
