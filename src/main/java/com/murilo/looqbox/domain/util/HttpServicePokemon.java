package com.murilo.looqbox.domain.util;

import com.murilo.looqbox.domain.model.Pokemon;
import com.murilo.looqbox.domain.request.SpotilightRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class HttpServicePokemon {
    public static final String URL = "https://pokeapi.co/api/v2/pokemon/?limit=";

    public static List<SpotilightRequest> searchAllPokemon(Integer limit) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate template = builder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "poke api");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            List<SpotilightRequest> searchAllSpotilightRequest;
            //Responsavel por ler o Json e tratar ela para a clase pokemonForm
            ResponseEntity<SpotilightRequest> pokemonRequest = template.exchange(URL + limit
                    , HttpMethod.GET
                    , entity
                    , SpotilightRequest.class);

            searchAllSpotilightRequest = Collections.singletonList(Objects.requireNonNull(pokemonRequest.getBody()));
            return searchAllSpotilightRequest;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Pokemon> transformSpotilightRequestInPokemon(Integer limit) {
        List<Pokemon> listPokemon = new ArrayList<>();
        for(SpotilightRequest spot : Objects.requireNonNull(searchAllPokemon(limit))){
            listPokemon.addAll(spot.getPokemons());
        }
        return listPokemon;
    }

}
