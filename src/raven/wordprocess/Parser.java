package raven.wordprocess;

import java.io.EOFException;
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
    private boolean elsIfFlag = false;

    public Parser(String filePath) throws IOException {
        initKeywordsPairs();
        keywordStack = new Stack<>();

        fileProcess = new FileProcess(new File(filePath));

    }

    /**
     * Find the start keyword of sql language and push it into stack
     */
    public void findStartWord() throws IOException {
        String[] words = fileProcess.extractWords();
        if (keywordsPairs.containsKey(words[0].toLowerCase())) { // only examine the first word of each line
            keywordStack.push(words[0]);
        }
    }

    /**
     * Find the end keyword(terminator) of sql language. If found, pop the start word from the stack
     */
    public void findEndWord() throws IOException {
        String strLine = fileProcess.getCurLine().toLowerCase();

        if (!keywordStack.empty()) {
            String[] endWords = keywordsPairs.get(keywordStack.peek());
            for (String eachEndWord : endWords) {
                if (strLine.indexOf(eachEndWord) > 0) {
                    if (eachEndWord.equals("elsif")) {
                        elsIfFlag = true;
                    }
                    keywordStack.pop();
                    return;
                }
            }
        }
    }

    private void initKeywordsPairs() {
        keywordsPairs = new HashMap<>();
        keywordsPairs.put("begin", new String[]{"end"});
        keywordsPairs.put("if", new String[]{"elsif", "end if"});
        keywordsPairs.put("elsif", new String[]{"elsif", "end if"});
        keywordsPairs.put("loop", new String[]{"end loop"});
    }

    @Override
    public void run() {
        try {
            while (true) {
                fileProcess.extractLine();
                this.findStartWord();
                this.findEndWord();
                fileProcess.insStringHead('\t', keywordStack);
                this.dealWithElsif();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getSimpleName());// can the program catch an EOFException?
            if (e instanceof EOFException) {
                fileProcess.replaceFile();
                fileProcess.close();
            }
        }
    }

    private void dealWithElsif() {
        if (elsIfFlag) {
            keywordStack.push("elsif");
            elsIfFlag = false;
        }
    }
}
