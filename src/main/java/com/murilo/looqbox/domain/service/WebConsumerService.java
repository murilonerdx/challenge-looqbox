package com.murilo.looqbox.domain.service;

import com.murilo.looqbox.domain.request.PokemonRequest;

import java.util.List;

public interface WebConsumerService {
    List<PokemonRequest> findAllPokemonReturnPageRequest();
}
