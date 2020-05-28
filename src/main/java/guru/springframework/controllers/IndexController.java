package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
//controller tag makes this a spring bean
public class IndexController {

   private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
         log.debug("Getting index page.");
        //Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        //Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        //System.out.println("Cat Id is: " + categoryOptional.get().getId());
        model.addAttribute("recipes2", recipeService.getRecipes());
        //    <tr th:each="recipe : ${recipes}"> dolardan sonraki recipes ın adı yukarıdaki.
        return "index";
    }
}
