package com.murilo.looqbox.resource;

import com.murilo.looqbox.domain.responses.SpotlightResponseResultSingle;
import com.murilo.looqbox.domain.responses.SpotlightResponseResultList;
import com.murilo.looqbox.domain.model.Spotlight;
import com.murilo.looqbox.domain.model.Pokemon;
import com.murilo.looqbox.domain.service.impl.WebConsumerServiceImpl;
import com.murilo.looqbox.domain.util.Algorithm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/pokemons")
public class PokemonResource {

  // OBS: Por que não salvar todas informações no banco ? Simplismente pois isso consumiria a
  // memoria, então como os dados são estaticos e podemos manusear de forma simples, então não é
  // necessario jogar todos os pokemons no banco
  // Pois a unica utilidade do JPA e integração com o banco seria usar com o CRUD de maneira a
  // criar, deletar, ou atualizar algo. Nesse caso não foi necessario, apenas consumimos e tratamos
  // a maneira na qual iriamos usar em especifico

  final WebConsumerServiceImpl consumer;

  public PokemonResource(WebConsumerServiceImpl consumer) {
    this.consumer = consumer;
  }

  // TODO: Listar todos pokemons que existem em um range de 811
  // Listando todos pokemons
  @RequestMapping(
      path = {"/AllPokemons", "/"},
      method = RequestMethod.GET)
  public ResponseEntity<List<Pokemon>> findAll() {
    return ResponseEntity.ok().body(consumer.findAllPokemonReturnPageRequest());
  }

  // TODO: ResponseEntity generico, para que possa voltar dois tipos de valores diferentes
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  // TODO: Parametro na requisição como q como foi pedido no documento
  public ResponseEntity<?> findByNamePokemon(@RequestParam(name = "q") String name) {
    // Aqui será guardado os nomes dos pokemons, essa estrutura foi feita como principal foco
    // separar de modo que não limite apenas em uma variavel todo a lista, distribuindo para usar
    // com outros focos
    // Os items que estão aqui dentro foram colocados aqui de modo particular, pois cada vez que
    // rodar eu quero sobrescrever os valores que já existem.
    // Service para listar todos os pokemons que existem, e listando eles atributos com nomes
    // iguais, para que ele possa referenciar e adicionar em cima deles.
    // Todo algoritimo estara aqui dentro, para não ficar tudo no controller, eu passo a lista e o
    // nome em particular
    // Vou transformar a lista de nomes aproximados em uma lista de string de pokemon
    Map<String, List<Spotlight>> response =
        Algorithm.Search(
            Algorithm.listPokemon(consumer.findAllSpotilightReturnPageRequest(), name).stream()
                .map(Pokemon::getName)
                .collect(Collectors.toList()),
            name);
    if (response.size() == 1) {
      // Fazer ele retornar o padrão proposto no arquivo
      return ResponseEntity.ok()
          .body(new SpotlightResponseResultSingle(response).getListResultResponse());
    }
    Set<Spotlight> manyResponse = new LinkedHashSet<>();
    response.values().forEach(manyResponse::addAll);
    // Fazer ele retornar o padrão proposto no arquivo
    return ResponseEntity.ok().body(new SpotlightResponseResultList(manyResponse));
  }
}
