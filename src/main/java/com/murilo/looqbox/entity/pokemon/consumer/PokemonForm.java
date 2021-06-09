package com.murilo.looqbox.entity.pokemon.consumer;

import java.util.List;

public class PokemonForm {

    private Integer count;
    private String next;

    private List<Result> results;

    public PokemonForm(List<Result> results) {
        this.results = results;
    }

    public PokemonForm() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
