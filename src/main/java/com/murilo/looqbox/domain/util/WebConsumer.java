package com.murilo.looqbox.domain.util;

import com.murilo.looqbox.domain.model.PokemonForm;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
public class WebConsumer {
    public List<PokemonForm> searchAllPokemon(Integer limit) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate template = builder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "poke api");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            List<PokemonForm> searchAllPokemonForm;
            //Responsavel por ler o Json e tratar ela para a clase pokemonForm
            ResponseEntity<PokemonForm> pokemonResponse = template.exchange("https://pokeapi.co/api/v2/pokemon/?limit=" + limit
                    , HttpMethod.GET
                    , entity
                    , PokemonForm.class);

            searchAllPokemonForm = Collections.singletonList(Objects.requireNonNull(pokemonResponse.getBody()));
            return searchAllPokemonForm;
        } catch (Exception e) {
            return null;
        }
    }

}
