package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class App extends JFrame{
    private JButton enviarButton;
    private JPanel panelMain;
    private JScrollPane scrollPane;
    private JTextField messageLabel;
    private JTextArea textArea;

    public App() {
        setTitle("Chat em tempo real");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
