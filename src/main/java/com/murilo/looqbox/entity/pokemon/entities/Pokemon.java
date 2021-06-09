package com.murilo.looqbox.entity.pokemon.entities;

import javax.persistence.*;

@Entity
@Table(name="TB_POKEMON")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String highlight;
    private int start;
    private int end;

    public Pokemon(Integer id, String name, String highlight, int start, int end) {
        this.id = id;
        this.name = name;
        this.highlight = highlight;
        this.start = start;
        this.end = end;
    }

    public Pokemon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
