package com.example.Snippet.Searcher;

import org.austral.edu.Results.Result;

public class SnippetResult implements Result {
    public String res;

    public SnippetResult() {
        this.res = "";
    }
    @Override
    public void savePrintElement(String s) {
        res += s;
    }

    @Override
    public void saveReaderElement(String s) {
        res += s;
    }

    public String getResult() {
        return this.res;
    }
}
