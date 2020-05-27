package guru.springframework.domain;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne //if we delete notes we dont want to delete recipe so there is no cascade here.
    private Recipe recipe;
    @Lob //with string it becomes clob
    private String recipeNotes;


    //Clob -> character large objects blob binary large objects//string default of Hibernate and JPA İS 255 Characters. ava String
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
