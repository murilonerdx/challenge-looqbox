package com.murilo.looqbox.domain.interfaces;

import java.util.List;

public interface PokemonInterface<E, I> {
    void save(E entity);
    List<E> listPokemonTrunk();
}
