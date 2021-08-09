package com.murilo.looqbox.domain.service.impl;


import com.murilo.looqbox.domain.model.Pokemon;
import com.murilo.looqbox.domain.request.SpotilightRequest;
import com.murilo.looqbox.domain.service.WebConsumerService;
import com.murilo.looqbox.domain.util.HttpServicePokemon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebConsumerServiceImpl implements WebConsumerService {

    //Serviço para listar todos pokemons
    @Override
    public List<SpotilightRequest> findAllSpotilightReturnPageRequest() {
        return HttpServicePokemon.searchAllPokemon(811);
    }

    @Override
    public List<Pokemon> findAllPokemonReturnPageRequest() {
        return HttpServicePokemon.transformSpotilightRequestInPokemon(811);
    }
}
