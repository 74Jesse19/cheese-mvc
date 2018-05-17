package org.launchcode.cheesemvc.controllers;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@RequestMapping("cheese") // every handler must be preced by /cheese
public class CheeseController {
    static ArrayList<String> cheeses = new ArrayList<>();
    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model){ //model is used to pass data to the template or "view"


        model.addAttribute("cheeses",cheeses); //this one passes an object cheeses(the one on the right)
        model.addAttribute("title","My Cheeses"); //look on index ${title} links to title here it passes a string
        return "cheese/index"; // corresponds to the index.html template in /cheese
    }

    @RequestMapping(value= "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName) {// spring will look for a param from add.html called cheeseName
        cheeses.add(cheeseName);
        // Redirect to /cheese
        return "redirect:";
    }
}



