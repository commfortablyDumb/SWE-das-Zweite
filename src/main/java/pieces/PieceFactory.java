package pieces;

import common.PieceColor;

public class PieceFactory {
    public Piece createPiece(String pieceType, PieceColor color) {
        // Prüfen, welcher Name übergeben wird und groß und Kleinschreibung ignorieren
        if (pieceType.equalsIgnoreCase("Pawn"))
            return new Pawn(color);
        else if (pieceType.equalsIgnoreCase("Rook"))
            return new Rook(color);
        else if (pieceType.equalsIgnoreCase("Knight"))
            return new Knight(color);
        else if (pieceType.equalsIgnoreCase("Bishop"))
            return new Bishop(color);
        else if (pieceType.equalsIgnoreCase("Queen"))
            return new Queen(color);
        else if (pieceType.equalsIgnoreCase("King"))
            return new King(color);
        return null;
    }
}
