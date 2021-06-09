package com.murilo.looqbox.entity.pokemon.services;

import com.murilo.looqbox.entity.interfaces.PokemonInterface;
import com.murilo.looqbox.entity.pokemon.consumer.services.ConsumerService;
import com.murilo.looqbox.entity.pokemon.entities.Pokemon;
import com.murilo.looqbox.entity.pokemon.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService implements PokemonInterface<Pokemon, Integer> {

    @Autowired
    PokemonRepository pokemonRepository;


    @Override
    public void save(Pokemon entity) {
        try{
            pokemonRepository.save(entity);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Pokemon> listPokemonTrunk() {
        return pokemonRepository.findAll();
    }

}
