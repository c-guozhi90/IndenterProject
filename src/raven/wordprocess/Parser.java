package raven.wordprocess;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class is for processing sql language
 * the keywords in sql are begin, declare, end, for, if and so on
 */
public class Parser implements Runnable {
    private Stack<String> keywordStack;
    private FileProcess fileProcess;
    private HashMap<String, String[]> keywordsPairs;
    private String[] startKeys;
    private boolean startWordFound;

    public Parser() throws IOException {
        initKeywordsPairs();
        keywordStack = new Stack<>();

        fileProcess = new FileProcess(new File(""));

    }

    /**
     * Find the start keyword of sql language and push it into stack
     */
    public void findStartWord() throws IOException {
        String[] words = fileProcess.extractWords();
        if (keywordsPairs.containsKey(words[0])) { // only examine the first word of each line
            keywordStack.push(words[0]);
            startWordFound = true;
        }
    }

    /**
     * Find the end keyword(terminator) of sql language. If found, pop the start word from the stack
     */
    public void findEndWord() throws IOException {
        String strLine = fileProcess.extractLine();

        if (!keywordStack.empty()) {
            String[] endWords = keywordsPairs.get(keywordStack.peek());
            for (String eachEndWord : endWords) {
                if (strLine.indexOf(eachEndWord) > 0) {
                    keywordStack.pop();
                    return;
                }
            }
        }
    }

    private void initKeywordsPairs() {
        keywordsPairs = new HashMap<>();
        keywordsPairs.put("begin", new String[]{"end"});
        keywordsPairs.put("if", new String[]{"elseif", "end if"});
        keywordsPairs.put("loop", new String[]{"end loop"});
        keywordsPairs.put("\"", new String[]{"\""});
        keywordsPairs.put("\'", new String[]{"\'"});
    }

    @Override
    public void run() {

    }
}
