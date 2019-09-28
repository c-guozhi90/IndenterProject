package raven.processcontrol;

import raven.processinterface.MainWindow;
import raven.wordprocess.Parser;

import javax.swing.*;

public class MainEntry {

    private String filePath;
    private MainWindow mainWindow;

    public static void main(String[] args) {

        MainEntry mainEntry = new MainEntry();

        // Init the ui thread
        SwingUtilities.invokeLater(() -> {
            mainEntry.mainWindow = new MainWindow();
        });
    }

}
