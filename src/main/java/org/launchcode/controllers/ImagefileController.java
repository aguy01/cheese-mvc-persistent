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

    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public String imageupload(Model model, @ModelAttribute Imagefile newimgfile,@RequestParam("id") int id,
                              @RequestParam("uploadFile") MultipartFile uploadFile,
                              RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {


        /**FileChooser fileChooser = new FileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");**/
        ;


        if (uploadFile.isEmpty()) {
            Menu menu = menuDao.findOne(id);
            model.addAttribute("title", "Add images to the menu: " + menu.getName());
            //redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            model.addAttribute("message", "Please select a file to upload. Use @valid statement here instead.");
            System.out.println("No file selected. Please select a file to upload. Use @valid statement here instead.");
            return "Imagefile/index";
        }
        String tempFileName = "/tempdir" + uploadFile.getOriginalFilename();

        //http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html
        /**org.springframework.web.multipart.MultipartFile is an interface so firstly you are going to need to work
         * with an implementation of this interface.
         The only implementation that I can see for that interface that you can use out-of-the-box is org.springframework.
         web.multipart.commons.CommonsMultipartFile. The API for that implementation can be found here
         http://static.springsource.org/spring/docs/3.0.x/api/org/springframework/web/multipart/commons/CommonsMultipartFile.html

         Alternatively as org.springframework.web.multipart.MultipartFile is an interface, you could provide your own
         implementation and simply wrap your byte array. As a trivial example:**/
        /**Menu menu = menuDao.findOne(menuId);
         model.addAttribute("title", menu.getName());
         model.addAttribute("cheeses", menu.getCheeses());
         model.addAttribute("menuId", menu.getId());**/
        //uploadFile.getBytes();
        //add photo upload coding here.

        //String uploadFilepath=Paths.get("." + File.separator, filename).toString();;

        //need to get the file into the input stream.

        //byte[] bytes = uploadFile.getBytes();
        //String filename1 = uploadFile.toString();
        //File f = new File(filename1);
        //f.getAbsolutePath();
        //FileInputStream fis = new FileInputStream(f.getAbsoluteFile());
        //f.getAbsolutePath();
        //Byte [] imagefile;
        //ByteArrayInputStream fileintoarray  = new ByteArrayInputStream(filename.getBytes());

        //InputStream is = new FileInputStream(new File(filename1));
        //File filename = new FileInputStream(filename1.getBytes());
        //String uploadFilename = uploadFile.getOriginalFilename();
        //createSessionFactory().openSession();

        //getSessionFactory().getCurrentSession();openSession()
        //File uploadfile = new File(filename);

        //Blob fileblob = Hibernate.getLobCreator(session).createBlob(filename.getBytes()); //new FileInputStream(uploadfile), file1.length()

        // File convFile = new File(uploadFile.getOriginalFilename());
        File file = new File(uploadFile.getOriginalFilename());//changing the multipart file into objet File
        //getProperty("upload.file.path");
        try {
            uploadFile.getBytes();
            uploadFile.getInputStream();

            String filename = uploadFile.getOriginalFilename();
            /**String directory = env.getProperty("file.path");
             String uploadFilePath = Paths.get("." + File.separator + directory, filename).toString();
            initSession();
            Session session = sessionFactory.getSessionFactory().getCurrentSession();
            Blob imageblob = Hibernate.getLobCreator(session).createBlob(new FileInputStream(file), file.length());**/
            // /file is the object with in try/catch block
            //endSession();
            byte[] imagefiledata = new byte[(int) file.length()];
            imagefiledata=uploadFile.getBytes();
            //byte[] imagefiledatainbytes  = uploadFile.getBytes();
             /**File file1 = new File(uploadFilePath);
            InputStream fin = new FileInputStream(file1);
             final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath)));
             stream.write(uploadFile.getBytes());
             stream.close();**/
            //FileInputStream fileInputStream = new FileInputStream(file1);
            //fileInputStream.read(imagefiledata);
            //fileInputStream.close();

            //FileInputStream fileInputStream = new FileInputStream(file);
            //fileInputStream.read(imagefiledata);
            //fileInputStream.close();

            /**fin.read(imagefiledata);
            fin.close();**/

            newimgfile.setMenu(menuDao.findOne(id));
            newimgfile.setImagecontent(imagefiledata);
            newimgfile.setFilename(uploadFile.getOriginalFilename());
            imagefileDao.save(newimgfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath)));


        //File convFile = new File( uploadFile.getOriginalFilename());
        //File savedFile = new File("/tempdir" + uploadFile);

        //String directory = "C:/Users/smahmoo8/IdeaProjects/cheese-mvc-persistent/tempdir";
        //String uploadFilePath = Paths.get("." + File.separator +directory, filename).toString();
        //uploadFile.transferTo();
        //byte [] byteArr=uploadFile.getBytes();
        // FileInputStream fileInputStream = new FileInputStream(file);
        Menu menu = menuDao.findOne(id);

        /***/model.addAttribute("title", "Add images to the menu: " + menu.getName());
        System.out.println("Original Filename is:" + file);

        System.out.println("File Class is:" + uploadFile.getClass());
        System.out.println("Is the File Empty?:" + uploadFile.isEmpty());
        System.out.println("File size:" + uploadFile.getSize() + ". Match this size to the image properties size in Windows");
        System.out.println("File contentType:" + uploadFile.getContentType());/****/


        //session.close();
        return "Imagefile/index";
    }

/**
    private static void initSession() {
        Configuration configuration = new Configuration().configure();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    private static void endSession() {
        session.getTransaction().commit();
        session.close();

        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    } **/


@RequestMapping(value = "/media-menuid/{menuid}", method = RequestMethod.GET)
public String postmedia(@PathVariable int menuid, Model model ) {//List <byte[]>

    Menu menu = menuDao.findOne(menuid);

    Imagefile image1 = imagefileDao.findOne(1);
    menu.getId();

    List<Imagefile> imagefiles = menu.getImagefiles();
    List <Integer> size = new ArrayList<Integer>();
    /**Integer size[] = new Integer[imagefiles.size()];
    for (int i = 0; i < imagefiles.size(); i++) {
        size[i]=i;

    }

    System.out.print("The size array has these elements : ");
    for (int i = 0; i < size.length; i++) {

        System.out.print(size[i]+" ");

    }**/

    for (int i = 0; i < imagefiles.size(); i++) {
        size.add(i);

    }

    System.out.print("The size array has these elements : ");
    for (Integer num : size) {//int i = 0; i < size.size(); i++

        System.out.print(num +" ");

    }

    imagefiles.get(0).getId();
    System.out.println("");
    model.addAttribute("menu",menu);
    model.addAttribute("numofimages",imagefiles);
    model.addAttribute("numofpics",imagefiles);
    model.addAttribute("array1",size);







    //byte[] newbyte = imagefileDao.findOne(menuid).getImagecontent();
    /**final HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.IMAGE_PNG);
     return new ResponseEntity<byte[]>(newbyte, headers, HttpStatus.OK);**/
    //String strimage= newbyte.toString();

    // imagefiles.stream();

    //pass size of the imagefiles array to the view.


    menu.getImagefiles().get(0);
    List<byte[]> imageBytes = new ArrayList<byte[]>();
    imageBytes.add(menu.getImagefiles().get(0).getImagecontent());
    imageBytes.add(menu.getImagefiles().get(1).getImagecontent());
    //byte[] menuimage = new byte[]<>;
    //byte[] menuimage =  menu.getImagefiles().get(2).getImagecontent();

    //model.addAttribute("imagefiles", imageBytes.get(0)); //menu.getImagefiles() ; menu.getImagefiles()
    System.out.println("Size of the List<Imagefile> imagefiles:" + imagefiles.size());
    System.out.println("The filename in the dB for the menuid 1:" + menu.getImagefiles().get(0).getFilename());
    System.out.println("Size of the image in bytes at 0 index:" + imageBytes.get(0).length+ "bytes");

    /*for (int i=0; i < menu.getImagefiles().size();i++) {


    }*/




    return "Imagefile/media-menuid";    /* retturns a list witb bytes in array. "Imagefile/media-menuid";"redirect:/menu/view/"
       theMenu.getId();"Imagefile/media-menuid";menuimage; imageBytes*/
}


}









