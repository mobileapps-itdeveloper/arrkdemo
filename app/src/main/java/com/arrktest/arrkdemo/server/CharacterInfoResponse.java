package com.arrktest.arrkdemo.server;

import java.util.List;

public class CharacterInfoResponse {
    private int count;
    private String next;
    private String previous;
    private List<CharacterInfo> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<CharacterInfo> getResults() {
        return results;
    }

    public void setResults(List<CharacterInfo> results) {
        this.results = results;
    }
}
