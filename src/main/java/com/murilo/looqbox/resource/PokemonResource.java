package com.murilo.looqbox.resource;

import com.murilo.looqbox.domain.responses.PokemonResponseSingle;
import com.murilo.looqbox.domain.responses.PokemonResponse;
import com.murilo.looqbox.domain.model.PokemonSpotlight;
import com.murilo.looqbox.domain.model.PokemonRequest;
import com.murilo.looqbox.domain.model.Pokemon;
import com.murilo.looqbox.domain.service.ConsumerService;
import com.murilo.looqbox.domain.util.Algorithm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonResource {

    //OBS: Por que não salvar todas informações no banco ? Simplismente pois isso consumiria a memoria, então como os dados são estaticos e podemos manusear de forma simples, então não é necessario jogar todos os pokemons no banco
    //Pois a unica utilidade do JPA e integração com o banco seria usar com o CRUD de maneira a criar, deletar, ou atualizar algo. Nesse caso não foi necessario, apenas consumimos e tratamos a maneira na qual iriamos usar em especifico

    final
    ConsumerService consumer;

    public PokemonResource(ConsumerService consumer) {
        this.consumer = consumer;
    }

    //TODO: Listar todos pokemons que existem em um range de 811
    //Listando todos pokemons
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<PokemonRequest>> findAll() {
        List<PokemonRequest> list = consumer.findAllPokemon();
        return ResponseEntity.ok().body(list);
    }


    //TODO: ResponseEntity generico, para que possa voltar dois tipos de valores diferentes
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //TODO: Parametro na requisição como q como foi pedido no documento
    public ResponseEntity<?> findByNamePokemon(@RequestParam(name = "q") String name) {
        //Aqui será guardado os nomes dos pokemons, essa estrutura foi feita como principal foco separar de modo que não limite apenas em uma variavel todo a lista, distribuindo para usar com outros focos
        //Os items que estão aqui dentro foram colocados aqui de modo particular, pois cada vez que rodar eu quero sobrescrever os valores que já existem.
        //Service para listar todos os pokemons que existem, e listando eles atributos com nomes iguais, para que ele possa referenciar e adicionar em cima deles.
        List<PokemonRequest> pokemonRequestList = consumer.findAllPokemon();

        //Todo algoritimo estara aqui dentro, para não ficar tudo no controller, eu passo a lista e o nome em particular
        List<Pokemon> manyListPokemons = Algorithm.listPokemon(pokemonRequestList, name);

        //Vou transformar a lista de nomes aproximados em uma lista de string de pokemon
        List<String> pokemons = manyListPokemons.stream().map(Pokemon::getName).collect(Collectors.toList());
        Map<String, List<PokemonSpotlight>> response = Algorithm.Search(pokemons, name);
        if (response.size() == 1){
            //Fazer ele retornar o padrão proposto no arquivo
            return ResponseEntity.ok().body(new PokemonResponseSingle(response).getListResultResponse());
        }
        Set<PokemonSpotlight> manyResponse = new LinkedHashSet<>();
        response.values().forEach(manyResponse::addAll);
        //Fazer ele retornar o padrão proposto no arquivo
        return ResponseEntity.ok().body(new PokemonResponse(manyResponse));
    }

}
