package com.murilo.looqbox.entity.pokemon.consumer.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.murilo.looqbox.entity.pokemon.consumer.model.Spotlight;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonsResponse implements Serializable {
    public PokemonsResponse(Set<Spotlight> results)
    {
        this.result = results.stream().map(Spotlight::getName).collect(Collectors.toList());
    }

    public List<String> result;
}
