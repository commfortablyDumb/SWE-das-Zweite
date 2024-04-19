package common;

import GUI.ChessBoardGUI;
import board.Board;
import board.Move;
import abstraction.Spot;
import gamestate.*;
import movehandlers.*;
import pieces.Piece;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGameWithGuiNew {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final Board board;
    private final PiecesMetadata metadata;
    private final IMoveHandler moveHandler;
    private final StateChecker stateChecker;

    public ChessGameWithGuiNew() {
        whitePlayer = new Player(PieceColor.WHITE);
        blackPlayer = new Player(PieceColor.BLACK);
        board = Board.getBoard();
        metadata = new PiecesMetadata();
        List<GameStateCheck> checks = new ArrayList<>();
        checks.add(new CheckState());
        checks.add(new CheckMateState());
        checks.add(new StaleMateState());
        stateChecker = new GameStateChecker(checks);
        //move handler setup

        //Hier wurde MoveHandler durch RegularMovehandler geändert, um die Funktion zu testen
        MoveHandler reg = new RegularMoveHandler();
        MoveHandler enPassant = new EnPassantMoveHandler();
        MoveHandler promotion = new PromotionMoveHandler();
        MoveHandler castling = new CastlingMoveHandler();
        castling.setNext(promotion);
        promotion.setNext(enPassant);
        enPassant.setNext(reg);
        moveHandler = castling;
    }

    public void start() {
        new ChessBoardGUI();
        Player currentPlayer = whitePlayer;
        Scanner scan = SingletonScanner.getBoard();
        StateInfo gameState = new StateInfo(0, "");
        int turnNumber = 1;
        // state codes:
        // 0: regular state continue as usual
        // 1: a particular state was reached but not a game ending one (check)
        // 2: game ending state reached (checkmate, draw)
        while (gameState.getStateCode() <= 1) {
            System.out.println("TURN " + turnNumber++);
            System.out.println(currentPlayer.getColor() + " player's turn");
            board.viewBoard();
            playTurn(currentPlayer);
            gameState = stateChecker.checkState(metadata, swapPlayers(currentPlayer).getColor());
            if (gameState.getStateCode() != 0)
                System.out.println(gameState.getStateDescription());
            currentPlayer = swapPlayers(currentPlayer);
            if (gameState.getStateCode() == 2)
                board.viewBoard();
        }
        scan.close();
    }

    private void playTurn(Player player) {
        Move move = getPlayerInput(player);
        Spot startSpot = move.getStartSpot();
        Piece startPiece = board.getPiece(move.getStartSpot());
        Piece endPiece = board.getPiece(move.getEndSpot());
        if (board.isSpotEmpty(startSpot)
                || !board.getPiece(startSpot).getColor().equals(player.getColor())
                || !moveHandler.handleMove(metadata, move)) {
            String message = "Invalid move, try again";
            JOptionPane.showMessageDialog(null, message);
            System.out.println(message);
            playTurn(player);
        } else {
            StateInfo gameState = stateChecker.checkIllegalStates(metadata, player.getColor());
            if (gameState.getStateCode() != 0) {
                undoMove(move, startPiece, endPiece);
                playTurn(player);
            }
        }
    }

    private Move getPlayerInput(Player player) {
        JOptionPane.showMessageDialog(null, "\n" + player.getColor() + ", Enter next move ");
        //System.out.print("\nEnter next move (" + player.getColor() + " player, format:<current pos.> <new pos.>): ");
        //Scanner scan = SingletonScanner.getBoard();
        ChessBoardGUI.waitForButtonClicked();
        String command = ChessBoardGUI.getInput();
        command = command.toLowerCase();
        ChessBoardGUI.setButtonUnClicked();
        if (command.matches("[a-h][1-8] [a-h][1-8]")) {
            String[] parts = command.split(" ");
            return new Move(parseCoordinates(parts[0]), parseCoordinates(parts[1]));
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input format, try again");
            System.out.println("Invalid input format, try again");
            return getPlayerInput(player);
        }
    }

    private Spot parseCoordinates(String stringCoordinates) {
        int x = 8 - Integer.parseInt(stringCoordinates.substring(1));
        int y = stringCoordinates.charAt(0) - 'a';
        return new Spot(x, y);
    }

    private void undoMove(Move move, Piece startPiece, Piece endPiece) {
        if (endPiece != null)
            board.unCapturePiece(endPiece);
        board.setPiece(startPiece, move.getStartSpot());
        board.setPiece(endPiece, move.getEndSpot());
        System.out.println("endangered your king, try another move");
    }

    private Player swapPlayers(Player currentPlayer) {
        if (currentPlayer.equals(whitePlayer))
            return blackPlayer;
        else
            return whitePlayer;
    }

    public Board getBoard() {
        return board;
    }

}