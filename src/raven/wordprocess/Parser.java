package raven.wordprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * This class is for processing sql language
 * the keywords in sql are begin, declare, end, for, if and so on
 */
public class Parser {
    private Stack<String> keywords;
    private FileProcess fileProcess;
    private HashMap<String, String[]> keywordsPairs;
    private String[] startKeys;

    public Parser() throws FileNotFoundException {
        initKeywordsPairs();
        keywords = new Stack<>();

        fileProcess = new FileProcess(new File(""));

    }

    public void wordMatch() {
        LinkedList<String> words = fileProcess.extractWords();
    }

    private void initKeywordsPairs() {
        keywordsPairs = new HashMap<>();
        keywordsPairs.put("begin", new String[]{"end"});
        keywordsPairs.put("if", new String[]{"elseif", "end if"});
        keywordsPairs.put("loop", new String[]{"end loop"});
        keywordsPairs.put("\"", new String[]{"\""});
        keywordsPairs.put("\'", new String[]{"\'"});
    }
}
