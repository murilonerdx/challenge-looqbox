package com.murilo.looqbox.domain.request;

import com.murilo.looqbox.domain.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

// Modelo para pegar informações da API
@SuppressWarnings("ALL")
public class SpotilightRequest {
  private final List<Pokemon> pokemons = new ArrayList<>();

  public List<Pokemon> getResults() {
    return pokemons;
  }

  public List<Pokemon> getPokemons() {
    return pokemons;
  }
}
