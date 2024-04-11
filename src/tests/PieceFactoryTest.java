package tests;

import common.PieceColor;
import org.junit.jupiter.api.Test;
import pieces.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceFactoryTest {

    // Test der Piece Factory
    // Hier wird die Erstellung der einzelnen Figuren getestet,
    // ob die Zuordnung zu den jeweiligen Klassen und der spezifischen Farbe korrekt sind.
    // Am Ende wird noch eine Falscheingabe geprüft

    PieceFactory factory = new PieceFactory();
    @Test
    public void testCreatePawn() {

        Piece pawn = factory.createPiece("Pawn", PieceColor.WHITE);
        assertNotNull(pawn);
        assertTrue(pawn instanceof Pawn);
        assertEquals(PieceColor.WHITE, pawn.getColor());
    }

    @Test
    public void testCreateRook(){

        Piece rook = factory.createPiece("Rook", PieceColor.BLACK);
        assertNotNull(rook);
        assertTrue(rook instanceof Rook);
        assertEquals(PieceColor.BLACK, rook.getColor());
    }

    @Test
    public void testCreateKnight(){

        Piece knight = factory.createPiece("Knight", PieceColor.WHITE);
        assertNotNull(knight);
        assertTrue(knight instanceof Knight);
        assertEquals(PieceColor.WHITE, knight.getColor());
    }

    @Test
    public void testCreateBishop(){

        Piece bishop = factory.createPiece("Bishop", PieceColor.BLACK);
        assertNotNull(bishop);
        assertTrue(bishop instanceof Bishop);
        assertEquals(PieceColor.BLACK, bishop.getColor());
    }

    @Test
    public void testCreateQueen(){

        Piece king = factory.createPiece("King", PieceColor.BLACK);
        assertNotNull(king);
        assertTrue(king instanceof King);
        assertEquals(PieceColor.BLACK, king.getColor());
    }

    @Test
    public void testCreateKing(){

        Piece bishop = factory.createPiece("Bishop", PieceColor.BLACK);
        assertNotNull(bishop);
        assertTrue(bishop instanceof Bishop);
        assertEquals(PieceColor.BLACK, bishop.getColor());
    }
    @Test
    public void testCreateWrongPiece() {
        PieceFactory factory = new PieceFactory();

        Piece invalidPiece = factory.createPiece("InvalidPiece", PieceColor.WHITE);
        assertNull(invalidPiece);
    }

}
