package GUI;

import board.Board;
import common.ChessGameWithGuiNew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChessBoardGUI extends JFrame implements BoardListener {
    private static JButton[][] buttons;
    private static JTextField textField;
    private JButton clearButton;
    private JButton submitButton;
    private JTextPane statusField;
    private Board board;
    private ChessGameWithGuiNew chessGame;


    public ChessBoardGUI() {
        super("Chess Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        board = Board.getBoard();
        board.setBoardListener(this);

        chessGame = new ChessGameWithGuiNew();
        chessGame.setGUIListener(this);

        //statusfeld für derzeitigen Spieler
        statusField = new JTextPane();
        statusField.setText("Player's turn: WHITE");
        statusField.setEditable(false);
        add(statusField, BorderLayout.NORTH);

        // Chessboard Panel erstellen
        JPanel chessboardPanel = createChessboardPanel();

        // Textfeld
        textField = new JTextField(20);
        textField.setEditable(false);

        //Button zum Bestätigen des Zuges
        submitButton = new JButton("Submit");
        ButtonClickListener clickListener = new ButtonClickListener();
        submitButton.addActionListener(clickListener);

        //Button zum Leeren des Textfelds
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        // Panel für Textfeld und Clear-Button
        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textFieldPanel.add(clearButton);
        textFieldPanel.add(textField);
        textFieldPanel.add(submitButton);

        // GUI zusammenfügen
        add(chessboardPanel, BorderLayout.CENTER);
        add(textFieldPanel, BorderLayout.SOUTH);

        setSize(750, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        chessGame.start();
    }

    // Funktion zur Erstellung des Chessboard Panels
    private JPanel createChessboardPanel() {
        JPanel chessboardPanel = new JPanel(new GridLayout(9, 9));
        buttons = new JButton[8][8];

        boolean isWhiteSquare = true;

        // Leeres Label für obere linke Ecke
        chessboardPanel.add(new JLabel(""));

        // Labels für Buchstaben a bis h
        for (char c = 'a'; c <= 'h'; c++) {
            chessboardPanel.add(new JLabel(String.valueOf(c), SwingConstants.CENTER));
        }

        // Zahlen 1 bis 8 am linken Rand
        for (int i = 1; i <= 8; i++) {
            chessboardPanel.add(new JLabel(String.valueOf(9-i), SwingConstants.CENTER));
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setName(convertToLetter(j) + (9-i));

                // Hintergrundfarbe setzen
                if (isWhiteSquare) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.GRAY);
                }
                isWhiteSquare = !isWhiteSquare;

                button.addActionListener(e -> {
                    JButton btn = (JButton) e.getSource();
                    String currentText = textField.getText();
                    String newName = null;
                    if (!currentText.isEmpty()) {
                        // Prüfen, ob bereits ein Name im Textfeld steht
                        if (currentText.contains(" ")) {
                            JOptionPane.showMessageDialog(ChessBoardGUI.this, "Bereits zwei Namen im Textfeld", "Fehler", JOptionPane.ERROR_MESSAGE);
                            textField.setText(""); // Textfeld leeren
                            return;
                        }
                        newName = currentText + " " + btn.getName();
                    }else {
                        newName = btn.getName();
                    }
                    textField.setText(newName);
                });
                buttons[i - 1][j] = button;
                chessboardPanel.add(button);
            }
            isWhiteSquare = !isWhiteSquare; // Nach jeder Reihe die Farbe umkehren
        }
        return chessboardPanel;
    }

    // Methode zur Konvertierung der Spaltenzahl in Buchstaben
    private String convertToLetter(int index) {
        char letter = (char) ('a' + index);
        return Character.toString(letter);
    }

    public void setButtonText(int x, int y, String text){
        buttons[x][y].setText(text);
    }

    public String getInput(){
        return textField.getText();
    }

    private boolean buttonClicked = false;
    public void waitForButtonClicked(){
        while (!buttonClicked) {
            try {
                Thread.sleep(100); // Kurze Pause, um die CPU zu entlasten
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setButtonUnClicked(){
        textField.setText("");
        buttonClicked = false;

    }

    @Override
    public void updatePlayer() {
        statusField.setText("Player's turn: " +chessGame.getCurrentPlayer());
    }

    @Override
    public void onBoardChanged(String[][] boardCopy) {
        SwingUtilities.invokeLater(() -> {
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    String symbolName = boardCopy[j][i];
                    setButtonText(j, i, symbolName);
                }
            }
        });
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null,message);
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonClicked = true;
        }
    }


}