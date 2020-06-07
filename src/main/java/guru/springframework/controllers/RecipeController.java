package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
//{id} ile  @PathVariable String id ismi ile aynı olmalı
    @RequestMapping("/recipe/show/{id}")
        public String showById (@PathVariable String id, Model model){
              model.addAttribute("recipe2", recipeService.findById(new Long(id)));

              return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe (Model model){

        model.addAttribute("recipe5", new RecipeCommand());

        return "recipe/recipeform";

    }

    //@PostMapping("recipe")
    // @Modelattribute annotation tells Spring to bind the form post parameters to the RecipeCommand object
    //@RequestMapping(name = "recipe", method = RequestMethod.POST)
    /*@PostMapping
    @RequestMapping(name = "recipe3")*/

    @PostMapping(name = "recipe3")
    // public String saveOrUpdate (@ModelAttribute NotesCommand command)  bununla deneme yaptım
    //method içi çalıştı ancak attirbute ları almadı.
    public String saveOrUpdate (@ModelAttribute RecipeCommand command) {
        //our service returs back a new implementation of the command
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedCommand.getId();

        //redirect tells to Spring MVC to redirect to to a specific URL
        //saved  object comes back
    }
}
