package com.murilo.looqbox.domain.model;

import java.io.Serializable;

public class Spotlight implements Serializable {
  private final String name;
  private String highlight;
  private int end;

  public Spotlight(String name, String highlight, int end) {
    this.name = name;
    this.highlight = highlight;
    this.end = end;
  }

  public String getName() {
    return name;
  }

  public String getHighlight() {
    return highlight;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public void setHighlight(String highlight) {
    this.highlight = highlight;
  }
}
