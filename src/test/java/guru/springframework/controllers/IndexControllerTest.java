package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;
    IndexController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeService);
    }


    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    @Test
    public void getIndexPage() throws Exception {

        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipes.add(recipe);
        //n this example, we are mocking the service and telling
        // "when the mock recipeService.getRecipes() is called then the mock should return a Set of recipe".
        when(recipeService.getRecipes()).thenReturn(recipes);
        //ArgumentCaptor is used to capture arguments for mocked methods.
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);


        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        //verify(model, times(1)).addAttribute(eq("recipes"),anySet());//we
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

        //verify(model, times(1)).addAttribute(eq("recipes"), eq(recipes)); can this work?
    }
}

/*

mock bir şeyin methodu çağrılırken bizim verdiğimiz argument ı mı almış testi
ArgumentCaptor is used to capture arguments for mocked methods.

In this example, we are mocking the service and telling "when the mock recipeService.getRecipes() is called then the mock should return a Set of recipe".



We then created a ArgumentCapture to capture the argument (Set<Recipe>) for the mocked method.



Next we verified this statement in the controller method.



model.addAttribute("recipes", recipeService.getRecipes());



We did it as:



verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());



The precding code can be read as. "Verify that addAttribute has been called on model once with the attribute name "recipes" and value Set<Recipe> - note argumentCaptor.capture() in the verify statement above is returning Set<Recipe>"



Finally we asserted that the ArgumentCapture object holds a Set with two Recipe objects

Consider you have a method:

class A {
    public void foo(OtherClass other) {
        SomeData data = new SomeData("Some inner data");
        other.doSomething(data);
    }
}
Now if you want to check the inner data you can use the captor:

// Create a mock of the OtherClass
OtherClass other = mock(OtherClass.class);

// Run the foo method with the mock
new A().foo(other);

// Capture the argument of the doSomething function
ArgumentCaptor<SomeData> captor = ArgumentCaptor.forClass(SomeData.class);
verify(other, times(1)).doSomething(captor.capture());

// Assert the argument
SomeData actual = captor.getValue();
assertEquals("Some inner data", actual.innerData);
 */