package org.launchcode.controllers;

import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.nio.file.Paths;
import java.sql.Blob;

import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.core.env.Environment;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.Hibernate;
import javafx.stage.*;
import org.hibernate.*;
import org.launchcode.models.Category;
import org.launchcode.models.Imagefile;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.ImagefileDao;
import org.launchcode.models.data.ImagefileDao;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.validation.constraints.Null;
import java.io.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by shaikh on 5/15/2017.
 */
@Controller
@RequestMapping("Imagefile")

public class ImagefileController {


    private static ServiceRegistry serviceRegistry;
    private static Session session;

    private Session sessionFactory;

    @Autowired
    private ImagefileDao imagefileDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private org.springframework.core.env.Environment env;

    @RequestMapping(value = "menu", method = RequestMethod.GET)//this is after landed on the page.
    public String Imagefile(Model model, @RequestParam("id") int id) {
        //@PathVariable int categoryId for  route type /cheese/category/2. This controller will only apply
        // within the menu/view/2
        /** This controller only activates when the file uplaod button is clicked within the menu/view/id, and nowhere
         * else. */

        //model.addAttribute("file",file.getName());
        //System.out.println("File is:" + file.getName());

        Menu menu = menuDao.findOne(id);
        model.addAttribute("title", "Add images to the menu: " + menu.getName());
        //model.addAttribute("cheeses", menu.getCheeses());
        // model.addAttribute("menuId", menu.getId());


        //Category cat = imageDao.findOne(id);
        //model.addAttribute("title", category.getName());
        //model.addAttribute("cheeses", cat.getCheeses());
        //model.addAttribute("categoryId", category.getId());

        return "Imagefile/index";
    }
    //once in the /imagefile page then add the upload button

