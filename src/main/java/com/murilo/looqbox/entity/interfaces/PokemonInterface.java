package com.murilo.looqbox.entity.interfaces;

import com.murilo.looqbox.entity.pokemon.PokemonForm;

import java.util.List;

public interface PokemonInterface {
    PokemonForm findPokemon(String identity);
    List<String> searchAllPokemon(Integer limit);
}
