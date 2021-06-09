package com.murilo.looqbox.entity.interfaces;

import java.util.List;

public interface PokemonInterface<E, I> {
    void save(E entity);
    List<E> listPokemonTrunk();
}
