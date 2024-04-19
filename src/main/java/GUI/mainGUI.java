package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame implements ActionListener {

    private JTextArea chessField;
    private JTextField inputField;
    private JTextArea statusField;


    public mainGUI() {
        setTitle("Schachspiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //statusfeld für derzeitigen Spieler
        statusField = new JTextArea();
        statusField.setText("Test");
        add(statusField, BorderLayout.NORTH);

        // Großes Textfeld für das Schachspiel
        chessField = new JTextArea(20, 130);
        chessField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chessField);
        add(scrollPane, BorderLayout.CENTER);

        // Panel für das Eingabefeld und den Bestätigungsbutton
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // Kleines Eingabefeld
        inputField = new JTextField(20);
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Bestätigungsbutton
        JButton confirmButton = new JButton("Bestätigen");
        confirmButton.addActionListener(this);
        inputPanel.add(confirmButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setTextField(String text){
        chessField.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
