package com.murilo.looqbox.domain.model;

import java.util.List;

//Modelo para pegar informações da API
public class PokemonRequest {

    private Integer count;
    private String next;

    private List<Pokemon> pokemons;

    public PokemonRequest(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public PokemonRequest() {
    }

    public List<Pokemon> getResults() {
        return pokemons;
    }

    public void setResults(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
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
