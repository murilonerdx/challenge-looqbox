package com.murilo.looqbox.resources;

import com.murilo.looqbox.entity.pokemon.consumer.responses.PokemonsResponseUnique;
import com.murilo.looqbox.entity.pokemon.consumer.responses.PokemonsResponse;
import com.murilo.looqbox.entity.pokemon.consumer.model.Spotlight;
import com.murilo.looqbox.entity.pokemon.consumer.model.PokemonForm;
import com.murilo.looqbox.entity.pokemon.consumer.model.Result;
import com.murilo.looqbox.entity.pokemon.consumer.services.ConsumerService;
import com.murilo.looqbox.entity.pokemon.entities.Pokemon;
import com.murilo.looqbox.entity.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonResource {

    //OBS: Por que não salvar todas informações no banco ? Simplismente pois isso consumiria a memoria, então como os dados são estaticos e podemos manusear de forma simples, então não é necessario jogar todos os pokemons no banco
    //Pois a unica utilidade do JPA e integração com o banco seria usar com o CRUD de maneira a criar, deletar, ou atualizar algo. Nesse caso não foi necessario, apenas consumimos e tratamos a maneira na qual iriamos usar em especifico

    @Autowired
    ConsumerService consumer;

    @Autowired
    PokemonService pokemonService;

    //TODO: Listar todos pokemons que existem em um range de 811
    //Listando todos pokemons
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<PokemonForm>> findAll() {
        List<PokemonForm> list = consumer.findAllPokemon();
        return ResponseEntity.ok().body(list);
    }

    //Como padrão você vai passar a lista de pokemons e a palavra que se aproxima ao nome do pokemon
    private Map<String, List<Spotlight>> Search(List<String> pokemons, String text) {
        //Uma lista de chave valor para armazenar as palavras e os pokemons
        Map<String, List<Spotlight>> filtered = new HashMap<>();

        //Definindo um padrão que deve ser levado em consideração com a letra estilo ou o que condiz com a palavra digitada
        Pattern searchPattern = Pattern.compile(text);
        for (String pokemon : pokemons) {
            Matcher match = searchPattern.matcher(pokemon);
            if (match.find()) {
                //Caso ele encontre então ele vai pro proximo passo
                List<Spotlight> spotlight = new ArrayList<>();
                //Aqui está a magica para setar os valores do inicio da palavra e o final da palavra
                spotlight.add(new Spotlight(pokemon, text, match.start(), match.end() - 1));
                //Então ele vai percorrer todo o a palavra digitada procurando pelas letras que condiz com as letras do nome do pokemon
                filtered.put(pokemon, spotlight);
                while (match.find()) {
                    //Então adiciona o nome do pokemon e sua spotlight de acordo com pokemon digitado ou encontrado...
                    filtered.get(pokemon).add(new Spotlight(pokemon, text, match.start(), match.end() - 1));
                }
            }
        }

        //Criando uma nova resposta para agora ordenar todas as palavras encontradas
        Map<String, List<Spotlight>> response = new HashMap<>();
        //Ordenando de acordo com a posição
        List<String> keysSorted = SortPokemons(filtered);
        for (String pokemon : keysSorted) {
            response.put(pokemon, filtered.get(pokemon));
        }
        return response;
    }

    //BubbleSort
    //Complexidade pior situação é O(n^2) e na melhor situação O(n)
    //Pior situação é quando precisar ordernar todas as posições e melhor situação é quando somente a primeira posição precisa ser ordenada Ex: b, a, c, d
    private static List<String> SortPokemons(Map<String, List<Spotlight>> pokemons) {


        String temp;
        boolean sorted = true;
        List<String> sortedList = new ArrayList<>(pokemons.keySet());

        //Ele vai atualizar, e armazenar na temp um valor temporario no qual ele vai ter que trocar
        //Então se o valor for maior que o proximo valor, ele vai salvar o proximo valor, e setar para trás, assim ele vai percorrer e vai fazer isso com todos
        for (int i = 0; i < pokemons.size() - 1 && sorted; ++i) {
            sorted = false;
            for (int j = i + 1; j < pokemons.size(); ++j) {
                if (sortedList.get(i).compareToIgnoreCase(sortedList.get(j)) > 0) {
                    //Valor temporario para ser mudado de posição
                    temp = sortedList.get(i);
                    //Mudança de posição
                    sortedList.set(i, sortedList.get(j));
                    sortedList.set(j, temp);
                    sorted = true;
                }
            }
        }

        return sortedList;
    }

    //TODO: ResponseEntity generico, para que possa voltar dois tipos de valores diferentes
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //TODO: Parametro na requisição como q como foi pedido no documento
    public ResponseEntity<?> findByNamePokemon(@RequestParam(name = "q") String name) {
        //Aqui será guardado os nomes dos pokemons, essa estrutura foi feita como principal foco separar de modo que não limite apenas em uma variavel todo a lista, distribuindo para usar com outros focos
        Result result;

        //Os items que estão aqui dentro foram colocados aqui de modo particular, pois cada vez que rodar eu quero sobrescrever os valores que já existem.
        List<Result> manyListPokemons = new ArrayList<>();

        //Service para listar todos os pokemons que existem, e listando eles atributos com nomes iguais, para que ele possa referenciar e adicionar em cima deles.
        List<PokemonForm> pokemonFormList = consumer.findAllPokemon();

        //Verificador foi criado para saber quando vai ter apenas um item na lista, a logica foi se só existe um item na lista quer dizer que ele pegou justamente aquele que seria o ultimo ou o unico, então ele sai do loop
        //Ao sar do loop eu já crio o destaque para aparecer na tela da forma que eu quero
        //Utilizando matriz para percorrer dois objetos distintos
        for (PokemonForm poke : pokemonFormList) {
            for (Result r : poke.getResults()) {
                //A logica para pegar o item é, ele vai percorrer toda a lista de pokemons buscando pelo item que tem a quantidade de caracters igual ou maior do que o usuario digitou, e comparando
                //se desde o primeiro até o ultimo contando apartir do caracter é igual ao que foi digitado
                //Existem duas formas eu fiz da seguinte forma, eu quero procurar por todos item que contem o valor digitado no nome
                //Porém para pegar apenas os que começam com o valor digitado, você pode definir da seguinte forma
                //if (r.getName().length() >= name.length() && r.getName().substring(0, name.length()).contentEquals(name)){}
                //Não foi deixado claro no documento se como padrão o sempre começa do zero até o nome do pokemon, ou se pode começar de qualquer posição
                //Nesse caso eu fiz começando de qualquer posição que o usuario digita
                if (r.getName().contains(name)) {
                    //Toda vez que ele entrar eu quero que ele instancia um novo resultado e joga dentro de uma lista pra mostrar para o usuario os itens cujo o nome é parecido com o que foi digitado
                    result = new Result();
                    result.setName(r.getName());
                    manyListPokemons.add(result);
                }
            }
        }
        //Vou transformar a lista de nomes aproximados em uma lista de string de pokemon
        List<String> pokemons = manyListPokemons.stream().map(Result::getName).collect(Collectors.toList());
        Map<String, List<Spotlight>> response = Search(pokemons, name);
        //Tirar valores duplicados
        Set<Spotlight> temp = new HashSet<>();
        response.values().forEach(temp::addAll);
        if (temp.size() > 1){
            //Fazer ele retornar o padrão proposto no arquivo
            return ResponseEntity.ok().body(new PokemonsResponse(temp));
        }
        //Fazer ele retornar o padrão proposto no arquivo
        return ResponseEntity.ok().body(new PokemonsResponseUnique(response).getListResultResponse());
    }


    //TODO: Listar pokemons do meu bau - Pokemons que foram pesquisados e encontrados
    @RequestMapping(path = "/bau", method = RequestMethod.GET)
    public ResponseEntity<List<Pokemon>> findAllBau() {
        List<Pokemon> list = pokemonService.listPokemonTrunk();
        return ResponseEntity.ok().body(list);
    }

}
