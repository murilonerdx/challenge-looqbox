package com.murilo.looqbox.domain.responses;

import com.murilo.looqbox.domain.model.Spotlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokemonResponseSingle {
    private List<Spotlight> listResultResponse = new ArrayList<>();
    public PokemonResponseSingle(Map<String, List<Spotlight>> search) {
        search.values().forEach(listResultResponse::addAll);
    }

    public List<Spotlight> getListResultResponse() {
        //Percorrendo e adicionado o pre no highlight
        listResultResponse.forEach(x->x.setHighlight("<pre>"+x.getHighlight()+"<pre>"));
        return listResultResponse;
    }

    public void setListResultResponse(List<Spotlight> listResultResponse) {
        this.listResultResponse = listResultResponse;
    }
}
