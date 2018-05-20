package org.launchcode.cheesemvc.controllers;



import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping("cheese") // every handler must be precede by /cheese
public class CheeseController {
    //static HashMap<String, String> cheeses = new HashMap<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model){ //model is used to pass data to the template or "view"


        model.addAttribute("cheeses",Cheese.getCheeses()); //this one passes an object cheeses(the one on the left)
        model.addAttribute("title","My Cheeses"); //look on index ${title} links to title here it passes a string
        return "cheese/index"; // corresponds to the index.html template in /cheese
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDesc) {// spring will look for a param from add.html called cheeseName
            Cheese.setCheeses(cheeseName, cheeseDesc);

        // Redirect to /cheese
        return "redirect:";
    }


    // request path cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeShowForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", Cheese.getCheeses());
        return "cheese/remove";
    }
    // request path cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String cheeseRemove(@RequestParam ArrayList<String> cheese){

            for (String c : cheese) {
                Cheese.getCheeses().remove(c);
            }
            return "redirect:";

        }
}






