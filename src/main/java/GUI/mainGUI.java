package GUI;

import common.ChessGameWithGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame implements ActionListener {

    private static JTextArea textField;
    private JTextField inputField;

    public static void main(String[] args) {
        new ChessGameWithGui().start();
    }


    public mainGUI() {
        setTitle("Schachspiel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //this.game = game;

        // Großes Textfeld für das Schachspiel
        textField = new JTextArea(20, 130);
        textField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textField);
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

    public static void setTextField(String text){
        textField.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
