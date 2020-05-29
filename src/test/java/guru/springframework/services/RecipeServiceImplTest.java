package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);//initialize mocks
                                                    //this tells give me a repository

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {

        Recipe recipe = new Recipe();
        //recipe.setDescription("recipe1");
        //Recipe recipe2= new Recipe();
        //recipe2.setDescription("recipe2");
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);
        //recipesData.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals( 1,recipes.size());
        //verify findAll is called only one time
        verify(recipeRepository, times(1)).findAll();
    }
}