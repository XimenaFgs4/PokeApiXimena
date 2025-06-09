/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PokeAPIFragoso.PokeApi.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/PokeApi")
public class PokemonController {

    private RestTemplate restTemplate = new RestTemplate();
    private String urlPoke = "https://pokeapi.co/api/v2/pokemon"; 
    
    @GetMapping
    public String PokeApi(Model model, HttpSession httpSession) {
        try {
            Result result = new Result();
            ResponseEntity<Result<PokemonURL>> getPokemon = restTemplate.exchange("https://pokeapi.co/api/v2/pokemon",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<PokemonURL>>() {
                        
                    });

            if(getPokemon.getStatusCode().is2xxSuccessful()) {
                result = getPokemon.getBody();
                List<PokemonURL> urlPoke = new ArrayList<>();
                urlPoke = result.results; 
                
                
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }
}
