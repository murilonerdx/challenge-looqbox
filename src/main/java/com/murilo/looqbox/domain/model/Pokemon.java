package com.murilo.looqbox.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon implements Serializable {

    private String name;
    private PokemonSpotlight spotlight;

    public Pokemon(String name, PokemonSpotlight spotlight) {
        this.name = name;
        this.spotlight = spotlight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pokemon() {
    }

    public PokemonSpotlight getSpotlight() {
        return spotlight;
    }

    public void setSpotlight(PokemonSpotlight spotlight) {
        this.spotlight = spotlight;
    }
}