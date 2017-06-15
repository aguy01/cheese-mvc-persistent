package org.launchcode.controllers;

import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.nio.file.Paths;
import java.sql.Blob;
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
 * Created by shaikh on 6/12/2017.
 */

@Controller
@RequestMapping("Imagedlfile")
public class ImagedlController {

    @Autowired
    private ImagefileDao imagefileDao;

    @Autowired
    private MenuDao menuDao;


    @RequestMapping(value = "{imageid}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] postmedia(@PathVariable String imageid ) {//List <byte[]>

        /*Menu menu = menuDao.findOne(menuid);
        List<Imagefile> imagefiles = menu.getImagefiles();*/
        //int idint1 = Integer.parseInt(imageid);
        System.out.println("imageid passed in:" + imageid);

        try {
            int idint = Integer.parseInt(imageid);
            System.out.println("idint is %d:" + idint);


        } catch (NumberFormatException e) {
            //Will Throw exception!
            //do something! anything to handle the exception.
        }


        //Integer.parseInt(imageone.getFilename()); //Integer.parseInt(image.getId()
        //byte[] menuimage =  imageone.getImagecontent();


        //byte[] newbyte = imagefileDao.findOne(menuid).getImagecontent();
        /**final HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.IMAGE_PNG);
         return new ResponseEntity<byte[]>(newbyte, headers, HttpStatus.OK);**/
        //String strimage= newbyte.toString();

        // imagefiles.stream();


        /*menu.getImagefiles().get(0);
        List<byte[]> imageBytes = new ArrayList<byte[]>();
        imageBytes.add(menu.getImagefiles().get(0).getImagecontent());
        imageBytes.add(menu.getImagefiles().get(1).getImagecontent());*/
        //byte[] menuimage = new byte[]<>;
        //byte[] menuimage =  menu.getImagefiles().get(image-1).getImagecontent();

        //model.addAttribute("imagefiles", imageBytes.get(0)); //menu.getImagefiles() ; menu.getImagefiles()
        /*System.out.println("Size of the List<Imagefile> imagefiles:" + imagefiles.size());
        System.out.println("The filename in the dB for the menuid 1:" + menu.getImagefiles().get(0).getFilename());
        System.out.println("Size of the image in bytes at 0 index:" + imageBytes.get(0).length+ "bytes");

    /*for (int i=0; i < menu.getImagefiles().size();i++) {


    }*/
        Imagefile imageone= imagefileDao.findOne(1);
        byte[] menuimage =  imageone.getImagecontent();
        return menuimage;



    }



}









