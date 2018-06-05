package org.launchcode.cheesemvc.controllers;



import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;





@Controller
@RequestMapping("cheese") // every handler must be precede by /cheese
public class CheeseController {


    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model){ //model is used to pass data to the template or "view"


        model.addAttribute("cheeses",CheeseData.getAll()); //this one passes an object cheeses(the one on the left)
        model.addAttribute("title","My Cheeses"); //look on index ${title} links to title here it passes a string
        return "cheese/index"; // corresponds to the index.html template in /cheese
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese){
        CheeseData.add(newCheese);
        // Redirect to /cheese
        return "redirect:";
    }


    // request path cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeShowForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }
    // request path cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String cheeseRemove(@RequestParam int[] cheeseIds){

            for (int cheeseId : cheeseIds) {
                CheeseData.remove(cheeseId);
            }
            return "redirect:";

        }
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        Cheese c = CheeseData.getById(cheeseId); //creates variable "c"
        model.addAttribute("cheeses", c);
    return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(int cheeseId, String name, String description){

        Cheese c = CheeseData.getById(cheeseId);
        c.setName(name);
        c.setDescription(description);
    return "redirect:";
    }
}






