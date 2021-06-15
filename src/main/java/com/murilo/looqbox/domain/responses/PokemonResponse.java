package com.murilo.looqbox.domain.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.murilo.looqbox.domain.model.PokemonSpotlight;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse implements Serializable {
    public PokemonResponse(Set<PokemonSpotlight> results)
    {
        this.result = results.stream().map(PokemonSpotlight::getName).collect(Collectors.toList());
    }

    public final List<String> result;
}
