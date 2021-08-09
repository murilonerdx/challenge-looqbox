package com.murilo.looqbox.domain.request;

import com.murilo.looqbox.domain.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

//Modelo para pegar informações da API
public class SpotilightRequest {
    private List<Pokemon> pokemons = new ArrayList<>();

    public SpotilightRequest(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public SpotilightRequest() {
    }

    public List<Pokemon> getResults() {
        return pokemons;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void setResults(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


}
