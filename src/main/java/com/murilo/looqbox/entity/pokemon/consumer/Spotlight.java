package com.murilo.looqbox.entity.pokemon.consumer;

public class Spotlight extends Result {
    private String name;
    private String highlight;
    private int start;
    private int end;

    public Spotlight(String name) {
        super(name);
    }

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
