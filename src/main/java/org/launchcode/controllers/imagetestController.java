package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shaikh on 6/6/2017.
 */


@Controller
@RequestMapping("imagetest")
public class imagetestController {
    @RequestMapping(value = "/{someid}/tempdir", method = RequestMethod.GET)//tempdir is lcoated in static folder to keep all
    //multimedia files. media files at any other place wont let display.
    public String index(Model model,  @PathVariable int someid) {//single action to the handler

        model.addAttribute("title", "image test page");


        return "imagetest/index";//returns the template


    }

}
