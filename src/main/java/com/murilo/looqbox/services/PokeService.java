package com.murilo.looqbox.services;

import com.murilo.looqbox.entity.pokemon.PokemonForm;
import com.murilo.looqbox.util.WebConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokeService {

    @Autowired
    private WebConsumer consumer;

    //Serviço para listar todos pokemons
    public List<PokemonForm> findAllPoke(){
        List<PokemonForm> listaPokemonForms = consumer.searchAllPokemon(811);
        return listaPokemonForms;
    }

    public WebConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(WebConsumer consumer) {
        this.consumer = consumer;
    }
}
