package raven.processinterface;

import raven.wordprocess.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private JButton formatBtn = new JButton("Format");
    private JButton chooseFileBtn = new JButton("...");
    private JTextField filePathField = new JTextField();
    private JTextArea statusTxtArea = new JTextArea();
    private JFileChooser fileChooser = new JFileChooser(".");

    public MainWindow() {

        super("SQL Formatter");
        setLayout(null);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Add GUI components
        add(filePathField);
        add(statusTxtArea);
        add(formatBtn);
        add(chooseFileBtn);
        filePathField.setBounds(10, 10, 300, 30);
        chooseFileBtn.setBounds(312, 10, 30, 30);
        formatBtn.setBounds(344, 10, 78, 30);
        statusTxtArea.setBounds(10, 50, 480, 300);

        formatBtn.addActionListener(this);

        setSize(500, 400);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String sourceEvent = ((JButton) actionEvent.getSource()).getText();
        try {
            switch (sourceEvent) {
                case "format":
                    System.out.println("Formatting the file");
                    new Thread(new Parser()).start();
                    break;
                case "...":
                    fileChooser.showDialog(this, "Choose");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
