
package PokeAPIFragoso.PokeApi.ML;

import java.util.List;

public class Pokemon {
    
    private String name; 
    private String id;
    public Sprites sprites; 
    public List<Types> types;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    
    
    
    
    

  
}
