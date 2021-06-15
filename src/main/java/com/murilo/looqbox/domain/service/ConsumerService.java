package com.murilo.looqbox.domain.service;


import com.murilo.looqbox.domain.model.PokemonRequest;
import com.murilo.looqbox.domain.util.WebConsumer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    private final WebConsumer consumer;

    public ConsumerService(WebConsumer consumer) {
        this.consumer = consumer;
    }
    //Serviço para listar todos pokemons
    public List<PokemonRequest> findAllPokemon() {
        return consumer.searchAllPokemon(811);
    }


}
