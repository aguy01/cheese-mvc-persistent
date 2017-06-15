package org.launchcode.controllers;
import org.hibernate.Hibernate;
import org.launchcode.models.*;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.hibernate.HibernateException;
import org.hibernate.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by shaikh on 4/11/2017.
 */


@Controller
@RequestMapping(value= "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao; //Get the repository instance injected and use it.


    @Autowired
    private CheeseDao cheeseDao;

    //@Autowired
    //private ImageFileDao imageFileDao;

    File imgfile = new File("/images");
    byte[] byteFile = new byte[(int)imgfile.length()];
    //FileNameExtensionFilter; FileChooser;
    FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("*.IMAGE", "jpg",
                                                                                                    "gif","png");
    private Session sessionFactory;

    JButton button ;
    JButton button2;
    JLabel label;
    JTextField textID;
    JTextField textNAME;
    JTextArea area;
    String s;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Menu menu, //using the attributes to use the class or objects.
                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";   //http://localhost:8080/menu/add
        }

        menuDao.save(menu);

        return "redirect:view/" + menu.getId(); //if all goes well then navigate to the page menu/view/3 for e.g.
    }


    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        /**Let's create functionality to allow the user to view the contents of a menu. In MenuController, create a handler
     * named viewMenu that accepts GET requests at URLs like view/5, where 5 can be any menu ID. You'll need to use the
     * correct syntax within the @RequestMapping annotation, along with the @PathVariable annotation on a method
     * parameter that you'll add (which should be an int).  headers=("content-type=multipart/*") @RequestParam("file")
     * MultipartFile file***/



        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());



        return "menu/view";
    }


    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {


        Menu menu = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(
                cheeseDao.findAll(), menu);

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);


        return "menu/add-item";
    }


    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form,
                          Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);

        return "redirect:/menu/view/" + theMenu.getId();

    }
    @RequestMapping(value = "view/{id}", method = RequestMethod.POST)
    public String Imagefile(Model model, @PathVariable int id) {
        //@PathVariable int categoryId for  route type /cheese/category/2. This controller will only apply
        // within the menu/view/2       @RequestParam("file") MultipartFile file
        /** This controller only activates when the file uplaod button is clicked within the menu/view/id, and nowhere
         * else. */

        /***model.addAttribute("file",file.getName());
         System.out.println("File is:" + file.getName());
         System.out.println("Hello World !");****/
        model.addAttribute("imagefile","from image file");

        //Category cat = imageDao.findOne(id);
        //model.addAttribute("title", category.getName());
        //model.addAttribute("cheeses", cat.getCheeses());
        //model.addAttribute("categoryId", category.getId());

        return "" ;
    }

    //add photo upload coding here.
    @RequestMapping(value = "view/{menuId}", method = RequestMethod.POST)
    public String imageupload(Model model, @PathVariable int menuId, @RequestParam("uploadFile") MultipartFile uploadFile,
                              RedirectAttributes redirectAttributes) {



        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menuId", menu.getId());
        //add photo upload coding here.

        //need to get the file into the input stream.
        //String filename = uploadFile.toString();
        //uploadFile.getOriginalFilename();
        //File filename = new FileInputStream(uploadFile1);
        //String uploadFilename = uploadFile.getOriginalFilename();
        //Session session = sessionFactory.getSessionFactory().getCurrentSession();
        //File uploadfile = new File(uploadfile);
        //Blob fileblob = Hibernate.getLobCreator(session).createBlob(filename.getBytes()); //new FileInputStream(uploadfile), file1.length()

        //model.addAttribute("imagefile",uploadFile.getOriginalFilename());
        //System.out.println("File is:" + uploadfile.getOriginalFilename());

        return "";
    }



}