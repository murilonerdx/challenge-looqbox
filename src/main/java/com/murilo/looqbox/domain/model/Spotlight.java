package com.murilo.looqbox.domain.model;

import java.io.Serializable;

public class Spotlight implements Serializable {
    private String name;
    private String highlight;
    private int start;
    private int end;

    public Spotlight(String name, String highlight, int start, int end) {
        this.name = name;
        this.highlight = highlight;
        this.start = start;
        this.end = end;
    }

    public Spotlight() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
