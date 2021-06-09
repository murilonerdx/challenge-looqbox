package com.murilo.looqbox.resources;

import com.murilo.looqbox.entity.pokemon.consumer.Spotlight;
import com.murilo.looqbox.entity.pokemon.consumer.PokemonForm;
import com.murilo.looqbox.entity.pokemon.consumer.Result;
import com.murilo.looqbox.entity.pokemon.consumer.services.ConsumerService;
import com.murilo.looqbox.entity.pokemon.entities.Pokemon;
import com.murilo.looqbox.entity.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonResource {

    //OBS: Por que não salvar todas informações no banco ? Simplismente pois isso consumiria a memoria, então como os dados são estaticos e podemos manusear de forma simples, então não é necessario jogar todos os pokemons no banco
    //Pois a unica utilidade do JPA e integração com o banco seria usar com o CRUD de maneira a criar, deletar, ou atualizar algo. Nesse caso não foi necessario, apenas consumimos e tratamos a maneira na qual iriamos usar em especifico

    @Autowired
    ConsumerService consumer;

    @Autowired
    PokemonService pokemonService;

    Pokemon pokemon = new Pokemon();



    //TODO: Listar todos pokemons que existem em um range de 811
    @RequestMapping(path = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<PokemonForm>> findAll() {
        List<PokemonForm> list = consumer.findAllPokemon();
        return ResponseEntity.ok().body(list);
    }


    //TODO: ResponseEntity generico, para que possa voltar dois tipos de valores diferentes
    @RequestMapping(method = RequestMethod.GET)
    //TODO: Parametro na requisição como q como foi pedido no documento
    public ResponseEntity<?> findByNamePokemon(@RequestParam(name = "q") String name) {
        //Aqui será guardado os nomes dos pokemons, essa estrutura foi feita como principal foco separar de modo que não limite apenas em uma variavel todo a lista, distribuindo para usar com outros focos
        Result result;

        //Os items que estão aqui dentro foram colocados aqui de modo particular, pois cada vez que rodar eu quero sobrescrever os valores que já existem.
        List<Result> nomesProx = new ArrayList<>();

        //Criando padrão que será mostrado para o usuario, a estrutura padronizada
        Spotlight destaque = new Spotlight();

        //Service para listar todos os pokemons que existem, e listando eles atributos com nomes iguais, para que ele possa referenciar e adicionar em cima deles.
        List<PokemonForm> pokemonFormList = consumer.findAllPokemon();

        //Verificador foi criado para saber quando vai ter apenas um item na lista, a logica foi se só existe um item na lista quer dizer que ele pegou justamente aquele que seria o ultimo ou o unico, então ele sai do loop
        //Ao sar do loop eu já crio o destaque para aparecer na tela da forma que eu quero
        boolean verificador = false;

        //Utilizando matriz para percorrer dois objetos distintos
        for (PokemonForm poke : pokemonFormList) {
            for (Result r : poke.getResults()) {
                //
                //A logica para pegar o item é, ele vai percorrer toda a lista de pokemons buscando pelo item que tem a quantidade de caracters igual ou maior do que o usuario digitou, e comparando
                //se desde o primeiro até o ultimo contando apartir do caracter é igual ao que foi digitado
                //Existem duas formas eu fiz da seguinte forma, eu quero procurar por todos item que contem o valor digitado no nome
                //Porém para pegar apenas os que começam com o valor digitado, você pode definir da seguinte forma
                //if (r.getName().length() >= name.length() && r.getName().substring(0, name.length()).contentEquals(name)){}
                //Não foi deixado claro no documento se como padrão o sempre começa do zero até o nome do pokemon, ou se pode começar de qualquer posição
                //Nesse caso eu fiz começando de qualquer posição que o usuario digita
                if (r.getName().length() >= name.length() && r.getName().contains(name)) {
                    //Toda vez que ele entrar eu quero que ele instancia um novo resultado e joga dentro de uma lista pra mostrar para o usuario os itens cujo o nome é parecido com o que foi digitado
                    result = new Result();
                    result.setName(r.getName());
                    nomesProx.add(result);
                }
            }
            //Caso ter apenas um item na lista ele vai entrar na condicional
            if (nomesProx.size() == 1) {
                verificador = true;
                //Então farei toda configuração para que apareça da maneira que foi pedido no documento
                //Qual é o nome do objeto encontrado
                destaque.setName(nomesProx.get(0).getName());
                //Qual caracter que começa
                //Daria para ter feito em match e usar Stream, porém optei em fazer o padrão mesmo, mais simples.
                destaque.setStart(destaque.getName().length() - name.length() );
                //Qual caracter que termina
                destaque.setEnd(name.length());
                //E de acordo com que palavra que o usuario digitou que achou o pokemon
                destaque.setHighlight("<pre>"+name+"<pre>");
            }
        }
        if (verificador) {
            //E por fim ele vai retornar o destaque caso existir e caso estiver de acordo com os passos anteriores
            //PokemonTrunk quer dizer que o pokemon que você "Achou" estara no bau, ou melhor na nossa api REST
            pokemon.setName(destaque.getName());
            pokemon.setStart(destaque.getStart());
            pokemon.setEnd(destaque.getEnd());
            pokemon.setHighlight(destaque.getHighlight());

            //Guardar no bau os pokemons que foram procurados e encontrados para ter um controle apartir do que ele acha apatir do que o usuario digita
            pokemonService.save(pokemon);

            return ResponseEntity.ok().body(destaque);
        }
        //Se não ele mostrara para o usuario a lista de nomes iguais
        return ResponseEntity.ok().body(nomesProx);
    }


    //TODO: Listar pokemons do meu bau - Pokemons que foram pesquisados e encontrados
    @RequestMapping(path = "/bau", method = RequestMethod.GET)
    public ResponseEntity<List<Pokemon>> findAllBau() {
        List<Pokemon> list = pokemonService.listPokemonTrunk();
        return ResponseEntity.ok().body(list);
    }



}
