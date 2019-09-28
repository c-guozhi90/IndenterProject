package raven.processinterface;

import raven.wordprocess.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainWindow extends JFrame implements ActionListener {
    private JButton formatBtn = new JButton("Format");
    private JButton chooseFileBtn = new JButton("...");
    private JTextField filePathField = new JTextField();
    private JTextArea statusTxtArea = new JTextArea();
    private JFileChooser fileChooser = new JFileChooser(".");

    public MainWindow() {

        super("SQL Formatter");
        setLayout(null);

        // GUI components configuration
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Choose a file");
        statusTxtArea.setEditable(false);

        // Add GUI components
        add(filePathField);
        add(statusTxtArea);
        add(formatBtn);
        add(chooseFileBtn);
        filePathField.setBounds(10, 10, 368, 30);
        chooseFileBtn.setBounds(380, 10, 30, 30);
        formatBtn.setBounds(412, 10, 78, 30);
        statusTxtArea.setBounds(10, 50, 480, 300);

        formatBtn.addActionListener(this);
        chooseFileBtn.addActionListener(this);

        // GUI window configuration
        setSize(505, 400);
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
                    new Thread(new Parser(filePathField.getText())).start();
                    break;
                case "...":
                    if (fileChooser.showDialog(this, "Choose") == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        filePathField.setText(selectedFile.getPath());
                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(this,"Cannot read or write the file!");
        }
    }
}
