package raven.processcontrol;

import raven.wordprocess.FileProcess;
import raven.wordprocess.Parser;

import java.io.IOException;

public class MainEntry {

    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
