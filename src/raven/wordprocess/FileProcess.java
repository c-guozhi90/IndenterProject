package raven.wordprocess;

import java.io.*;
import java.util.LinkedList;

public class FileProcess {
    /**
     * insert specified character the head of a string
     */
    private BufferedWriter bw;
    private BufferedReader br;
    private File curFile;

    public FileProcess(File curFile) throws IOException {
        if (!curFile.isFile()) {
            throw new FileNotFoundException();
        }
        this.curFile = curFile;
        br = new BufferedReader(new FileReader(curFile));
        bw = new BufferedWriter(new FileWriter(curFile.getAbsolutePath())); // the writer writes string to temp file.
    }

    public static void insStringHead(char c) {
    }


    public static void replaceFile() {

    }

    /**
     * split a line of string by spaces and return the words collection
     *
     * @return the words seperated by spaces
     */
    public String[] extractWords() throws IOException {

        String[] results = br.readLine().trim().split(" ");
        return results;
    }
}
