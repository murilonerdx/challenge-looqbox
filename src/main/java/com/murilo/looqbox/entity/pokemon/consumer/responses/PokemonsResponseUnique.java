package com.murilo.looqbox.entity.pokemon.consumer.responses;

import com.murilo.looqbox.entity.pokemon.consumer.model.Spotlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PokemonsResponseUnique {
    private List<Spotlight> listResultResponse = new ArrayList<>();
    public PokemonsResponseUnique(Map<String, List<Spotlight>> search) {
        search.values().forEach(listResultResponse::addAll);
    }

    public List<Spotlight> getListResultResponse() {
        return listResultResponse;
    }

    public void setListResultResponse(List<Spotlight> listResultResponse) {
        this.listResultResponse = listResultResponse;
    }
}
