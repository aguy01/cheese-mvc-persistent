package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */




@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll()); //cheeseDao contains category info as well due to the foreign key of category..
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";  //"redirect:" means to the cheese/index.html.
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";  //"redirect:" means to the cheese/index.html.
    }


//bonus mission  /cheese/category/2, where 2 may be the ID of any category in the system. category/{categoryId}
// category/{categoryId} another try with category?id=  or try cheese/category?id=

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam("id") int id) { //@PathVariable int categoryId for  route type
        // /cheese/category/2


        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();/**left side will automagically populate the List because
         hibernate did the work to fetch those objects from the database and to popuklate the list. cat Object
         has lis of cheeses. so with cat.getCheeses and that will automagically populate the list because hibernate
         will automatically done the work to fetch the objects from the db and populate the list. so if we were to look
         at the Category class, nothing is added to the List <Cheese> cheeses, but it will get populated because of the
         mapping set up. This list should be populated with this specific category_id, hibernate will go to work poulating
         that list for us, when we ask for it.
         In the Category Class, nothing is done to add anything to the list, because magic of jpa hibernate
         will fill the list only  when the List is in use at this way cat.getCheeses and because of the mapping
         is set up between the 2 classes Category and Cheese***/
        //model.addAttribute("cheeses", cheeses);
        //model.addAttribute("title", category.getName());
        model.addAttribute("cheeses", cat.getCheeses());
        //model.addAttribute("categoryId", category.getId());
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());

        return "cheese/index";}
}
