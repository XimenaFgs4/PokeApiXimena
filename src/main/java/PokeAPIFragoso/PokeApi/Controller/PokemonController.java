package PokeAPIFragoso.PokeApi.Controller;

import PokeAPIFragoso.PokeApi.ML.Pokemon;
import PokeAPIFragoso.PokeApi.ML.PokemonURL;
import PokeAPIFragoso.PokeApi.ML.Result;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/PokeApi")
public class PokemonController {

    private RestTemplate restTemplate = new RestTemplate();
    private String urlPoke = "https://pokeapi.co/api/v2/pokemon";

    @GetMapping
    public String PokeApi(Model model) {
        try {
            Result result = new Result();
            ResponseEntity<Result<PokemonURL>> getPokemonUrl = restTemplate.exchange("https://pokeapi.co/api/v2/pokemon",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<PokemonURL>>() {

            });

            if (getPokemonUrl.getStatusCode().is2xxSuccessful()) {
                result = getPokemonUrl.getBody();
                List<PokemonURL> urlPoke = new ArrayList<>();
                urlPoke = result.results;

                List<Pokemon> pokemones = new ArrayList<>();

                for (PokemonURL urlPokes : urlPoke) {
                    ResponseEntity<Pokemon> getPoke = restTemplate.exchange(urlPokes.getURL(),
                            HttpMethod.GET,
                            HttpEntity.EMPTY,
                            new ParameterizedTypeReference<Pokemon>() {

                    });

                    if (getPoke.getStatusCode().is2xxSuccessful()) {
                        pokemones.add(getPoke.getBody());
                    }

                }
                
                
                model.addAttribute("listaPokemones", pokemones);
                model.addAttribute("results", getPokemonUrl.getBody());

            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return "ViewCards";

    }
    
    @GetMapping("PokemonName/{name}")
    public String DetailPokemon(@PathVariable String name, Model model) {
        
        Pokemon pokemon = new Pokemon(); 
        
        try{     
            ResponseEntity<Pokemon> getDetailPokemon = restTemplate.exchange("https://pokeapi.co/api/v2/pokemon/" + name,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Pokemon>(){
                        
                    });
            
            model.addAttribute("namePokemon", getDetailPokemon.getBody());
            getDetailPokemon.getBody();
            
        }catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
        return "DetailPokemon";
        
    }
    
    
}
