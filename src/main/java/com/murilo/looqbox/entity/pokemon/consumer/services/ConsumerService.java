package com.murilo.looqbox.entity.pokemon.consumer.services;

import com.murilo.looqbox.entity.pokemon.consumer.PokemonForm;
import com.murilo.looqbox.entity.pokemon.consumer.util.WebConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private WebConsumer consumer;

    public WebConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(WebConsumer consumer) {
        this.consumer = consumer;
    }

    //Serviço para listar todos pokemons

    public List<PokemonForm> findAllPokemon() {
        return consumer.searchAllPokemon(811);
    }


}
