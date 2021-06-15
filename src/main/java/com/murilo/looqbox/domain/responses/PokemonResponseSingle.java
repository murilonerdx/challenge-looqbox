package com.murilo.looqbox.domain.responses;

import com.murilo.looqbox.domain.model.PokemonSpotlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PokemonResponseSingle {
    private List<PokemonSpotlight> listResultResponse = new ArrayList<>();
    public PokemonResponseSingle(Map<String, List<PokemonSpotlight>> search) {
        search.values().forEach(listResultResponse::addAll);
    }

    public List<PokemonSpotlight> getListResultResponse() {
        //Percorrendo e adicionado o pre no highlight
        listResultResponse.forEach(x->x.setHighlight("<pre>"+x.getHighlight()+"<pre>"));
        return listResultResponse;
    }

    public void setListResultResponse(List<PokemonSpotlight> listResultResponse) {
        this.listResultResponse = listResultResponse;
    }
}
