package com.murilo.looqbox.domain.util;

import com.murilo.looqbox.domain.model.Pokemon;
import com.murilo.looqbox.domain.request.SpotilightRequest;
import com.murilo.looqbox.domain.model.Spotlight;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class Algorithm {

  // Como padrão você vai passar a lista de pokemons e a palavra que se aproxima ao nome do pokemon
  public static Map<String, List<Spotlight>> Search(List<String> pokemons, String text) {
    // Uma lista de chave valor para armazenar as palavras e os pokemons
    Map<String, List<Spotlight>> filtered = new HashMap<>();

    // Definindo um padrão que deve ser levado em consideração com a letra estilo ou o que condiz
    // com a palavra digitada
    Pattern searchPattern = Pattern.compile(text);
    for (String pokemon : pokemons) {
      Matcher match = searchPattern.matcher(pokemon);
      if (match.find()) {
        // Caso ele encontre então ele vai pro proximo passo
        List<Spotlight> spotlights = new ArrayList<>();
        // Aqui está a magica para setar os valores do inicio da palavra e o final da palavra
        spotlights.add(new Spotlight(pokemon, text, match.end() - 1));
        // Então ele vai percorrer todo o a palavra digitada procurando pelas letras que condiz com
        // as letras do nome do pokemon
        filtered.put(pokemon, spotlights);
        while (match.find()) {
          // Então adiciona o nome do pokemon e sua spotlight de acordo com pokemon digitado ou
          // encontrado...
          filtered.get(pokemon).add(new Spotlight(pokemon, text, match.end() - 1));
        }
      }
    }

    // Criando uma nova resposta para agora ordenar todas as palavras encontradas
    Map<String, List<Spotlight>> responses = new LinkedHashMap<>();
    // Ordenando de acordo com a posição
    List<String> keysSorted = SortPokemons(filtered);
    for (String pokemon : keysSorted) {
      responses.put(pokemon, filtered.get(pokemon));
    }
    return responses;
  }

  public static List<Pokemon> listPokemon(
      List<SpotilightRequest> spotilightRequestList, String name) {
    Pokemon pokemon;
    List<Pokemon> manyListPokemons = new ArrayList<>();
    // Verificador foi criado para saber quando vai ter apenas um item na lista, a logica foi se só
    // existe um item na lista quer dizer que ele pegou justamente aquele que seria o ultimo ou o
    // unico, então ele sai do loop
    // Ao sar do loop eu já crio o destaque para aparecer na tela da forma que eu quero
    // Utilizando matriz para percorrer dois objetos distintos
    for (SpotilightRequest poke : spotilightRequestList) {
      for (Pokemon r : poke.getResults()) {
        // A logica para pegar o item é, ele vai percorrer toda a lista de pokemons buscando pelo
        // item que tem a quantidade de caracters igual ou maior do que o usuario digitou, e
        // comparando
        // se desde o primeiro até o ultimo contando apartir do caracter é igual ao que foi digitado
        // Existem duas formas eu fiz da seguinte forma, eu quero procurar por todos item que contem
        // o valor digitado no nome
        // Porém para pegar apenas os que começam com o valor digitado, você pode definir da
        // seguinte forma
        // if (r.getName().length() >= name.length() && r.getName().substring(0,
        // name.length()).contentEquals(name)){}
        // Não foi deixado claro no documento se como padrão o sempre começa do zero até o nome do
        // pokemon, ou se pode começar de qualquer posição
        // Nesse caso eu fiz começando de qualquer posição que o usuario digita
        if (r.getName().contains(name)) {
          // Toda vez que ele entrar eu quero que ele instancia um novo resultado e joga dentro de
          // uma lista pra mostrar para o usuario os itens cujo o nome é parecido com o que foi
          // digitado
          pokemon = new Pokemon();
          pokemon.setName(r.getName());
          manyListPokemons.add(pokemon);
        }
      }
    }
    return manyListPokemons;
  }

  // BubbleSort
  // Complexidade pior situação é O(n^2) e na melhor situação O(n)
  // Pior situação é quando precisar ordernar todas as posições e melhor situação é quando somente a
  // primeira posição precisa ser ordenada Ex: b, a, c, d
  private static List<String> SortPokemons(Map<String, List<Spotlight>> pokemons) {
    String temp;
    boolean sorted = true;
    List<String> sortedList = new ArrayList<>(pokemons.keySet());

    // Ele vai atualizar, e armazenar na temp um valor temporario no qual ele vai ter que trocar
    // Então se o valor for maior que o proximo valor, ele vai salvar o proximo valor, e setar para
    // trás, assim ele vai percorrer e vai fazer isso com todos
    // Ele vai comprar com todos
    for (int i = 0; i < pokemons.size() - 1 && sorted; ++i) {
      sorted = false;
      for (int j = i + 1; j < pokemons.size(); ++j) {
        if (sortedList.get(i).compareToIgnoreCase(sortedList.get(j)) > 0) {
          // Valor temporario para ser mudado de posição
          temp = sortedList.get(i);
          // Mudança de posição, mudando a posição antiga para a nova, e salvando a antiga em uma
          // variavel temporaria para usar depois na nova posição
          sortedList.set(i, sortedList.get(j));
          sortedList.set(j, temp);
          sorted = true;
        }
      }
    }
    return sortedList;
  }
}
