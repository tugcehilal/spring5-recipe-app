package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//ınverse
@Data
public class Recipe {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) //MySql (oracle recently added this.)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    @OneToMany(cascade =  CascadeType.ALL,  mappedBy = "recipe")//target preperty in Ingredient class.
    //is going to fill on a the child called "recipe"
    private Set<Ingredient> ingredients = new HashSet<>(); //böyle initialize olmasa null pointer hatası oluyomuş

    @Lob //blob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING ) //Ordinal default. ancak araya bir şey koyduğun zaman enumlarda
    //mevcut kodda değişmiş oluyor.çünkü onun sıralamadaki yeri değişiyor enumda
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable (name = "recipe_category",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn (name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {

        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }


}
