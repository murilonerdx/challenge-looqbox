package com.murilo.looqbox.domain.service;

import com.murilo.looqbox.domain.model.Pokemon;
import com.murilo.looqbox.domain.request.SpotilightRequest;

import java.util.List;

public interface WebConsumerService {
    List<SpotilightRequest> findAllSpotilightReturnPageRequest();
    List<Pokemon> findAllPokemonReturnPageRequest();
}
