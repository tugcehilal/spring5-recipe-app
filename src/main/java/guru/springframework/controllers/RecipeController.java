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
    @RequestMapping("/recipe/{id}/show")
        public String showById (@PathVariable String id, Model model){
              model.addAttribute("recipe2", recipeService.findById(new Long(id)));

              return "recipe/show";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){

        //model.addAttribute("recipe88", recipeService.findCommandById(Long.valueOf(id)));//Neither BindingResult nor plain target object for bean name 'recipe5' available as request attribute
        //recipe5 recipeform.xhtml2de recipe/new de öyle çağırıyor. aynı adı vermek lazım o yüzden burada.
        model.addAttribute("recipe5", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
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

        return "redirect:/recipe/" + savedCommand.getId() +"/show";

        //redirect tells to Spring MVC to redirect to to a specific URL
        //saved  object comes back
    }
}
